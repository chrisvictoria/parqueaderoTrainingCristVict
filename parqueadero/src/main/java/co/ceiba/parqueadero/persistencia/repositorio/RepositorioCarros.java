package co.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.ceiba.parqueadero.negocio.Carro;
import co.ceiba.parqueadero.negocio.Vehiculo;
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
	public void agregar(Vehiculo carro) {
		CarroEntity carroEntity = buildCarroEntity((Carro)carro);
		System.out.println("ACA!!!");
		System.out.println(carroEntity.getPlaca());
		entityManager.persist(carroEntity);
		System.out.println("SALIO");
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
}
