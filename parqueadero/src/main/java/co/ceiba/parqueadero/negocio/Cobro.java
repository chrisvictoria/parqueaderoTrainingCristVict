package co.ceiba.parqueadero.negocio;

public abstract class Cobro {
	double cobroHora;
	double cobroDia;
	int cantidadHorasInicioDia;
	
	public Cobro(double cobroHora, double cobroDia, int valorCobroInicioDia) {
		this.cobroHora = cobroHora;
		this.cobroDia = cobroDia;
		this.cantidadHorasInicioDia = valorCobroInicioDia;
	}	
}
