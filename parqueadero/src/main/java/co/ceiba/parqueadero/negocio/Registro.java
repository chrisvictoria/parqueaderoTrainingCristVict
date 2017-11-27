package co.ceiba.parqueadero.negocio;

import java.util.Date;

public class Registro {
	
	public static final String TIPO_ENTRADA = "ENTRADA";
	public static final String TIPO_SALIDA = "SALIDA";
	
	private Date fecha;
	private String tipo;
	private Vehiculo vehiculo;
	
	public Registro(Date fecha, String tipo, Vehiculo vehiculo) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.vehiculo = vehiculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
}
