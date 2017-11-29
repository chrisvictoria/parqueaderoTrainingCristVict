package co.ceiba.parqueadero.negocio;

import java.util.Date;

public interface IEstrategiaCobro {
	
	double cobrar(Date fechaEntrada, Date fechaSalida, double cilindraje);
}
