package co.ceiba.parqueadero.persistencia.builder;

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
	
	public static CarroEntity convertirAEntity(Carro carro){
		CarroEntity carroEntity = new CarroEntity();
		carroEntity.setPlaca(carro.getPlaca());
		return carroEntity;
	}
}
