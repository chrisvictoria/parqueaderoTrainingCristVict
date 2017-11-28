package co.ceiba.parqueadero.persistencia.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import co.ceiba.parqueadero.negocio.Registro;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioCarros;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioMotos;
import co.ceiba.parqueadero.negocio.repositorio.IRepositorioRegistro;
import co.ceiba.parqueadero.persistencia.builder.RegistroCarroBuilder;
import co.ceiba.parqueadero.persistencia.builder.RegistroMotoBuilder;
import co.ceiba.parqueadero.persistencia.entidad.CarroEntity;
import co.ceiba.parqueadero.persistencia.entidad.MotoEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroCarroEntity;
import co.ceiba.parqueadero.persistencia.entidad.RegistroMotoEntity;

public class RepositorioRegistro implements IRepositorioRegistro{
	
	private static final String CARRO_FIND_BY_PLACA = "RegistroCarro.findByPlaca";
	private static final String MOTO_FIND_BY_PLACA = "RegistroMoto.findByPlaca";
	private static final String PLACA = "placa";

	private EntityManager entityManager;
	private IRepositorioCarros repositorioCarros;
	private IRepositorioMotos repositorioMotos;
	
	public RepositorioRegistro(EntityManager entityManager, IRepositorioCarros repositorioCarros, IRepositorioMotos repositorioMotos){
		this.entityManager = entityManager;
		this.repositorioCarros = repositorioCarros;
		this.repositorioMotos = repositorioMotos;
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
		CarroEntity carroEntity = repositorioCarros.obtenerCarroEntityPorPlaca(registro.getVehiculo().getPlaca()); 
		return RegistroCarroBuilder.convertirAEntity(registro, carroEntity);
	}
	
	private RegistroMotoEntity buildRegistroMotoEntity(Registro registro){
		MotoEntity motoEntity = repositorioMotos.obtenerMotoEntityPorPlaca(registro.getVehiculo().getPlaca());
		return RegistroMotoBuilder.convertirAEntity(registro, motoEntity);
	}

	@Override
	public Registro obtenerUltimoRegistroCarroPorPlaca(String placa) {
		RegistroCarroEntity registroCarroEntity = obtenerRegistroCarroEntityPorPlaca(placa);
		return RegistroCarroBuilder.convertirADominio(registroCarroEntity);
	}
	
	@Override
	public Registro obtenerUltimoRegistroMotoPorPlaca(String placa) {
		RegistroMotoEntity registroMotoEntity = obtenerRegistroMotoEntityPorPlaca(placa);
		return RegistroMotoBuilder.convertirADominio(registroMotoEntity);
	}
	@SuppressWarnings("rawtypes")
	private RegistroCarroEntity obtenerRegistroCarroEntityPorPlaca(String placa) {
		Query query = entityManager.createNamedQuery(CARRO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);
		System.out.println("obtenerRegistroCarroEntityPorPlaca");
		System.out.println(query.unwrap(org.hibernate.Query.class).getQueryString());
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (RegistroCarroEntity) resultList.get(0) : null;
	}
	@SuppressWarnings("rawtypes")
	private RegistroMotoEntity obtenerRegistroMotoEntityPorPlaca(String placa) {
		Query query = entityManager.createNamedQuery(MOTO_FIND_BY_PLACA);
		query.setParameter(PLACA, placa);
		List resultList = query.getResultList();
		return !resultList.isEmpty() ? (RegistroMotoEntity) resultList.get(0) : null;
	}
}
