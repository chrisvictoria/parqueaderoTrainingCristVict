package co.ceiba.parqueadero.negocio.integracion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Parqueadero;
import co.ceiba.parqueadero.negocio.Vigilante;
import co.ceiba.parqueadero.persistencia.sistema.SistemaDePersistencia;
import junit.framework.Assert;

public class ParqueaderoTest {
	private static final String PLACA = "123";
	private SistemaDePersistencia sistemaPersistencia;

	@Before
	public void setUp() {		
		sistemaPersistencia = new SistemaDePersistencia();
		sistemaPersistencia.iniciar();
	}
	/*
	@After
	public void tearDown() {
		sistemaPersistencia.terminar();
	}
	*/
	@Test
	public void registrarEntradaCarroTest(){
		sistemaPersistencia = new SistemaDePersistencia();
		Vigilante  vigilante = new Vigilante("Pepe");
		Carro carro = new Carro(PLACA);
		Parqueadero parqueadero = new Parqueadero(sistemaPersistencia, vigilante);
		parqueadero.registrarEntradaCarro(carro);
		Assert.assertEquals(1, parqueadero.getCantidadCarros());
	}
}
