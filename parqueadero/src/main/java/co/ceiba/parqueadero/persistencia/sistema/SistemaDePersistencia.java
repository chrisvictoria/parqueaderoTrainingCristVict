package co.ceiba.parqueadero.persistencia.sistema;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.conexion.ConexionJPA;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioCarros;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioMotos;
import co.ceiba.parqueadero.persistencia.repositorio.RepositorioRegistro;

@Scope(value = "application")
@Component
public class SistemaDePersistencia {
	
	public EntityManager entityManager;
	
	public SistemaDePersistencia() {
		this.entityManager = new ConexionJPA().createEntityManager();
	}
	
	public IRepositorioMotos getRepositorioMotos(){
		return new RepositorioMotos(entityManager);
	}
	
	public IRepositorioCarros getRepositorioCarros(){
		return new RepositorioCarros(entityManager);
	}
	
	public IRepositorioRegistro getRepositorioRegistro(){
		return  new RepositorioRegistro(entityManager, getRepositorioCarros(), getRepositorioMotos());
	}
	
	public void iniciar() {
		entityManager.getTransaction().begin();
	}

	public void terminar() {
		entityManager.getTransaction().commit();
	}
}
