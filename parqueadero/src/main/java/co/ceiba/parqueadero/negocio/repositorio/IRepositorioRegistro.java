package co.ceiba.parqueadero.negocio.repositorio;

import co.ceiba.parqueadero.negocio.Registro;

public interface IRepositorioRegistro {
	public void agregarRegistroCarro(Registro registro);
	public void actualizarRegistroCarro(Registro registro);
	public void agregarRegistroMoto(Registro registro);
	public void actualizarRegistroMoto(Registro regitro);
	Registro obtenerUltimoRegistroMotoPorPlaca(String placa);
	Registro obtenerUltimoRegistroCarroPorPlaca(String placa);
}
