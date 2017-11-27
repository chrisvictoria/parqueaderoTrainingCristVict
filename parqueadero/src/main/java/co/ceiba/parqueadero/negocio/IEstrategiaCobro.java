package co.ceiba.parqueadero.negocio;

public interface IEstrategiaCobro {
	
	double cobrarHora(final double horas);
	double cobrarDia(final double horas);
}
