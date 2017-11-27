package co.ceiba.parqueadero.negocio;

public class Moto extends Vehiculo{

	private double cilindraje;
		
	public Moto(String placa, double cilindraje) {
		super(placa);
		this.cilindraje = cilindraje;
	}
	
	public Moto(String placa) {
		super(placa);
	}
	
	@Override
	public double getValorParqueadero(){
		return 0;
	}
	
	public double getCilindraje(){
		return cilindraje;
	}
}
