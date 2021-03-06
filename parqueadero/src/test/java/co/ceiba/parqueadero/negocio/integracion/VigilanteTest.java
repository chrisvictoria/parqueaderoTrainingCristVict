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
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;


public class VigilanteTest {
	
	private static final String PLACA = "123";
	private static final String PLACA_INI_CON_A = "A123";
	private static final double CILINDRAJE_ALTO = 650.0;
	private static final double CILINDRAJE_BAJO = 150.0;
	private static final int CAPCIDAD_MAXIMA_CARROS = 20;
	private static final int CAPACIDAD_MAXIMA_MOTOS = 10;
	private Date miercoles, lunes, fechaEntrada, fechaSalida;
	
	private SistemaDePersistencia sistemaPersistencia;
	private IEstrategiaCobro estrategiaCobroMotos;
	private IEstrategiaCobro estrategiaCobroCarros;
	private IRepositorioRegistro repositorioRegistro;
	private IRepositorioCarros repositorioCarros;
	private IRepositorioMotos repositorioMotos;
	private Parqueadero parqueadero;

	@Before
	public void setUp() throws ParseException {
		sistemaPersistencia = new SistemaDePersistencia();
		repositorioRegistro = sistemaPersistencia.getRepositorioRegistro();
		repositorioCarros = sistemaPersistencia.getRepositorioCarros();
		repositorioMotos = sistemaPersistencia.getRepositorioMotos();
		sistemaPersistencia.iniciar();
		miercoles = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-29");
		lunes = new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-27");
		fechaEntrada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-01-14 09:29:58");
		fechaSalida = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2012-01-15 10:31:48");
		estrategiaCobroMotos = new EstrategiaCobroMoto(500.0, 600.0, 9);
		estrategiaCobroCarros = new EstrategiaCobroCarro(1000.0, 8000.0, 9);
		parqueadero = new Parqueadero(	CAPCIDAD_MAXIMA_CARROS, 
										CAPACIDAD_MAXIMA_MOTOS);
	}
	
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}
	
	@Test
	public void registrarEntradaCarroTest(){		
		Carro carro = new Carro(PLACA);
		Vigilante vigilante = new Vigilante("Pepe",
											estrategiaCobroCarros,
											estrategiaCobroMotos,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		vigilante.registrarCarro(carro);
		vigilante.registrarEntradaCarro(carro, miercoles);
		Assert.assertTrue(vigilante.estaCarroEnParqueadero(carro));
		Assert.assertEquals(1, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarEntradaCarroPlacaIniConAUnMiercolesTest(){		
		Carro carro1 = new Carro(PLACA_INI_CON_A);
		Vigilante vigilante = new Vigilante("Pepe",
											estrategiaCobroCarros,
											estrategiaCobroMotos,
											repositorioRegistro,
											repositorioCarros,
											repositorioMotos,
											parqueadero);
		vigilante.registrarCarro(carro1);
		try {
			vigilante.registrarEntradaCarro(carro1, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_DIA_NO_PERMITIDO, e.getMessage());
		}
	}
	
	@Test
	public void registrarEntradaCarroPlacaIniConAUnLunesODomingoTest(){		
		Carro carro1 = new Carro(PLACA_INI_CON_A);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarCarro(carro1);
		vigilante.registrarEntradaCarro(carro1, lunes);
		Assert.assertTrue(vigilante.estaCarroEnParqueadero(carro1));
		Assert.assertEquals(1, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarEntradaMismoCarroVariasVecesTest(){
		Carro carro = new Carro(PLACA);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarCarro(carro);
		vigilante.registrarEntradaCarro(carro, miercoles);
		try {
			vigilante.registrarEntradaCarro(carro, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
	@Test
	public void capacidadMaximaParqueaderoCarrosTest(){
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		for(int i=1; i <= CAPCIDAD_MAXIMA_CARROS; i++){
			Carro carro = new Carro(Integer.toString(i));
			vigilante.registrarCarro(carro);
			vigilante.registrarEntradaCarro(carro, miercoles);
		}
		try {
			Carro carro = new Carro(Integer.toString(CAPCIDAD_MAXIMA_CARROS+1));
			vigilante.registrarEntradaCarro(carro, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.PARQUEADERO_CARROS_LLENO, e.getMessage());
		}
	}
	
	@Test
	public void registrarSalidaCarroTest(){
		double valorEsperado = 9000.0;
		Carro carro = new Carro(PLACA);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarCarro(carro);
		vigilante.registrarEntradaCarro(carro, fechaEntrada);
		double valor = vigilante.registrarSalidaCarro(carro, fechaSalida);
		Assert.assertEquals(valorEsperado, valor, 0.0);
		Assert.assertFalse(vigilante.estaCarroEnParqueadero(carro));
		Assert.assertEquals(0, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarSalidaMismoCarroVariasVecesTest(){
		Carro carro = new Carro(PLACA);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarCarro(carro);
		vigilante.registrarEntradaCarro(carro, miercoles);
		vigilante.registrarSalidaCarro(carro, miercoles);
		try {
			vigilante.registrarSalidaCarro(carro, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}	
	
	@Test
	public void registrarEntradaMotoTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE_ALTO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		vigilante.registrarEntradaMoto(moto, miercoles);
		Assert.assertTrue(vigilante.estaMotoEnParqueadero(moto));
		Assert.assertEquals(1, parqueadero.getCantidadMotos());
	}
	
	@Test
	public void registrarEntradaMotoPlacaIniConAUnMiercolesTest(){		
		Moto moto = new Moto(PLACA_INI_CON_A, CILINDRAJE_ALTO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		try {
			vigilante.registrarEntradaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_DIA_NO_PERMITIDO, e.getMessage());
		}
	}
	
	@Test
	public void registrarEntradaMotoPlacaIniConAUnLunesODomingoTest(){		
		Moto moto = new Moto(PLACA_INI_CON_A, CILINDRAJE_ALTO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		vigilante.registrarEntradaMoto(moto, lunes);
		Assert.assertTrue(vigilante.estaMotoEnParqueadero(moto));
		Assert.assertEquals(1, parqueadero.getCantidadMotos());
	}
	
	@Test
	public void registrarEntradaMismaMotoVariasVecesTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE_ALTO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		vigilante.registrarEntradaMoto(moto, miercoles);
		try {
			vigilante.registrarEntradaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
	@Test
	public void capacidadMaximaParqueaderoMotosTest(){
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		for(int i=1; i <= CAPACIDAD_MAXIMA_MOTOS; i++){
			Moto moto = new Moto(Integer.toString(i), CILINDRAJE_ALTO);
			vigilante.registrarMoto(moto);
			vigilante.registrarEntradaMoto(moto, miercoles);
		}
		try {
			Moto moto = new Moto(Integer.toString(CAPACIDAD_MAXIMA_MOTOS+1), CILINDRAJE_ALTO);
			vigilante.registrarEntradaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.PARQUEADERO_MOTOS_LLENO, e.getMessage());
		}
	}
	
	@Test
	public void registrarSalidaMotoCilindrajeAltoTest(){
		double valorEsperado = 3100.0;
		Moto moto = new Moto(PLACA, CILINDRAJE_ALTO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		vigilante.registrarEntradaMoto(moto, fechaEntrada);
		double valor = vigilante.registrarSalidaMoto(moto, fechaSalida);
		Assert.assertEquals(valorEsperado, valor, 0.0);
		Assert.assertFalse(vigilante.estaMotoEnParqueadero(moto));
		Assert.assertEquals(0, parqueadero.getCantidadMotos());
	}
	
	@Test
	public void registrarSalidaMotoCilindrajeBajoTest(){
		double valorEsperado = 1100.0;
		Moto moto = new Moto(PLACA, CILINDRAJE_BAJO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		vigilante.registrarEntradaMoto(moto, fechaEntrada);
		double valor = vigilante.registrarSalidaMoto(moto, fechaSalida);
		Assert.assertEquals(valorEsperado, valor, 0.0);
		Assert.assertFalse(vigilante.estaMotoEnParqueadero(moto));
		Assert.assertEquals(0, parqueadero.getCantidadMotos());
	}
	
	@Test
	public void registrarSalidaMismaMotoVariasVecesTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE_ALTO);
		Vigilante vigilante = new Vigilante("Pepe",
				estrategiaCobroCarros,
				estrategiaCobroMotos,
				repositorioRegistro,
				repositorioCarros,
				repositorioMotos,
				parqueadero);
		vigilante.registrarMoto(moto);
		vigilante.registrarEntradaMoto(moto, miercoles);
		vigilante.registrarSalidaMoto(moto, miercoles);
		try {
			vigilante.registrarSalidaMoto(moto, miercoles);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Vigilante.VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
}
