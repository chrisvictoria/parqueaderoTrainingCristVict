package co.ceiba.parqueadero.persistencia.builder;

import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroMotoEntity;

public class RegistroMotoBuilder {
	public static RegistroMotoEntity convertirAEntity(Registro registro, MotoEntity motoEntity){
		RegistroMotoEntity registroMotoEntity = new RegistroMotoEntity();
		registroMotoEntity.setFechaEntrada(registro.getFechaEntrada());
		registroMotoEntity.setFechaSalida(registro.getFechaSalida());
		registroMotoEntity.setValor(registro.getValor());
		registroMotoEntity.setMotoEntity(motoEntity);
		return registroMotoEntity;
	}
	
	public static Registro convertirADominio(RegistroMotoEntity registroMotoEntity){
		Registro registro = null;
		if(registroMotoEntity != null){
			registro = new Registro(registroMotoEntity.getFechaEntrada(), registroMotoEntity.getFechaSalida(), MotoBuilder.convertirADominio(registroMotoEntity.getMotoEntity()), registroMotoEntity.getValor());
		}
		return registro;
	}
}
