package testdatabuilder;

import co.ceiba.parqueadero.negocio.Carro;

public class CarroTestDataBuilder {
	private String placa;
	
	public CarroTestDataBuilder conPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public Carro build() {
		return new Carro(this.placa);
	}
}
