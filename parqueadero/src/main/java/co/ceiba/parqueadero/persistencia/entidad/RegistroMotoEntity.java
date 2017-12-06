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

@Entity(name="RegistroMoto")
@NamedQueries({
	@NamedQuery(name = "RegistroMoto.findByPlaca", query = "SELECT registromoto FROM RegistroMoto registromoto WHERE registromoto.fechaEntrada is not null and registromoto.fechaSalida is null and registromoto.motoEntity.placa = :placa"),
	@NamedQuery(name = "RegistroMoto.enParqueadero", query = "SELECT registromoto FROM RegistroMoto registromoto WHERE registromoto.fechaSalida is null")
})
public class RegistroMotoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
private Date fechaEntrada;
	
	private Date fechaSalida;
	
	private double valor;
	
	@ManyToOne
	@JoinColumn(name="ID_MOTO",referencedColumnName="id")
	private MotoEntity motoEntity;

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

	public MotoEntity getMotoEntity() {
		return motoEntity;
	}

	public void setMotoEntity(MotoEntity motoEntity) {
		this.motoEntity = motoEntity;
	}
}
