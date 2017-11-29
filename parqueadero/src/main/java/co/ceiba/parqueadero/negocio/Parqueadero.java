package co.ceiba.parqueadero.negocio;


import java.util.Date;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
*/
import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;

//@Scope(value = "application")
//@Component
public class Parqueadero {
	
	public static final String VEHICULO_ESTA_EN_PARQUEADERO = "Ya se ecuentra un vehiculo con la placa en el parqueadero";
	public static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "No se ecuentra un vehiculo con la placa en el parqueadero";
	public static final String PARQUEADERO_MOTOS_LLENO = "El parqueadero de motos esta lleno";
	public static final String PARQUEADERO_CARROS_LLENO = "El parqueadero de carros esta lleno";
	
	//@Autowired
	private IVigilar vigilante;
	//@Autowired
	private SistemaDePersistencia sistemaDePersistencia;
	private IEstrategiaCobro estrategiaCobroCarro;
	private IEstrategiaCobro estrategiaCobroMoto;
	
	//@Value("20")
	private int capacidadMaximaCarros;
	//@Value("10")
	private int capacidadMaximaMotos;	
	
	private int cantidadMotos = 0;
	private int cantidadCarros = 0;
	
	public Parqueadero(int capacidadMaximaCarros, int capacidadMaximaMotos, SistemaDePersistencia sistemaDePersistencia, IVigilar vigilante){
		this.capacidadMaximaCarros = capacidadMaximaCarros;
		this.capacidadMaximaMotos = capacidadMaximaMotos;
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
	
	public int getCantidadMotos() {
		return cantidadMotos;
	}

	public int getCantidadCarros() {
		return cantidadCarros;
	}

	public void registrarEntradaCarro(Vehiculo vehiculo){
		if(estaCarroEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_ESTA_EN_PARQUEADERO);
		}
		cantidadCarros += 1;
		if(cantidadCarros > capacidadMaximaCarros){
			cantidadCarros -= 1;
			throw new VehiculoException(PARQUEADERO_CARROS_LLENO);
		}
		vigilante.revisarVehiculo(vehiculo);
		sistemaDePersistencia.getRepositorioCarros().agregar(vehiculo);
		Registro registro = new Registro(new Date(), null , vehiculo, 0.0);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroCarro(registro);
	}
	
	public void registrarEntradaMoto(Vehiculo vehiculo){
		if(estaMotoEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_ESTA_EN_PARQUEADERO);
		}
		cantidadMotos += 1;
		if(cantidadMotos > capacidadMaximaMotos){
			cantidadMotos -= 1;
			throw new VehiculoException(PARQUEADERO_MOTOS_LLENO);
		}
		vigilante.revisarVehiculo(vehiculo);
		sistemaDePersistencia.getRepositorioMotos().agregar (vehiculo);
		Registro registro = new Registro(new Date(), null, vehiculo, 0.0);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroMoto(registro);
	}
	/*
	public void registrarSalidaCarro(Vehiculo vehiculo){
		if(!estaCarroEnParqueadero(vehiculo)){
			throw new VehiculoException(VEHICULO_NO_ESTA_EN_PARQUEADERO);
		}
		vigilante.revisarVehiculo(vehiculo);
		Registro registro = new Registro(new Date(), Registro.TIPO_SALIDA , vehiculo);
		sistemaDePersistencia.getRepositorioRegistro().agregarRegistroCarro(registro);
		cantidadCarros -= 1;
	}
	
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
	public void cobrarVehiculo(Vehiculo vehiculo){
		
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
