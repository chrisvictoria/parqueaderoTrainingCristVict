package co.ceiba.parqueadero.persistencia.repositorio;

import javax.persistence.EntityManager;

import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.builder.RegistroCarroBuilder;
import co.ceiba.parqueadero.persistencia.builder.RegistroMotoBuilder;
import co.ceiba.parqueadero.persistencia.entidad.RegistroCarroEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroMotoEntity;

public class RepositorioRegistro implements IRepositorioRegistro{

	private EntityManager entityManager;
	
	public RepositorioRegistro(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public void agregarRegistroCarro(Registro registro) {
		RegistroCarroEntity registroCarroEntity = buildRegistroCarroEntity(registro);
		entityManager.persist(registroCarroEntity);
	}

	@Override
	public void agregarRegistroMoto(Registro registro) {
		RegistroMotoEntity registroMotoEntity = buildRegistroMotoEntity(registro);
		entityManager.persist(registroMotoEntity);		
	}	
	
	private RegistroCarroEntity buildRegistroCarroEntity(Registro registro){
		return RegistroCarroBuilder.convertirAEntity(registro);
	}
	
	private RegistroMotoEntity buildRegistroMotoEntity(Registro registro){
		return RegistroMotoBuilder.convertirAEntity(registro);
	}
}
