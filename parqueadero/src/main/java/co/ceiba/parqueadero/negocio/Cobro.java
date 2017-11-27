package co.ceiba.parqueadero.negocio;

public abstract class Cobro {
	double cobroHora;
	double cobroDia;
	int valorCobroInicioDia;
	int valorCobroFinalDia;
	
	public Cobro(double cobroHora, double cobroDia, int valorCobroInicioDia, int valorCobroFinalDia) {
		this.cobroHora = cobroHora;
		this.cobroDia = cobroDia;
		this.valorCobroInicioDia = valorCobroInicioDia;
		this.valorCobroFinalDia = valorCobroFinalDia;
	}	
}
