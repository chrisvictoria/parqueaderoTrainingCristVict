package co.ceiba.parqueadero.persistencia.entidad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="RegistroMoto")
public class RegistroMotoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date fecha;
	
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name="ID_MOTO",referencedColumnName="id")
	private MotoEntity motoEntity;

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

	public MotoEntity getMotoEntity() {
		return motoEntity;
	}

	public void setMotoEntity(MotoEntity motoEntity) {
		this.motoEntity = motoEntity;
	}
}
