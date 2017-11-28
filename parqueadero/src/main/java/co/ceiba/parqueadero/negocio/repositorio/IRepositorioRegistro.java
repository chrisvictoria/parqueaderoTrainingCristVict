package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.negocio.Registro;

public interface IRepositorioRegistro {
	public void agregarRegistroCarro(Registro registro);
	public void agregarRegistroMoto(Registro registro);
	Registro obtenerUltimoRegistroMotoPorPlaca(String placa);
	Registro obtenerUltimoRegistroCarroPorPlaca(String placa);
}
