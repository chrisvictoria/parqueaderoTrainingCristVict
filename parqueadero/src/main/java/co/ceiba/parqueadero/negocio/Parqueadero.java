package co.ceiba.parqueadero.negocio;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;

@Scope(value = "application")
@Component
@Transactional
public class Parqueadero {
	
	public static final String VEHICULO_ESTA_EN_PARQUEADERO = "Ya se ecuentra un vehiculo con la placa en el parqueadero.";
	public static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "No se ecuentra un vehiculo con la placa en el parqueadero.";
	public static final String PARQUEADERO_MOTOS_LLENO = "El parqueadero de motos esta lleno.";
	public static final String PARQUEADERO_CARROS_LLENO = "El parqueadero de carros esta lleno.";
	public static final String VEHICULO_DIA_NO_PERMITIDO = "El vehiculo no tiene permitido entrar al parqueadero porque la placa comienza con A.";
	public static final String VEHICULO_NO_REGISTRADO = "El vehiculo no esta registrado en la BD.";
	
	private IVigilar vigilante;
	
	@Autowired
	private IRepositorioRegistro repositorioRegistro;
	@Autowired
	private IRepositorioCarros repositorioCarros;
	@Autowired
	private IRepositorioMotos repositorioMotos;
	
	private IEstrategiaCobro estrategiaCobroCarro;
	private IEstrategiaCobro estrategiaCobroMoto;	
	
	private int capacidadMaximaCarros;
	
	private int capacidadMaximaMotos;	
	
	private int cantidadMotos = 0;
	private int cantidadCarros = 0;
	
	@Autowired
	public Parqueadero( @Value("20")int capacidadMaximaCarros,
						@Value("10") int capacidadMaximaMotos, 
						IVigilar vigilante, 
						IEstrategiaCobro estrategiaCobroCarro, 
						IEstrategiaCobro estrategiaCobroMoto, 
						IRepositorioRegistro repositorioRegistro,
						IRepositorioCarros repositorioCarros,
						IRepositorioMotos repositorioMotos){
		this.capacidadMaximaCarros = capacidadMaximaCarros;
		this.capacidadMaximaMotos = capacidadMaximaMotos;
		this.vigilante = vigilante;
		this.estrategiaCobroCarro = estrategiaCobroCarro;
		this.estrategiaCobroMoto = estrategiaCobroMoto;
		this.repositorioRegistro = repositorioRegistro;
		this.repositorioCarros = repositorioCarros;
		this.repositorioMotos = repositorioMotos;
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
		
	private void revisarPlacaPermiso(Date fechaEntrada, String placa){
		Calendar iniCal = Calendar.getInstance();
		iniCal.setTime(fechaEntrada);
		int diaSemana = iniCal.get(Calendar.DAY_OF_WEEK);
		if(placa.toLowerCase().substring(0,1).equals("a")  && (diaSemana != Calendar.SUNDAY && diaSemana != Calendar.MONDAY)){
			throw new VehiculoException(VEHICULO_DIA_NO_PERMITIDO);
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Carro> obtenerCarros(){
		return (ArrayList<Carro>)repositorioCarros.obtenerTodos();
	}
	
	public void registrarCarro(Carro carro){
		Carro carroBD = (Carro) repositorioCarros.obtenerPorPlaca(carro.getPlaca());
		if(carroBD == null){
			repositorioCarros.agregar(carro);
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
		revisarPlacaPermiso(fechaEntrada, vehiculo.getPlaca());
		
		Carro carro = (Carro) repositorioCarros.obtenerPorPlaca(vehiculo.getPlaca());
		if(carro == null){
			throw new VehiculoException(VEHICULO_NO_REGISTRADO);
		}
		
		Registro registro = new Registro(fechaEntrada, null , carro, 0.0);		
		
		repositorioRegistro.agregarRegistroCarro(registro);
		cantidadCarros += 1;
	}
	
	public double registrarSalidaCarro(Vehiculo vehiculo , Date fechaSalida){
		if(!estaCarroEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		vigilante.revisarVehiculo(vehiculo);
		Registro registro = repositorioRegistro.obtenerUltimoRegistroCarroPorPlaca(vehiculo.getPlaca());
		registro.setFechaSalida(fechaSalida);
		cobrarCarro(registro);
		repositorioRegistro.actualizarRegistroCarro(registro);
		cantidadCarros -= 1;
		return registro.getValor();
	}
	
	public void registrarMoto(Moto moto){
		Moto motoBD = (Moto) repositorioMotos.obtenerPorPlaca(moto.getPlaca());
		if(motoBD == null){
			repositorioMotos.agregar(moto);
		}		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Moto> obtenerMotos(){
		return (ArrayList<Moto>)repositorioMotos.obtenerTodos();
	}
	
	public void registrarEntradaMoto(Vehiculo vehiculo, Date fechaEntrada){
		if(estaMotoEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_ESTA_EN_PARQUEADERO);
		}		
		if(cantidadMotos+1 > capacidadMaximaMotos){
			throw new VehiculoException(PARQUEADERO_MOTOS_LLENO);
		}
		vigilante.revisarVehiculo(vehiculo);
		revisarPlacaPermiso(fechaEntrada, vehiculo.getPlaca());
		
		Moto moto = (Moto) repositorioMotos.obtenerPorPlaca(vehiculo.getPlaca());
		
		if(moto == null){
			throw new VehiculoException(VEHICULO_NO_REGISTRADO);
		}
		
		Registro registro = new Registro(fechaEntrada, null, moto, 0.0);
		repositorioRegistro.agregarRegistroMoto(registro);
		cantidadMotos += 1;
	}	
	
	public double registrarSalidaMoto(Vehiculo vehiculo, Date fechaSalida){
		if(!estaMotoEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		vigilante.revisarVehiculo(vehiculo);
		
		Registro registro = repositorioRegistro.obtenerUltimoRegistroMotoPorPlaca(vehiculo.getPlaca());
		registro.setFechaSalida(fechaSalida);
		cobrarMoto(registro);
		repositorioRegistro.actualizarRegistroMoto(registro);
		cantidadMotos -= 1;
		return registro.getValor();
	}
	
	public ArrayList<Registro> obtenerMotosEnParqueadero(){
		return repositorioRegistro.obtenerRegistrosMotos();
	}
	
	public ArrayList<Registro> obtenerCarrosEnParqueadero(){
		return repositorioRegistro.obtenerRegistrosCarros();
	}
	
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
		Registro registro = repositorioRegistro.obtenerUltimoRegistroCarroPorPlaca(vehiculo.getPlaca());
		return registro != null && registro.getFechaSalida() == null ? true : false;
	}
	
	public boolean estaMotoEnParqueadero(Vehiculo vehiculo){
		Registro registro = repositorioRegistro.obtenerUltimoRegistroMotoPorPlaca(vehiculo.getPlaca());
		return registro != null && registro.getFechaSalida() == null ? true : false;
	}
}
