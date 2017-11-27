package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.negocio.Carro;

public interface IRepositorioCarros {
	Carro obtenerPorPlaca(String placa);
	void agregar(Carro carro);
}
