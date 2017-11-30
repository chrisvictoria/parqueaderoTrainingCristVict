package co.ceiba.parqueadero.persistencia.builder;

import java.util.ArrayList;
import java.util.List;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;

public class CarroBuilder {
	
	public static Carro convertirADominio(CarroEntity carroEntity){
		Carro carro = null;
		if(carroEntity != null){
			carro = new Carro(carroEntity.getPlaca());
		}
		return carro;
	}
	
	public static ArrayList<Carro> convertirListaADominio(List<CarroEntity> carroEntityList){
		ArrayList<Carro> carroList = new ArrayList<Carro>();
		if(carroEntityList != null){
			ArrayList<CarroEntity> array = new ArrayList<CarroEntity>(carroEntityList);
			for(CarroEntity entity : array){
				Carro carro = convertirADominio(entity);
				carroList.add(carro);
			}
		}
		return carroList;
	}
	
	public static CarroEntity convertirAEntity(Carro carro){
		CarroEntity carroEntity = new CarroEntity();
		carroEntity.setPlaca(carro.getPlaca());
		return carroEntity;
	}
}
