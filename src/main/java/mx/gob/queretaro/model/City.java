package mx.gob.queretaro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="city")
public class City implements Serializable {

	private static final long serialVersionUID = 98357281576429822L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id", unique=true, nullable=false)
	private short cityId;
	@Column(nullable=false, length=50)
	private String city;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable=false)
	private Date lastUpdate;
	@Column(nullable=false, length=2)
	private String status;

	// Llave Foranea
	@JsonIgnore
	// @JsonIgnoreProperties(value = {"cities", "hibernateLazyInitializer", "handler"}, allowSetters = true) // Trae pais
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // trae pais y ciudades hijas
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;

	public City(short cityId, String city, Date lastUpdate) {
		this.cityId = cityId;
		this.city = city;
		this.lastUpdate = lastUpdate;
	}

	public City() {
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(short cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
