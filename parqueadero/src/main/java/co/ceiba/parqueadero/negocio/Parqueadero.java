package co.ceiba.parqueadero.negocio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "application")
@Component
public class Parqueadero {

	private int capacidadMaximaCarros;
	
	private int capacidadMaximaMotos;	
	
	private int cantidadMotos = 0;
	private int cantidadCarros = 0;
	
	@Autowired
	public Parqueadero( @Value("20")int capacidadMaximaCarros,
						@Value("10") int capacidadMaximaMotos){
		this.capacidadMaximaCarros = capacidadMaximaCarros;
		this.capacidadMaximaMotos = capacidadMaximaMotos;
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

	public void setCantidadMotos(int cantidadMotos) {
		this.cantidadMotos = cantidadMotos;
	}

	public void setCantidadCarros(int cantidadCarros) {
		this.cantidadCarros = cantidadCarros;
	}
}
