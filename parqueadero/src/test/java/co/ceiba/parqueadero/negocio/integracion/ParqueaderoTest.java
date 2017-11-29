package co.ceiba.parqueadero.negocio.integracion;

//import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.Vigilante;
import co.ceiba.parqueadero.negocio.excepcion.VehiculoException;
import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;


public class ParqueaderoTest {
	
	private static final String PLACA = "123";
	private static final double CILINDRAJE = 650.0;
	private static final int CAPCIDAD_MAXIMA_CARROS = 20;
	private static final int CAPACIDAD_MAXIMA_MOTOS = 10;	
	
	private SistemaDePersistencia sistemaPersistencia;
	private Vigilante  vigilante;

	@Before
	public void setUp() {		
		sistemaPersistencia = new SistemaDePersistencia();
		vigilante = new Vigilante("Pepe");
		sistemaPersistencia.iniciar();
	}
	
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}
	
	@Test
	public void registrarEntradaCarroTest(){		
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaCarro(carro);
		Assert.assertTrue(parqueadero.estaCarroEnParqueadero(carro));
		Assert.assertEquals(1, parqueadero.getCantidadCarros());
	}
	/*
	@Test
	public void registrarSalidaCarroTest(){
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaCarro(carro);
		parqueadero.registrarSalidaCarro(carro);
		Assert.assertFalse(parqueadero.estaCarroEnParqueadero(carro));
		Assert.assertEquals(0, parqueadero.getCantidadCarros());
	}
	
	@Test
	public void registrarEntradaMismoCarroVariasVecesTest(){
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaCarro(carro);
		try {
			parqueadero.registrarEntradaCarro(carro);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
	@Test
	public void registrarSalidaMismoCarroVariasVecesTest(){
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaCarro(carro);
		parqueadero.registrarSalidaCarro(carro);
		try {
			parqueadero.registrarSalidaCarro(carro);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
	@Test
	public void capacidadMaximaParqueaderoCarrosTest(){
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		for(int i=1; i <= CAPCIDAD_MAXIMA_CARROS; i++){
			Carro carro = new Carro(Integer.toString(i));
			parqueadero.registrarEntradaCarro(carro);
		}
		try {
			Carro carro = new Carro(Integer.toString(CAPCIDAD_MAXIMA_CARROS+1));
			parqueadero.registrarEntradaCarro(carro);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.PARQUEADERO_CARROS_LLENO, e.getMessage());
		}
	}
	*/
	@Test
	public void registrarEntradaMotoTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaMoto(moto);
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
	
	@Test
	public void registrarEntradaMismaMotoVariasVecesTest(){
		Moto moto = new Moto(PLACA, CILINDRAJE);
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaMoto(moto);
		try {
			parqueadero.registrarEntradaMoto(moto);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.VEHICULO_ESTA_EN_PARQUEADERO, e.getMessage());
		}
	}
	
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
	
	@Test
	public void capacidadMaximaParqueaderoMotosTest(){
		Parqueadero parqueadero = new Parqueadero(CAPCIDAD_MAXIMA_CARROS, CAPACIDAD_MAXIMA_MOTOS, sistemaPersistencia, vigilante);
		for(int i=1; i <= CAPACIDAD_MAXIMA_MOTOS; i++){
			Moto moto = new Moto(Integer.toString(i), CILINDRAJE);
			parqueadero.registrarEntradaMoto(moto);
		}
		try {
			Moto moto = new Moto(Integer.toString(CAPACIDAD_MAXIMA_MOTOS+1), CILINDRAJE);
			parqueadero.registrarEntradaMoto(moto);
			fail();
		} catch (VehiculoException e) {
			Assert.assertEquals(Parqueadero.PARQUEADERO_MOTOS_LLENO, e.getMessage());
		}
	}*/
}
