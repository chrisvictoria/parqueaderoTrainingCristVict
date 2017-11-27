package co.ceiba.parqueadero.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity(name = "Moto")
@NamedQuery(name = "Moto.findByPlaca", query = "SELECT moto FROM Moto moto WHERE moto.placa = :placa")
public class MotoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String placa;
	@Column(nullable = false)
	private double cilindraje;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public double getCilindraje() {
		return cilindraje;
	}
	
	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}	
}
