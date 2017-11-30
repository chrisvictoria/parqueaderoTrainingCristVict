package co.ceiba.parqueadero;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(co.ceiba.parqueadero.servicios.rest.RegistroVehiculosRest.class);
	}

}