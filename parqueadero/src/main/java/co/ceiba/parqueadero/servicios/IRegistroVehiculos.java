package co.ceiba.parqueadero.servicios;

import co.ceiba.parqueadero.servicios.rest.RegistroParamsWrapper;

public interface IRegistroVehiculos {
	public String registrarEntradaVehiculo(RegistroParamsWrapper registroParamsWrapper);
	//public String registrarSalidaCarro(Registro registro);
	//public String registrarEntradaMoto(Registro registro);
	//public String registrarSalidaMoto(Registro registro);
}
