package co.ceiba.parqueadero.negocio;

import java.util.Date;

public class Registro {
	public static final String TIPO_SALIDA = "Salida";
	public static String TIPO_ENTRADA = "Entrada";
	
	private Date fechaEntrada;
	private Date fechaSalida;
	private Vehiculo vehiculo;
	private String tipo;
	
	public Registro(Date fechaEntrada, Date fechaSalida, Vehiculo vehiculo, String tipo) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.vehiculo = vehiculo;
		this.tipo = tipo;
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
	
	public String getTipo() {
		return tipo;
	}
}
