package co.ceiba.parqueadero.servicios.rest;

import java.util.Date;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.servicios.IRegistroVehiculos;

@Named
@Path("/registrar")
public class RegistroVehiculosRest implements IRegistroVehiculos{
	
	private static final String REGISTRO_ALMACENADO = "Vehiculo registrado exitosamente";
	
	@Autowired
	Parqueadero parqueadero;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/carro")
	public String registrarEntradaCarro(Carro carro) {
		try{
			parqueadero.registrarEntradaCarro(carro, new Date());
		} catch (VehiculoException e) {
			return e.getMessage();
		}
		return REGISTRO_ALMACENADO;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/carro")
	public String registrarSalidaCarro(Carro carro) {
		System.out.println("Entro");
		try{
			System.out.println("Entr1o");
			parqueadero.registrarSalidaCarro(carro, new Date());
			System.out.println("Entro2");
		} catch (VehiculoException e) {
			return e.getMessage();
		}
		return REGISTRO_ALMACENADO;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/moto")
	public String registrarEntradaMoto(Moto moto) {
		try{
			parqueadero.registrarEntradaMoto(moto, new Date());
		} catch (VehiculoException e) {
			return e.getMessage();
		}
		return REGISTRO_ALMACENADO;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/moto")
	public String registrarSalidaMoto(Moto moto) {
		return REGISTRO_ALMACENADO;
	}
	
	@GET
	@Path("client")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCustomer() {
		return "HOLA!";
	}
}
