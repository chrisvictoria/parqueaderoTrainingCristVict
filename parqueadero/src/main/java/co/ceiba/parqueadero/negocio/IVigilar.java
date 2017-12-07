package co.ceiba.parqueadero.negocio;

import java.util.ArrayList;
import java.util.Date;

public interface IVigilar {
	public void revisarVehiculo(Vehiculo vehiculo);
	public void revisarPlacaPermiso(Date fechaEntrada, String placa);
	public double cobrarMoto(Registro registro);
	public double cobrarCarro(Registro registro);
	public ArrayList<Carro> obtenerCarros();
	public void registrarCarro(Carro carro);
	public void registrarEntradaCarro(Vehiculo vehiculo, Date fechaEntrada);
	public double registrarSalidaCarro(Vehiculo vehiculo , Date fechaSalida);
	public void registrarMoto(Moto moto);
	public ArrayList<Moto> obtenerMotos();
	public void registrarEntradaMoto(Vehiculo vehiculo, Date fechaEntrada);
	public double registrarSalidaMoto(Vehiculo vehiculo, Date fechaSalida);
	public ArrayList<Registro> obtenerMotosEnParqueadero();
	public ArrayList<Registro> obtenerCarrosEnParqueadero();
	public boolean estaCarroEnParqueadero(Vehiculo vehiculo);
	public boolean estaMotoEnParqueadero(Vehiculo vehiculo);
}
