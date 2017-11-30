package co.ceiba.parqueadero.negocio;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Carro extends Vehiculo{
	
	public Carro(){this.tipoVehiculo="Carro";}
	
	public Carro(String placa) {
		super(placa);
		this.tipoVehiculo="Carro";
	}
}
