package co.ceiba.parqueadero.negocio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.ceiba.parqueadero.negocio.Carro;
import testdatabuilder.CarroTestDataBuilder;

public class CarroTest {
	private static final String PLACA = "1234";
	
	@Test
	public void crearCarroTest(){
		CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conPlaca(PLACA);
		Carro carro = carroTestDataBuilder.build();
		assertEquals(PLACA, carro.getPlaca());
	}
}
