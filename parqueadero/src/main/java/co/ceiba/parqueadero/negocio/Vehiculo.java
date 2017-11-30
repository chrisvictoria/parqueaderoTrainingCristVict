package co.ceiba.parqueadero.negocio;


public abstract class Vehiculo {
	String placa;
	String tipoVehiculo;
	double cilindraje;
	
	public Vehiculo(){}
	
	public Vehiculo(String  placa){
		this.placa = placa;
	}
	
	public Vehiculo(String  placa, double cilindraje){
		this.placa = placa;
		this.cilindraje = cilindraje;
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

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}
	
}
