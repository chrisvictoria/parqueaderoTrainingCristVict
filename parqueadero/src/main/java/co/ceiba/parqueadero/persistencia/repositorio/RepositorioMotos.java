package co.ceiba.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.Vehiculo;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.persistencia.builder.MotoBuilder;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;

@Repository
public class RepositorioMotos implements IRepositorioMotos{
	private static final String PLACA = "placa";
	private static final String MOTO_FIND_BY_PLACA = "Moto.findByPlaca";

	@PersistenceContext
	private EntityManager entityManager;
	
	public RepositorioMotos(){}
	
	public RepositorioMotos(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public Moto obtenerPorPlaca(String placa) {
		MotoEntity motoEntity = obtenerMotoEntityPorPlaca(placa);
		return MotoBuilder.convertirADominio(motoEntity);
	}

	@Override
	public void agregar(Vehiculo moto) {
		MotoEntity motoEntity = buildMotoEntity((Moto)moto);
		entityManager.persist(motoEntity);
	}
	
	private MotoEntity buildMotoEntity(Moto moto){
		return MotoBuilder.convertirAEntity(moto);
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public MotoEntity obtenerMotoEntityPorPlaca(String placa){
		Query query = entityManager.createNamedQuery(MOTO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (MotoEntity) resultList.get(0) : null;
	}

	@Override
	public ArrayList<Moto> obtenerTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
