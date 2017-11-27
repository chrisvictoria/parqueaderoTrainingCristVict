package co.ceiba.parqueadero.servicios;

import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;

@Named
@Path("registrar/")
public class RegistroVehiculosRest implements IRegistroVehiculos{

	@POST
	@Path("carro/")
	public void registrarCarro(Carro carro) {
		// TODO Auto-generated method stub
		
	}
	
	@POST
	@Path("moto/")
	public void registrarMoto(Moto moto) {
		// TODO Auto-generated method stub
		
	}

}
