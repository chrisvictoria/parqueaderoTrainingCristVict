package co.ceiba.parqueadero.negocio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;

@Scope(value = "application")
@Component
public class Parqueadero {
	
	@Autowired
	private IVigilar vigilante;

	private SistemaDePersistencia sistemaDePersistencia;
	private IEstrategiaCobro estrategiaCobro;
	
	@Value("20")
	private int capacidadCarros;
	@Value("10")
	private int capacidadMotos;	
	
	public Parqueadero(int capacidadCarros, int capacidadMotos){
		this.capacidadCarros = capacidadCarros;
		this.capacidadMotos = capacidadMotos;
		this.sistemaDePersistencia = new SistemaDePersistencia();
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
	
	public IEstrategiaCobro getEstrategiaCobro() {
		return estrategiaCobro;
	}

	public void setEstrategiaCobro(IEstrategiaCobro estrategiaCobro) {
		this.estrategiaCobro = estrategiaCobro;
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
