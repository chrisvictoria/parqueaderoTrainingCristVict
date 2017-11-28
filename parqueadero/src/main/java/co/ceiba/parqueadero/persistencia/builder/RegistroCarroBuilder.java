package co.ceiba.parqueadero.persistencia.builder;

import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroCarroEntity;

public class RegistroCarroBuilder {
	
	public static RegistroCarroEntity convertirAEntity(Registro registro, CarroEntity carroEntity){
		RegistroCarroEntity registroCarroEntity = new RegistroCarroEntity();
		registroCarroEntity.setFecha(registro.getFecha());
		registroCarroEntity.setTipo(registro.getTipo());
		registroCarroEntity.setCarroEntity(carroEntity);
		return registroCarroEntity;
	}
	
	public static Registro convertirADominio(RegistroCarroEntity registroCarroEntity){
		Registro registro = null;
		if(registroCarroEntity != null){
			registro = new Registro(registroCarroEntity.getFecha(), registroCarroEntity.getTipo(), CarroBuilder.convertirADominio(registroCarroEntity.getCarroEntity()));
		}
		return registro;
	}
}
