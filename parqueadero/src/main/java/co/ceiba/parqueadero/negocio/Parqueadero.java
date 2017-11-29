package co.ceiba.parqueadero.negocio;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;

@Scope(value = "application")
@Component
public class Parqueadero {
	
	public static final String VEHICULO_ESTA_EN_PARQUEADERO = "Ya se ecuentra un vehiculo con la placa en el parqueadero.";
	public static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "No se ecuentra un vehiculo con la placa en el parqueadero.";
	public static final String PARQUEADERO_MOTOS_LLENO = "El parqueadero de motos esta lleno.";
	public static final String PARQUEADERO_CARROS_LLENO = "El parqueadero de carros esta lleno.";
	public static final String VEHICULO_DIA_NO_PERMITIDO = "El vehiculo no tiene permitido entrar al parqueadero porque la placa comienza con A.";
	
	private IVigilar vigilante;
	
	private SistemaDePersistencia sistemaDePersistencia;
	
	private IEstrategiaCobro estrategiaCobroCarro;
	private IEstrategiaCobro estrategiaCobroMoto;	
	
	private int capacidadMaximaCarros;
	
	private int capacidadMaximaMotos;	
	
	private int cantidadMotos = 0;
	private int cantidadCarros = 0;
	
	@Autowired
	public Parqueadero(@Value("20")int capacidadMaximaCarros,@Value("10") int capacidadMaximaMotos, SistemaDePersistencia sistemaDePersistencia, IVigilar vigilante, IEstrategiaCobro estrategiaCobroCarro, IEstrategiaCobro estrategiaCobroMoto){
		this.capacidadMaximaCarros = capacidadMaximaCarros;
		this.capacidadMaximaMotos = capacidadMaximaMotos;
		this.vigilante = vigilante;
		this.sistemaDePersistencia = sistemaDePersistencia;
		this.estrategiaCobroCarro = estrategiaCobroCarro;
		this.estrategiaCobroMoto = estrategiaCobroMoto;
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
	
	public int getCantidadMotos() {
		return cantidadMotos;
	}

	public int getCantidadCarros() {
		return cantidadCarros;
	}
		
	private void revisarPlacaPermiso(Registro registro){
		Calendar iniCal = Calendar.getInstance();
		iniCal.setTime(registro.getFechaEntrada());
		int diaSemana = iniCal.get(Calendar.DAY_OF_WEEK);
		if(registro.getVehiculo().getPlaca().toLowerCase().substring(0,1).equals("a")  && (diaSemana != Calendar.SUNDAY && diaSemana != Calendar.MONDAY)){
			throw new VehiculoException(VEHICULO_DIA_NO_PERMITIDO);
		}
	}
	
	public void registrarEntradaCarro(Vehiculo vehiculo, Date fechaEntrada){
		if(estaCarroEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_ESTA_EN_PARQUEADERO);
		}		
		if(cantidadCarros+1 > capacidadMaximaCarros){
			throw new VehiculoException(PARQUEADERO_CARROS_LLENO);
		}
		vigilante.revisarVehiculo(vehiculo);
		
		Registro registro = new Registro(fechaEntrada, null , vehiculo, 0.0);
		revisarPlacaPermiso(registro);
		
		sistemaDePersistencia.getRepositorioCarros().agregar(vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroCarro(registro);
		cantidadCarros += 1;
	}
	
	public double registrarSalidaCarro(Vehiculo vehiculo , Date fechaSalida){
		if(!estaCarroEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		vigilante.revisarVehiculo(vehiculo);
		Registro registro = sistemaDePersistencia.getRepositorioRegistro().obtenerUltimoRegistroCarroPorPlaca(vehiculo.getPlaca());
		registro.setFechaSalida(fechaSalida);
		cobrarCarro(registro);
		sistemaDePersistencia.getRepositorioRegistro().actualizarRegistroCarro(registro);
		cantidadCarros -= 1;
		return registro.getValor();
	}
	
	public void registrarEntradaMoto(Vehiculo vehiculo, Date fechaEntrada){
		if(estaMotoEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_ESTA_EN_PARQUEADERO);
		}		
		if(cantidadMotos+1 > capacidadMaximaMotos){
			throw new VehiculoException(PARQUEADERO_MOTOS_LLENO);
		}
		vigilante.revisarVehiculo(vehiculo);
		
		Registro registro = new Registro(fechaEntrada, null, vehiculo, 0.0);
		revisarPlacaPermiso(registro);
		
		sistemaDePersistencia.getRepositorioMotos().agregar (vehiculo);		
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroMoto(registro);
		cantidadMotos += 1;
	}
	
	/*
	public void registrarSalidaMoto(Vehiculo vehiculo){
		if(!estaMotoEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		vigilante.revisarVehiculo(vehiculo);
		sistemaDePersistencia.getRepositorioMotos().agregar(vehiculo);
		Registro registro = new Registro(new Date(), Registro.TIPO_SALIDA , vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroMoto(registro);
		cantidadMotos -= 1;
	}
	*/
	private void cobrarMoto(Registro registro){
		Moto moto = (Moto)registro.getVehiculo();
		double valor = estrategiaCobroMoto.cobrar(registro.getFechaEntrada(), registro.getFechaSalida(), moto.getCilindraje());
		registro.setValor(valor);
	}
	
	private void cobrarCarro(Registro registro){
		double valor = estrategiaCobroCarro.cobrar(registro.getFechaEntrada(), registro.getFechaSalida(), 0.0);
		registro.setValor(valor);
	}
	
	public boolean estaCarroEnParqueadero(Vehiculo vehiculo){
		Registro registro = sistemaDePersistencia.getRepositorioRegistro().obtenerUltimoRegistroCarroPorPlaca(vehiculo.getPlaca());
		return registro != null && registro.getFechaSalida() == null ? true : false;
	}
	
	public boolean estaMotoEnParqueadero(Vehiculo vehiculo){
		Registro registro = sistemaDePersistencia.getRepositorioRegistro().obtenerUltimoRegistroMotoPorPlaca(vehiculo.getPlaca());
		return registro != null && registro.getFechaSalida() == null ? true : false;
	}
}
