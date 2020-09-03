package mx.gob.queretaro.request;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class CityRequest implements Serializable {

	private static final long serialVersionUID = -7369519744613007256L;
	@NotNull
	@NotEmpty(message ="El nombre de la ciudad no debe ser vacía")
	@Size(min = 5, max = 20)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "debe contener solo letras")
	private String city;
	@NotNull
	private short countryId;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdate;

	public CityRequest() {

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public short getCountryId() {
		return countryId;
	}

	public void setCountryId(short countryId) {
		this.countryId = countryId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		System.out.println("Fecha = "+lastUpdate);
		this.lastUpdate = lastUpdate;
	}

}
