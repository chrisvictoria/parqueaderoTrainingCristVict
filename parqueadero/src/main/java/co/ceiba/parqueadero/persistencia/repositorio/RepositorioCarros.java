package co.ceiba.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Vehiculo;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.persistencia.builder.CarroBuilder;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;

@Repository
public class RepositorioCarros implements IRepositorioCarros{
	private static final String PLACA = "placa";
	private static final String CARRO_FIND_BY_PLACA = "Carro.findByPlaca";
	private static final String CARRO_ALL = "Carro.all";
	 
	@PersistenceContext
	private EntityManager entityManager;
	
	public RepositorioCarros(){}
	
	public RepositorioCarros(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	@Override
	public Carro obtenerPorPlaca(String placa) {
		CarroEntity carroEntity = obtenerCarroEntityPorPlaca(placa);
		return CarroBuilder.convertirADominio(carroEntity);
	}

	@Override
	public void agregar(Vehiculo carro) {
		CarroEntity carroEntity = buildCarroEntity((Carro)carro);
		entityManager.persist(carroEntity);
	}
	
	private CarroEntity buildCarroEntity(Carro carro){
		return CarroBuilder.convertirAEntity(carro);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public CarroEntity obtenerCarroEntityPorPlaca(String placa){
		Query query = entityManager.createNamedQuery(CARRO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);

		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (CarroEntity) resultList.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Carro> obtenerTodos() {
		Query query = entityManager.createNamedQuery(CARRO_ALL);
		List<CarroEntity> resultList = query.getResultList();
		return CarroBuilder.convertirListaADominio(resultList);
	}
}
