package co.ceiba.parqueadero.negocio;

//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;

//@Scope(value = "application")
//@Component
public class Vigilante extends Persona implements IVigilar{
	
	public Vigilante(String nombre) {
		super(nombre);
	}

	public void revisarVehiculo(Vehiculo vehiculo) {
		System.out.println("Vigilante revisa carro");
		System.out.println("Vigilante dice todo esta bien");
	}
}
