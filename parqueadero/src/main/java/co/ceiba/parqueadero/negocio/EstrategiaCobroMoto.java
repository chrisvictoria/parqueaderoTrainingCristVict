package co.ceiba.parqueadero.negocio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "application")
@Component
public class EstrategiaCobroMoto extends Cobro implements IEstrategiaCobro{

	public EstrategiaCobroMoto(@Value("500.0")double cobroHora,@Value("600.0") double cobroDia,@Value("9") int cantidadHorasInicioDia) {
		super(cobroHora, cobroDia, cantidadHorasInicioDia);
	}
	
	@Override
	public double cobrar(Date fechaEntrada, Date fechaSalida, double cilindraje) {
		long diferencia = fechaSalida.getTime() - fechaEntrada.getTime();
		long horas = diferencia / (60 * 60 * 1000) % 24;
		long dias = diferencia / (24 * 60 * 60 * 1000);
		
		double valorTotal = 0;
		if(dias>0){
			valorTotal = dias*cobroDia + horas*cobroHora;
		}else{
			if(horas < cantidadHorasInicioDia){
				//cobrar por horas
				valorTotal = horas*cobroHora;
			}else{
				//cobrar por el dia
				valorTotal = cobroDia;
			}
		}
		return cilindraje >= 500.0 ? valorTotal+2000.0 : valorTotal;
	}

}
