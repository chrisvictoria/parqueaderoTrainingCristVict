package co.ceiba.parqueadero.negocio.unitaria;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.EstrategiaCobroCarro;
import co.ceiba.parqueadero.negocio.EstrategiaCobroMoto;
import co.ceiba.parqueadero.negocio.IEstrategiaCobro;
import co.ceiba.parqueadero.negocio.IVigilar;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.Vigilante;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioCarros;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioMotos;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioRegistro;
import testdatabuilder.CarroTestDataBuilder;

public class VigilanteTest {
	private static final int CAPCIDAD_MAXIMA_CARROS = 20;
	private static final int CAPACIDAD_MAXIMA_MOTOS = 10;
	private static final String PLACA = "1234";

	@Test
	public void obtenerCarrosTest(){
		IRepositorioMotos repositorioMotos = mock(RepositorioMotos.class);
		IRepositorioCarros repositorioCarros = mock(RepositorioCarros.class);
		IRepositorioRegistro repositorioRegistro = mock(RepositorioRegistro.class);
		IEstrategiaCobro estrategiaCobroCarro = new EstrategiaCobroCarro(1000.0, 8000.0, 9);
		IEstrategiaCobro estrategiaCobroMoto = new EstrategiaCobroMoto(500.0, 600.0, 9);
		ArrayList<Carro> carrosEsperado = new ArrayList<>();
		for(int i = 0; i<2;i++){
			CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conPlaca(PLACA);
			Carro carro = carroTestDataBuilder.build();
			carrosEsperado.add(carro);
		}
		when(repositorioCarros.obtenerTodos()).thenReturn(carrosEsperado);
		Parqueadero parqueadero = new Parqueadero(	CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS);
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);		
		ArrayList<Carro> carrosRetornado = vigilante.obtenerCarros();
		assertEquals(carrosEsperado, carrosRetornado);
	}
}
