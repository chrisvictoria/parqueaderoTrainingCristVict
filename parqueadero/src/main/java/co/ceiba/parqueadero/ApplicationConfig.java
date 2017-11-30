package co.ceiba.parqueadero;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	/*@Named
	static class JerseyConfig extends ResourceConfig {
		public JerseyConfig() {
			this.packages("co.ceiba.parqueadero.servicios.rest");
		}
	}*/
	
	 @Bean
	 ServletRegistrationBean h2servletRegistration(){
	         ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
	         registrationBean.addUrlMappings("/console/*");
	         return registrationBean;
	 }
}
