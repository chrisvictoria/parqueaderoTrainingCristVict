package co.ceiba.parqueadero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"co.ceiba.parqueadero.negocio","co.ceiba.parqueadero","co.ceiba.parqueadero.persistencia.sistema"})
@EntityScan("co.ceiba.parqueadero.persistencia.entidad")
public class Application 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
    }
}
