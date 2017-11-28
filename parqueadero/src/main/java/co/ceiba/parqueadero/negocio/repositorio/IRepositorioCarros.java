package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;

public interface IRepositorioCarros extends IRepositorioVehiculos{
	CarroEntity obtenerCarroEntityPorPlaca(String placa);
}
