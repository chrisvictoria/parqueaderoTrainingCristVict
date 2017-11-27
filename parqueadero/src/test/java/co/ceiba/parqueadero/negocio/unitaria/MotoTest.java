package co.ceiba.parqueadero.negocio.unitaria;

import static org.junit.Assert.*;

import org.junit.Test;

import co.ceiba.parqueadero.negocio.Moto;
import testdatabuilder.MotoTestDataBuilder;

public class MotoTest {

	private static final String PLACA = "1234";
	private static final double CILINDRAJE = 600.00;
	
	@Test
	public void crearMotoTest(){
		MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().conPlaca(PLACA).conCilindraje(CILINDRAJE);
		Moto moto = motoTestDataBuilder.build();
		assertEquals(PLACA, moto.getPlaca());
		assertEquals(CILINDRAJE, moto.getCilindraje(), 0.01);
	}
}
