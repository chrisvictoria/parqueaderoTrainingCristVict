package co.ceiba.parqueadero.negocio.repositorio;

import java.util.ArrayList;

import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;

public interface IRepositorioMotos extends IRepositorioVehiculos{
	MotoEntity obtenerMotoEntityPorPlaca(String placa);
	ArrayList<Moto> obtenerTodos();
}
