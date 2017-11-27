package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.negocio.Moto;

public interface IRepositorioMotos {
	
	Moto obtenerPorPlaca(String placa);
	void agregar(Moto moto);
}
