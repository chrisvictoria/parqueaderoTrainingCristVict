package co.ceiba.parqueadero.persistencia.sistema;

import javax.persistence.EntityManager;

import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.conexion.ConexionJPA;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioCarros;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioMotos;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioRegistro;

public class SistemaDePersistencia {
	
	public EntityManager entityManager;
	private IRepositorioMotos repositorioMotos;
	private IRepositorioCarros repositorioCarros;
	private IRepositorioRegistro repositorioRegistro;
	
	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
		this.repositorioMotos = new RepositorioMotos(entityManager);
		this.repositorioCarros = new RepositorioCarros(entityManager);
		this.repositorioRegistro = new RepositorioRegistro(entityManager, repositorioCarros, repositorioMotos);
	}
	
	public IRepositorioMotos getRepositorioMotos(){
		return repositorioMotos;
	}
	
	public IRepositorioCarros getRepositorioCarros(){
		return repositorioCarros;
	}
	
	public IRepositorioRegistro getRepositorioRegistro(){
		return repositorioRegistro;
	}
	
	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
