package co.ceiba.parqueadero.negocio.integracion;

import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.EstrategiaCobroCarro;
import co.ceiba.parqueadero.negocio.EstrategiaCobroMoto;
import co.ceiba.parqueadero.negocio.IEstrategiaCobro;
import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.Vigilante;
import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;


public class ParqueaderoTest {
	
	private static final String PLACA = "123";
	private static final String PLACA_INI_CON_A = "A123";
	private static final double CILINDRAJE = 650.0;
	private static final int CAPCIDAD_MAXIMA_CARROS = 20;
	private static final int CAPACIDAD_MAXIMA_MOTOS = 10;
	private Date miercoles, lunes, fechaEntrada, fechaSalida;
	
	private SistemaDePersistencia sistemaPersistencia;
	private Vigilante  vigilante;
	private IEstrategiaCobro estrategiaCoboMotos;
	private IEstrategiaCobro estrategiaCoboCarros;

	@Before
	public void setUp() throws ParseException {		
		sistemaPersistencia = new SistemaDePersistencia();
		vigilante = new Vigilante("Pepe");
		sistemaPersistencia.iniciar();
		miercoles = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-29");
		lunes = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-27");
		fechaEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-01-14 09:29:58");
		fechaSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-01-15 10:31:48");
		estrategiaCoboMotos = new EstrategiaCobroMoto(500.0, 600.0, 9);
		estrategiaCoboCarros = new EstrategiaCobroCarro(1000.0, 8000.0, 9);
	}
	
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}
	
	@Test
	public void registrarEntradaCarroTest(){		
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaCarro(carro, miercoles);
		Assert.assertTrue(parqueadero.estaCarroEnParqueadero(carro));
		Assert.assertEquals(1, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarEntradaCarroPlacaIniConAUnMiercolesTest(){		
		Carro carro1 = new Carro(PLACA_INI_CON_A);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		try {
			parqueadero.registrarEntradaCarro(carro1, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_DIA_NO_PERMITIDO, e.getMessage());
		}
	}
	
	@Test
	public void registrarEntradaCarroPlacaIniConAUnLunesODomingoTest(){		
		Carro carro1 = new Carro(PLACA_INI_CON_A);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaCarro(carro1, lunes);
		Assert.assertTrue(parqueadero.estaCarroEnParqueadero(carro1));
		Assert.assertEquals(1, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarEntradaMismoCarroVariasVecesTest(){
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaCarro(carro, miercoles);
		try {
			parqueadero.registrarEntradaCarro(carro, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
	@Test
	public void capacidadMaximaParqueaderoCarrosTest(){
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		for(int i=1; i <= CAPCIDAD_MAXIMA_CARROS; i++){
			Carro carro = new Carro(Integer.toString(i));
			parqueadero.registrarEntradaCarro(carro, miercoles);
		}
		try {
			Carro carro = new Carro(Integer.toString(CAPCIDAD_MAXIMA_CARROS+1));
			parqueadero.registrarEntradaCarro(carro, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.PARQUEADERO_CARROS_LLENO, e.getMessage());
		}
	}
	
	@Test
	public void registrarSalidaCarroTest(){
		double valorEsperado = 9000.0;
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaCarro(carro, fechaEntrada);
		double valor = parqueadero.registrarSalidaCarro(carro, fechaSalida);
		Assert.assertEquals(valorEsperado, valor, 0.0);
		Assert.assertFalse(parqueadero.estaCarroEnParqueadero(carro));
		Assert.assertEquals(0, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarSalidaMismoCarroVariasVecesTest(){
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaCarro(carro, miercoles);
		parqueadero.registrarSalidaCarro(carro, miercoles);
		try {
			parqueadero.registrarSalidaCarro(carro, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
	
	@Test
	public void registrarEntradaMotoTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaMoto(moto, miercoles);
		Assert.assertTrue(parqueadero.estaMotoEnParqueadero(moto));
		Assert.assertEquals(1, parqueadero.getCantidadMotos());
	}
	
	@Test
	public void registrarEntradaMotoPlacaIniConAUnMiercolesTest(){		
		Moto moto = new Moto(PLACA_INI_CON_A, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		try {
			parqueadero.registrarEntradaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_DIA_NO_PERMITIDO, e.getMessage());
		}
	}
	
	@Test
	public void registrarEntradaMotoPlacaIniConAUnLunesODomingoTest(){		
		Moto moto = new Moto(PLACA_INI_CON_A, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaMoto(moto, lunes);
		Assert.assertTrue(parqueadero.estaMotoEnParqueadero(moto));
		Assert.assertEquals(1, parqueadero.getCantidadMotos());
	}
	/*
	@Test
	public void registrarSalidaMotoTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaMoto(moto);
		parqueadero.registrarSalidaMoto(moto);
		Assert.assertFalse(parqueadero.estaMotoEnParqueadero(moto));
		Assert.assertEquals(0, parqueadero.getCantidadMotos());
	}
	*/
	@Test
	public void registrarEntradaMismaMotoVariasVecesTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		parqueadero.registrarEntradaMoto(moto, miercoles);
		try {
			parqueadero.registrarEntradaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	/*
	@Test
	public void registrarSalidaMismaMotoVariasVecesTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaMoto(moto);
		parqueadero.registrarSalidaMoto(moto);
		try {
			parqueadero.registrarSalidaMoto(moto);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	*/
	@Test
	public void capacidadMaximaParqueaderoMotosTest(){
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante, estrategiaCoboCarros, estrategiaCoboMotos);
		for(int i=1; i <= CAPACIDAD_MAXIMA_MOTOS; i++){
			Moto moto = new Moto(Integer.toString(i), CILINDRAJE);
			parqueadero.registrarEntradaMoto(moto, miercoles);
		}
		try {
			Moto moto = new Moto(Integer.toString(CAPACIDAD_MAXIMA_MOTOS+1), CILINDRAJE);
			parqueadero.registrarEntradaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.PARQUEADERO_MOTOS_LLENO, e.getMessage());
		}
	}
}
