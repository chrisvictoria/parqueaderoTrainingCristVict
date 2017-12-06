package co.ceiba.parqueadero.negocio.repositorio;

import java.util.ArrayList;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;

public interface IRepositorioCarros extends IRepositorioVehiculos{
	CarroEntity obtenerCarroEntityPorPlaca(String placa);
	ArrayList<Carro> obtenerTodos();
}
