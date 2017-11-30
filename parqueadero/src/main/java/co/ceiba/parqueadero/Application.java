package co.ceiba.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"co.ceiba.parqueadero.negocio",
				"co.ceiba.parqueadero",
				"co.ceiba.parqueadero.persistencia.repositorio"})
@EntityScan("co.ceiba.parqueadero.persistencia.entidad")
public class Application  extends SpringBootServletInitializer {

    public static void main( String[] args )
    {
    	new Application().configure(new SpringApplicationBuilder(Application.class)).run(args);
    	//SpringApplication.run(Application.class, args);
    }
}
