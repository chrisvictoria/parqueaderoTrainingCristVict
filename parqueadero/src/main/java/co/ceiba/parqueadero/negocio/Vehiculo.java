package co.ceiba.parqueadero.negocio;

public abstract class Vehiculo {
	String placa;
	static String tipoVehiculo;
	
	public Vehiculo(){}
	
	public Vehiculo(String  placa){
		this.placa = placa;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
}
