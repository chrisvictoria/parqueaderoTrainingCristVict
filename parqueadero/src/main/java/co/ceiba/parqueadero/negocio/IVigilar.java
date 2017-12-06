package co.ceiba.parqueadero.negocio;

import java.util.Date;

public interface IVigilar {
	public void revisarVehiculo(Vehiculo vehiculo);
	public void revisarPlacaPermiso(Date fechaEntrada, String placa);
	public double cobrarMoto(Registro registro);
	public double cobrarCarro(Registro registro);
}
