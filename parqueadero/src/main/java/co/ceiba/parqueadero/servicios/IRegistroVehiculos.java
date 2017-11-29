package co.ceiba.parqueadero.servicios;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;

public interface IRegistroVehiculos {
	public String registrarEntradaCarro(Carro carro);
	public String registrarSalidaCarro(Carro carro);
	public String registrarEntradaMoto(Moto moto);
	public String registrarSalidaMoto(Moto moto);
}
