package co.ceiba.parqueadero.persistencia.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name="RegistroCarro")
@NamedQueries({
	@NamedQuery(name = "RegistroCarro.findByPlaca", query = "SELECT registrocarro FROM RegistroCarro registrocarro WHERE registrocarro.fechaEntrada is not null and registrocarro.fechaSalida is null and registrocarro.carroEntity.placa = :placa"),
	@NamedQuery(name = "RegistroCarro.enParqueadero", query = "SELECT registrocarro FROM RegistroCarro registrocarro WHERE registrocarro.fechaSalida is null")
})
public class RegistroCarroEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private double valor;
	
	@ManyToOne
	@JoinColumn(name="ID_CARRO",referencedColumnName="id")
	private CarroEntity carroEntity;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFechaEntrada() {
		return fechaEntrada;
	}
	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public CarroEntity getCarroEntity() {
		return carroEntity;
	}
	public void setCarroEntity(CarroEntity carroEntity) {
		this.carroEntity = carroEntity;
	}
}
