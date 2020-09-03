package mx.gob.queretaro.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.repository.ICountryRepository;
import mx.gob.queretaro.service.ICountryService;

@Service
@Transactional
public class CountryServiceImpl implements ICountryService {

	@Autowired
	private ICountryRepository countryRepository;
	private final Logger log = Logger.getLogger(getClass().getName());

	@Override
	public List<Country> obtenerTodos() throws InternalException {
		try {
			return countryRepository.obtenerTodos();
		} catch (InternalException ex) {
			throw ex;
		} catch (Exception ex) {
			log.error("Ocurrio un error al obtener los paises", ex);
			throw new InternalException("Ocurrio un error al obtener los paises");
		}
	}

}
