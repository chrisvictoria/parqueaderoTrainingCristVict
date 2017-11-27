package co.ceiba.parqueadero.negocio;

import java.util.Date;

public class Registro {
	
	private Date fechaEntrada;
	private Date fechaSalida;
	private Vehiculo vehiculo;
	
	public Registro(Date fechaEntrada, Date fechaSalida, Vehiculo vehiculo) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.vehiculo = vehiculo;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
}
