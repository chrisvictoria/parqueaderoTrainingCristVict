package co.ceiba.parqueadero.negocio;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;

@Scope(value = "application")
@Component
public class Parqueadero {
	
	@Autowired
	private IVigilar vigilante;

	IRepositorioMotos repositorioMotos;
	IRepositorioCarros repositorioCarros;
	
	@Value("20")
	private int capacidadCarros;
	@Value("10")
	private int capacidadMotos;	
	
	public Parqueadero(IRepositorioMotos repositorioMotos, IRepositorioCarros repositorioCarros, int capacidadCarros, int capacidadMotos){
		this.repositorioMotos = repositorioMotos;
		this.repositorioCarros = repositorioCarros;
		this.capacidadCarros = capacidadCarros;
		this.capacidadMotos = capacidadMotos;
	}
	
	public IVigilar getVigilante() {
		return vigilante;
	}

	public void setVigilante(IVigilar vigilante) {
		this.vigilante = vigilante;
	}

	public int getCapacidadCarros() {
		return capacidadCarros;
	}

	public int getCapacidadMotos() {
		return capacidadMotos;
	}

	public void registrarEntradaVehiculo(Vehiculo vehiculo){
		vigilante.revisarVehiculo(vehiculo);
	}
	
	public void registrarSalidaVehiculo(Vehiculo vehiculo){
		vigilante.revisarVehiculo(vehiculo);
	}
	
	public void cobrarVehiculo(Vehiculo vehiculo){
		
	}
}
