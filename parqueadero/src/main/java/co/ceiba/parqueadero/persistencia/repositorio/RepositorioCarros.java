package co.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.persistencia.builder.CarroBuilder;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;

public class RepositorioCarros implements IRepositorioCarros{
	private static final String PLACA = "placa";
	private static final String CARRO_FIND_BY_PLACA = "Carro.findByPlaca";
	
	private EntityManager entityManager;
	
	public RepositorioCarros(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public Carro obtenerPorPlaca(String placa) {
		CarroEntity carroEntity = obtenerCarroEntityPorPlaca(placa);
		return CarroBuilder.convertirADominio(carroEntity);
	}

	@Override
	public void agregar(Carro carro) {
		CarroEntity carroEntity = buildCarroEntity(carro);
		entityManager.persist(carroEntity);
	}
	
	private CarroEntity buildCarroEntity(Carro carro){
		return CarroBuilder.convertirAEntity(carro);
	}
	
	@SuppressWarnings("rawtypes")
	private CarroEntity obtenerCarroEntityPorPlaca(String placa){
		Query query = entityManager.createNamedQuery(CARRO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (CarroEntity) resultList.get(0) : null;
	}

}
