package co.ceiba.parqueadero.persistencia.builder;

import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroCarroEntity;

public class RegistroCarroBuilder {
	
	public static RegistroCarroEntity convertirAEntity(Registro registro, CarroEntity carroEntity){
		RegistroCarroEntity registroCarroEntity = new RegistroCarroEntity();
		registroCarroEntity.setFechaEntrada(registro.getFechaEntrada());
		registroCarroEntity.setFechaSalida(registro.getFechaSalida());
		registroCarroEntity.setValor(registro.getValor());
		registroCarroEntity.setCarroEntity(carroEntity);
		return registroCarroEntity;
	}
	
	public static Registro convertirADominio(RegistroCarroEntity registroCarroEntity){
		Registro registro = null;
		if(registroCarroEntity != null){
			registro = new Registro(registroCarroEntity.getFechaEntrada(), registroCarroEntity.getFechaSalida(), CarroBuilder.convertirADominio(registroCarroEntity.getCarroEntity()), registroCarroEntity.getValor());
		}
		return registro;
	}
	
	public static RegistroCarroEntity actualizarDatosEntity(RegistroCarroEntity registroCarroEntity, Registro registro){
		registroCarroEntity.setFechaEntrada(registro.getFechaEntrada());
		registroCarroEntity.setFechaSalida(registro.getFechaSalida());
		registroCarroEntity.setValor(registro.getValor());
		return registroCarroEntity;
	}
}
