package co.ceiba.parqueadero.negocio;

import java.util.Date;

public abstract class Vehiculo {
	String placa;

	IEstrategiaCobro estrategiaCobro;
	
	
	public Vehiculo(String  placa){
		this.placa = placa;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public IEstrategiaCobro getEstrategiaCobro() {
		return estrategiaCobro;
	}

	public void setEstrategiaCobro(IEstrategiaCobro estrategiaCobro) {
		this.estrategiaCobro = estrategiaCobro;
	}
	
	public abstract double getValorParqueadero();
}
