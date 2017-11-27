package testdatabuilder;

import co.ceiba.parqueadero.negocio.Moto;

public class MotoTestDataBuilder {

	private String placa;
	private double cilindraje;
	
	public MotoTestDataBuilder(){
		
	}
	
	public MotoTestDataBuilder conPlaca(String placa){
		this.placa = placa;
		return this;
	}
	
	public MotoTestDataBuilder conCilindraje(double cilindraje){
		this.cilindraje = cilindraje;
		return this;
	}
	
	public Moto build() {
		return new Moto(this.placa, this.cilindraje);
	}
}
