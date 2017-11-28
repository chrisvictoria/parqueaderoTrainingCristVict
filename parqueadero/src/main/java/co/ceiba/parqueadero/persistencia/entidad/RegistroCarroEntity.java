package co.ceiba.parqueadero.persistencia.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity(name="RegistroCarro")
@NamedQuery(name = "RegistroCarro.findByPlaca", query = "SELECT registrocarro FROM RegistroCarro registrocarro WHERE registrocarro.fecha = (select max(a.fecha) FROM RegistroCarro a where a.carroEntity.placa = :placa) and registrocarro.carroEntity.placa = :placa")
public class RegistroCarroEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date fecha;
	
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="ID_CARRO",referencedColumnName="id")
	private CarroEntity carroEntity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public CarroEntity getCarroEntity() {
		return carroEntity;
	}
	public void setCarroEntity(CarroEntity carroEntity) {
		this.carroEntity = carroEntity;
	}
}
