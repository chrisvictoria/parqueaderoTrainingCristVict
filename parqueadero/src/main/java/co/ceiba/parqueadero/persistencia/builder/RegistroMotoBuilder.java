package co.ceiba.parqueadero.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroMotoEntity;

public class RegistroMotoBuilder {
	
	private RegistroMotoBuilder() {
	    throw new IllegalStateException("Utility class");
	}
	
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
	
	public static ArrayList<Registro> convertirListaADominio(List<RegistroMotoEntity> registroMotoEntityList){
		ArrayList<Registro> registroList = new ArrayList<>();
		if(registroMotoEntityList != null){
			ArrayList<RegistroMotoEntity> array = new ArrayList<>(registroMotoEntityList);
			for(RegistroMotoEntity entity : array){
				Registro registro = convertirADominio(entity);
				registroList.add(registro);
			}
		}
		return registroList;
	}
	
	public static RegistroMotoEntity actualizarDatosEntity(RegistroMotoEntity registroMotoEntity, Registro registro){
		registroMotoEntity.setFechaEntrada(registro.getFechaEntrada());
		registroMotoEntity.setFechaSalida(registro.getFechaSalida());
		registroMotoEntity.setValor(registro.getValor());
		return registroMotoEntity;
	}
}
