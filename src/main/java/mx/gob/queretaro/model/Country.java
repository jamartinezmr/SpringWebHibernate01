package mx.gob.queretaro.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c ORDER BY c.countryId ASC")
public class Country implements Serializable {

	private static final long serialVersionUID = 6504621010333628987L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="country_id", unique=true, nullable=false)
	private short countryId;
	@Column(nullable=false, length=50)
	private String country;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update", nullable=false)
	private Date lastUpdate;
	@JsonIgnore
	@JsonIgnoreProperties(value = {"country", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(mappedBy="country")
	private List<City> cities;

	public Country() {
	}

	public Country(short countryId) {
		this.countryId = countryId;
	}

	public Country(short countryId, String country) {
		this.countryId = countryId;
		this.country = country;
	}


	public Country(short countryId, String country, Date lastUpdate) {
		this.countryId = countryId;
		this.country = country;
		this.lastUpdate = lastUpdate;
	}

	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(short countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
