package mx.gob.queretaro.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.City;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.repository.ICityRepository;
import mx.gob.queretaro.request.CityRequest;
import mx.gob.queretaro.service.ICityService;

@Service
@Transactional
public class CityServiceImpl implements ICityService {

	private final ICityRepository cityRepository;
	private final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public CityServiceImpl (ICityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public List<City> obtenerTodos() throws InternalException {
		try {
			return cityRepository.obtenerTodos();
		} catch (InternalException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("Ocurrio un eror al obtener las ciudades", ex);
			throw new InternalException("Ocurrio un eror al obtener las ciudades");
		}
	}

	@Override
	public City obtenerPorId(short id) throws InternalException {
		if (id > 0L) {
			try {
				return cityRepository.obtenerPorId(id);
			} catch (InternalException ex) {
				throw ex;
			} catch (Exception ex) {
				log.error(String.format("Ocurrio un eror al obtener la ciudad con el id: %d", id), ex);
				throw new InternalException(String.format("Ocurrio un eror al obtener la ciudad con el id: %d", id));
			}
		} else {
			throw new InternalException("El id de la ciudad debe ser mayor a 0");
		}
	}


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void guardar(CityRequest cityRequest) throws InternalException {
		try {
			System.out.println("getLastUpdate "+ cityRequest.getLastUpdate());
			City city = new City();

			city.setCity(cityRequest.getCity().trim());
			city.setCountry(new Country(cityRequest.getCountryId()));
			city.setLastUpdate(cityRequest.getLastUpdate());
			city.setStatus("AC");

			cityRepository.guardar(city);
		} catch (InternalException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("Ocurrio un eror al guardar la ciudad", ex);
			throw new InternalException("Ocurrio un eror al guardar la ciudad");
		}
	}

}
