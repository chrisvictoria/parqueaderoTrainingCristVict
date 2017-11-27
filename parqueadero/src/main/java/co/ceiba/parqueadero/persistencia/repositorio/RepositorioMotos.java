package co.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.ceiba.parqueadero.negocio.Moto;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.persistencia.builder.MotoBuilder;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;

public class RepositorioMotos implements IRepositorioMotos{
	private static final String PLACA = "placa";
	private static final String MOTO_FIND_BY_PLACA = "Moto.findByPlaca";

	private EntityManager entityManager;
	
	public RepositorioMotos(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public Moto obtenerPorPlaca(String placa) {
		MotoEntity motoEntity = obtenerMotoEntityPorPlaca(placa);
		return MotoBuilder.convertirADominio(motoEntity);
	}

	@Override
	public void agregar(Moto moto) {
		MotoEntity motoEntity = buildMotoEntity(moto);
		entityManager.persist(motoEntity);
	}
	
	private MotoEntity buildMotoEntity(Moto moto){
		return MotoBuilder.convertirAEntity(moto);
	}
	
	@SuppressWarnings("rawtypes")
	private MotoEntity obtenerMotoEntityPorPlaca(String placa){
		Query query = entityManager.createNamedQuery(MOTO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (MotoEntity) resultList.get(0) : null;
	}
}
