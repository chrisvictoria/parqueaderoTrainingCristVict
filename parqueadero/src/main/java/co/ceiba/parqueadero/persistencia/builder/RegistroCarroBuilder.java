package co.ceiba.parqueadero.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

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
	
	public static ArrayList<Registro> convertirListaADominio(List<RegistroCarroEntity> registroCarroEntityList){
		ArrayList<Registro> registroList = new ArrayList<Registro>();
		if(registroCarroEntityList != null){
			ArrayList<RegistroCarroEntity> array = new ArrayList<RegistroCarroEntity>(registroCarroEntityList);
			for(RegistroCarroEntity entity : array){
				Registro registro = convertirADominio(entity);
				registroList.add(registro);
			}
		}
		return registroList;
	}
	
	public static RegistroCarroEntity actualizarDatosEntity(RegistroCarroEntity registroCarroEntity, Registro registro){
		registroCarroEntity.setFechaEntrada(registro.getFechaEntrada());
		registroCarroEntity.setFechaSalida(registro.getFechaSalida());
		registroCarroEntity.setValor(registro.getValor());
		return registroCarroEntity;
	}
}
