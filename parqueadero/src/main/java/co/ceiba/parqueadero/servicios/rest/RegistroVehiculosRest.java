package co.ceiba.parqueadero.servicios.rest;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;

@Component
@Path("/registrar")
public class RegistroVehiculosRest{
	
	private static final String REGISTRO_ALMACENADO = "Vehiculo registrado exitosamente.";
	
	@Autowired
	Parqueadero parqueadero;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/entradaVehiculo")
	public String registrarEntradaVehiculo(RegistroParamsWrapper registroParamsWrapper) {
		try{
			if(registroParamsWrapper.getTipo().equals("Carro")){
				Carro carro = registroParamsWrapper.getCarro();
				parqueadero.registrarCarro(carro);
				parqueadero.registrarEntradaCarro(carro, registroParamsWrapper.getDateEntrada());
			}else if(registroParamsWrapper.getTipo().equals("Moto")){
				Moto moto = registroParamsWrapper.getMoto();
				parqueadero.registrarMoto(moto);
				parqueadero.registrarEntradaMoto(moto, registroParamsWrapper.getDateEntrada());
			}
		} catch (VehiculoException e) {
			return e.getMessage();
		}catch (ParseException e) {
			return e.getMessage();
		}
		return REGISTRO_ALMACENADO;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/salidaVehiculo")
	public String registrarSalidaVehiculo(RegistroParamsWrapper registroParamsWrapper) {
		double varlor = 0.0;
		try{
			if(registroParamsWrapper.getTipo().equals("Carro")){
				Carro carro = registroParamsWrapper.getCarro();
				varlor = parqueadero.registrarSalidaCarro(carro, registroParamsWrapper.getDateSalida());
			}else if(registroParamsWrapper.getTipo().equals("Moto")){
				Moto moto = registroParamsWrapper.getMoto();
				varlor = parqueadero.registrarSalidaMoto(moto, registroParamsWrapper.getDateSalida());
			}			
		} catch (VehiculoException e) {
			return e.getMessage();
		}catch (ParseException e) {
			return e.getMessage();
		}
		return REGISTRO_ALMACENADO+" Valor a pagar:"+ varlor;
	}
	
	@GET
	@Path("getCarrosEnParqueadero")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registro> getCarrosEnParqueadero() {
		return parqueadero.obtenerCarrosEnParqueadero();
	}
	
	@GET
	@Path("getMotosEnParqueadero")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Registro> getMotosEnParqueadero() {
		return parqueadero.obtenerMotosEnParqueadero();
	}
	
	@GET
	@Path("getCarros")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carro> getCarros() {
		return parqueadero.obtenerCarros();
	}
	
	@GET
	@Path("getMotos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Moto> getMotos() {
		return parqueadero.obtenerMotos();
	}
}
