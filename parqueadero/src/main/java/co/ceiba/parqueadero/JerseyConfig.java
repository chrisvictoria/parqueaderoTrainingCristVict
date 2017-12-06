package co.ceiba.parqueadero;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(co.ceiba.parqueadero.servicios.rest.RegistroVehiculosRest.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}

}