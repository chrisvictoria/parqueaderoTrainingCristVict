package co.ceiba.parqueadero.persistencia.builder;

import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.persistencia.entidad.RegistroMotoEntity;

public class RegistroMotoBuilder {
	public static RegistroMotoEntity convertirAEntity(Registro registro){
		RegistroMotoEntity registroMotoEntity = new RegistroMotoEntity();
		registroMotoEntity.setFecha(registro.getFecha());
		registroMotoEntity.setTipo(registro.getTipo());
		registroMotoEntity.setMotoEntity(MotoBuilder.convertirAEntity((Moto)registro.getVehiculo()));
		return registroMotoEntity;
	}
}
