package co.ceiba.parqueadero.servicios;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;

public interface IRegistroVehiculos {
	public void registrarCarro(Carro carro);
	public void registrarMoto(Moto moto);
}
