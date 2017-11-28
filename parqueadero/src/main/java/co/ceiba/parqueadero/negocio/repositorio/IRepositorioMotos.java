package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;

public interface IRepositorioMotos extends IRepositorioVehiculos{
	MotoEntity obtenerMotoEntityPorPlaca(String placa);
}
