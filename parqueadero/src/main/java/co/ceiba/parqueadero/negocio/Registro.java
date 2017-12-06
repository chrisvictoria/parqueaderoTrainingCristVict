package co.ceiba.parqueadero.negocio;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Registro {
	@XmlJavaTypeAdapter(MyDateAdapter.class)
	private Date fechaEntrada;
	@XmlJavaTypeAdapter(MyDateAdapter.class)
	private Date fechaSalida;
	private double valor;
	private Vehiculo vehiculo;
	
	public Registro(){
	}
	
	public Registro(Date fechaEntrada, Date fechaSalida, Vehiculo vehiculo, double valor) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.vehiculo = vehiculo;
		this.valor = valor;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}	
}
