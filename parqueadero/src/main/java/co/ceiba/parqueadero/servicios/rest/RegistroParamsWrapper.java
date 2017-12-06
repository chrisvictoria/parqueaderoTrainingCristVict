package co.ceiba.parqueadero.servicios.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;

public class RegistroParamsWrapper{
	
	 String fechaEntrada;
	 String fechaSalida;
	 String placa;
	 double cilindraje;
	 String tipo;
	
	RegistroParamsWrapper(){
	}

	public RegistroParamsWrapper(String fechaEntrada, String fechaSalida, String placa, double cilindraje,
			String tipo) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
	}



	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Date getDateEntrada() throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaEntrada);
		//Instant instant = Instant.parse(fechaEntrada);
		//return Date.from(instant);
	}
	
	public Date getDateSalida() throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaSalida);
		//Instant instant = Instant.parse(fechaSalida);
		//return Date.from(instant);
	}
	
	public Carro getCarro(){
		Carro carro = new Carro(placa);
		return carro;
	}
	
	public Moto getMoto(){
		Moto moto = new Moto(placa, cilindraje);
		return moto;
	}
}
