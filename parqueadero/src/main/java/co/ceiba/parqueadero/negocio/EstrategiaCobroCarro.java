package co.ceiba.parqueadero.negocio;

public class EstrategiaCobroCarro extends Cobro implements IEstrategiaCobro{

	public EstrategiaCobroCarro(double cobroHora, double cobroDia, int valorCobroInicioDia, int valorCobroFinalDia) {
		super(cobroHora, cobroDia, valorCobroInicioDia, valorCobroFinalDia);
	}
	
	public double cobrarHora(double horas) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double cobrarDia(double horas) {
		// TODO Auto-generated method stub
		return 0;
	}

}
