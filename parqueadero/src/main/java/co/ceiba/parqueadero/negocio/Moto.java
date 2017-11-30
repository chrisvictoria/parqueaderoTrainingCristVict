package co.ceiba.parqueadero.negocio;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Moto extends Vehiculo{
	
	public Moto(){this.tipoVehiculo="Moto";}
	
	public Moto(String placa, double cilindraje) {
		super(placa, cilindraje);
		this.tipoVehiculo="Moto";
	}
	
	public Moto(String placa) {
		super(placa);
		this.tipoVehiculo="Moto";
	}
}
