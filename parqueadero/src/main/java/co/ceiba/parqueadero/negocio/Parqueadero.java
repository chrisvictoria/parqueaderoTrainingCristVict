package co.ceiba.parqueadero.negocio;


import java.util.Date;

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
	@Autowired
	private SistemaDePersistencia sistemaDePersistencia;
	private IEstrategiaCobro estrategiaCobro;
	
	@Value("20")
	private int capacidadMaximaCarros;
	@Value("10")
	private int capacidadMaximaMotos;	
	
	private int cantidadMotos = 0;
	private int cantidadCarros = 0;
	
	public Parqueadero(SistemaDePersistencia sistemaDePersistencia, IVigilar vigilante){
		this.vigilante = vigilante;
		this.sistemaDePersistencia = sistemaDePersistencia;
	}
	
	public IVigilar getVigilante() {
		return vigilante;
	}

	public void setVigilante(IVigilar vigilante) {
		this.vigilante = vigilante;
	}

	public int getCapacidadCarros() {
		return capacidadMaximaCarros;
	}

	public int getCapacidadMotos() {
		return capacidadMaximaMotos;
	}
	
	public IEstrategiaCobro getEstrategiaCobro() {
		return estrategiaCobro;
	}

	public void setEstrategiaCobro(IEstrategiaCobro estrategiaCobro) {
		this.estrategiaCobro = estrategiaCobro;
	}
	
	public int getCantidadMotos() {
		return cantidadMotos;
	}

	public int getCantidadCarros() {
		return cantidadCarros;
	}

	public void registrarEntradaCarro(Vehiculo vehiculo){
		vigilante.revisarVehiculo(vehiculo);
		sistemaDePersistencia.getRepositorioCarros().agregar(vehiculo);
		Registro registro = new Registro(new Date(), Registro.TIPO_ENTRADA , vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroCarro(registro);
		cantidadCarros += 1;
	}
	
	public void registrarEntradaMoto(Vehiculo vehiculo){
		vigilante.revisarVehiculo(vehiculo);
		sistemaDePersistencia.getRepositorioMotos().agregar(vehiculo);
		Registro registro = new Registro(new Date(), Registro.TIPO_ENTRADA , vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroMoto(registro);
		cantidadMotos += 1;
	}
	
	public void registrarSalidaCarro(Vehiculo vehiculo){
		vigilante.revisarVehiculo(vehiculo);
		Registro registro = new Registro(new Date(), Registro.TIPO_SALIDA , vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroCarro(registro);
		cantidadCarros -= 1;
	}
	
	public void registrarSalidaMoto(Vehiculo vehiculo){
		vigilante.revisarVehiculo(vehiculo);
		sistemaDePersistencia.getRepositorioMotos().agregar(vehiculo);
		Registro registro = new Registro(new Date(), Registro.TIPO_SALIDA , vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroMoto(registro);
		cantidadMotos -= 1;
	}
	
	public void cobrarVehiculo(Vehiculo vehiculo){
		
	}
}
