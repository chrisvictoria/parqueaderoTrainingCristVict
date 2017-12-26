package co.ceiba.parqueadero.negocio.unitaria;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.EstrategiaCobroCarro;
import co.ceiba.parqueadero.negocio.EstrategiaCobroMoto;
import co.ceiba.parqueadero.negocio.IEstrategiaCobro;
import co.ceiba.parqueadero.negocio.IVigilar;
import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.negocio.Vigilante;
import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioCarros;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioMotos;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioRegistro;
import testdatabuilder.CarroTestDataBuilder;
import testdatabuilder.MotoTestDataBuilder;

public class VigilanteTest {
	private static final int CAPCIDAD_MAXIMA_CARROS = 20;
	private static final int CAPACIDAD_MAXIMA_MOTOS = 10;
	private static final String PLACA = "1234";
	private static final double CILINDRAJE_ALTO = 650.0;
	private static final double CILINDRAJE_BAJO = 150.0;

	IRepositorioMotos repositorioMotos;
	IRepositorioCarros repositorioCarros;
	IRepositorioRegistro repositorioRegistro;
	IEstrategiaCobro estrategiaCobroCarro;
	IEstrategiaCobro estrategiaCobroMoto;
	Parqueadero parqueadero;
	private Date fechaEntrada, fechaSalida;

	@Before
	public void setUp() throws ParseException {
		repositorioMotos = mock(RepositorioMotos.class);
		repositorioCarros = mock(RepositorioCarros.class);
		repositorioRegistro = mock(RepositorioRegistro.class);
		estrategiaCobroCarro = new EstrategiaCobroCarro(1000.0, 8000.0, 9);
		estrategiaCobroMoto = new EstrategiaCobroMoto(500.0, 600.0, 9);
		parqueadero = new Parqueadero(	CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS);
		fechaEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-01-14 09:29:58");
		fechaSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-01-15 10:31:48");
	}

	@After
	public void tearDown() {
		
	}

	public void revisarPlacaPermisoTest() throws ParseException {
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		try {
			vigilante.revisarPlacaPermiso(new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-29"), "A123");
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_DIA_NO_PERMITIDO, e.getMessage());
		}
	}

	@Test
	public void cobrarMotoCilindrajeBajoTest(){
		double valorEsperado = 1100.0;
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		Moto moto = new Moto(PLACA, CILINDRAJE_BAJO);
		Registro registro = new Registro(fechaEntrada, fechaSalida, moto, 0.0);
		double valor = vigilante.cobrarMoto(registro);
		Assert.assertEquals(valorEsperado, valor, 0.0);
	}

	@Test
	public void cobrarCarroTest(){
		double valorEsperado = 9000.0;
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		Carro carro = new Carro(PLACA);
		Registro registro = new Registro(fechaEntrada, fechaSalida, carro, 0.0);
		double valor = vigilante.cobrarCarro(registro);
		Assert.assertEquals(valorEsperado, valor, 0.0);
	}

	@Test
	public void obtenerCarrosTest(){
		
		ArrayList<Carro> carrosEsperado = new ArrayList<>();
		for(int i = 0; i<2;i++){
			CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conPlaca(PLACA);
			Carro carro = carroTestDataBuilder.build();
			carrosEsperado.add(carro);
		}
		when(repositorioCarros.obtenerTodos()).thenReturn(carrosEsperado);
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

	@Test
	public void obtenerMotosTest(){
		ArrayList<Moto> motosEsperado = new ArrayList<>();
		for(int i = 0; i<2;i++){
			MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().conPlaca(PLACA);
			Moto moto = motoTestDataBuilder.build();
			motosEsperado.add(moto);
		}
		when(repositorioMotos.obtenerTodos()).thenReturn(motosEsperado);
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		ArrayList<Moto> motosRetornado = vigilante.obtenerMotos();
		assertEquals(motosEsperado, motosRetornado);
	}

	@Test
	public void obtenerMotosEnParqueaderoTest(){
		ArrayList<Registro> registrosEsperado = new ArrayList<>();
		for(int i = 0; i<2;i++){
			MotoTestDataBuilder motoTestDataBuilder = new MotoTestDataBuilder().conPlaca(PLACA);
			Moto moto = motoTestDataBuilder.build();
			Registro registro = new Registro(fechaEntrada, fechaSalida, moto, 0.0);
			registrosEsperado.add(registro);
		}
		when(repositorioRegistro.obtenerRegistrosMotos()).thenReturn(registrosEsperado);
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		ArrayList<Registro> registrosRetornado = vigilante.obtenerMotosEnParqueadero();
		assertEquals(registrosEsperado, registrosRetornado);
	}

	@Test
	public void obtenerCarrosEnParqueaderoTest(){
		ArrayList<Registro> registrosEsperado = new ArrayList<>();
		for(int i = 0; i<2;i++){
			CarroTestDataBuilder carroTestDataBuilder = new CarroTestDataBuilder().conPlaca(PLACA);
			Carro carro = carroTestDataBuilder.build();
			Registro registro = new Registro(fechaEntrada, fechaSalida, carro, 0.0);
			registrosEsperado.add(registro);
		}
		when(repositorioRegistro.obtenerRegistrosCarros()).thenReturn(registrosEsperado);
		IVigilar vigilante = new Vigilante("Pepe",
											estrategiaCobroCarro,
											estrategiaCobroMoto,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		ArrayList<Registro> registrosRetornado = vigilante.obtenerCarrosEnParqueadero();
		assertEquals(registrosEsperado, registrosRetornado);
	}
}
