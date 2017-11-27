package co.ceiba.parqueadero.persistencia.sistema;

import javax.persistence.EntityManager;

import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.persistencia.conexion.ConexionJPA;

public class SistemaDePersistencia {
	
	private EntityManager entityManager;
	private IRepositorioMotos repositorioMotos;
	private IRepositorioCarros repositorioCarros;
	
	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}
	
	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
