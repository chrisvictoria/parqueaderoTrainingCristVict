package co.ceiba.parqueadero.negocio;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;

@Scope(value = "application")
@Component
public class Vigilante implements IVigilar{
	
	public static final String VEHICULO_DIA_NO_PERMITIDO = "El vehiculo no tiene permitido entrar al parqueadero porque la placa comienza con A.";
	@Autowired
	private IEstrategiaCobro estrategiaCobroCarro;
	@Autowired
	private IEstrategiaCobro estrategiaCobroMoto;
	
	String nombre;
	
	public Vigilante(@Value("Pepe") String nombre,
					IEstrategiaCobro estrategiaCobroCarro, 
					IEstrategiaCobro estrategiaCobroMoto) {
		this.nombre = nombre;
		this.estrategiaCobroCarro = estrategiaCobroCarro;
		this.estrategiaCobroMoto = estrategiaCobroMoto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void revisarVehiculo(Vehiculo vehiculo) {
		System.out.println("Vigilante revisa carro");
		System.out.println("Vigilante dice todo esta bien");
	}

	public void revisarPlacaPermiso(Date fechaEntrada, String placa){
		Calendar iniCal = Calendar.getInstance();
		iniCal.setTime(fechaEntrada);
		int diaSemana = iniCal.get(Calendar.DAY_OF_WEEK);
		if(placa.toLowerCase().substring(0,1).equals("a")  && (diaSemana != Calendar.SUNDAY && diaSemana != Calendar.MONDAY)){
			throw new VehiculoException(VEHICULO_DIA_NO_PERMITIDO);
		}
	}
	
	public double cobrarMoto(Registro registro){
		Moto moto = (Moto)registro.getVehiculo();
		double valor = estrategiaCobroMoto.cobrar(registro.getFechaEntrada(), registro.getFechaSalida(), moto.getCilindraje());
		return valor;
	}
	
	public double cobrarCarro(Registro registro){
		double valor = estrategiaCobroCarro.cobrar(registro.getFechaEntrada(), registro.getFechaSalida(), 0.0);
		return valor;
	}
}
