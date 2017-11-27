package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.negocio.Vehiculo;

public interface IRepositorioVehiculos {
	Vehiculo obtenerPorPlaca(String placa);
	void agregar(Vehiculo vehiculo);
}
