package co.ceiba.parqueadero.negocio;

public class Moto extends Vehiculo{

	private double cilindraje;
		
	public Moto(String placa, double cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
		this.tipoVehiculo = "MOTO";
	}
	
	public Moto(String placa) {
		super(placa);
	}
	
	public double getCilindraje(){
		return cilindraje;
	}
}
