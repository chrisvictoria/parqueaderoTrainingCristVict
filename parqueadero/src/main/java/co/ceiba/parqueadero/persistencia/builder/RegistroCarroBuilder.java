package co.ceiba.parqueadero.persistencia.builder;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.persistencia.entidad.RegistroCarroEntity;

public class RegistroCarroBuilder {

	public static RegistroCarroEntity convertirAEntity(Registro registro){
		RegistroCarroEntity registroCarroEntity = new RegistroCarroEntity();
		registroCarroEntity.setFecha(registro.getFecha());
		registroCarroEntity.setTipo(registro.getTipo());
		registroCarroEntity.setCarroEntity(CarroBuilder.convertirAEntity((Carro)registro.getVehiculo()));
		return registroCarroEntity;
	}
}
