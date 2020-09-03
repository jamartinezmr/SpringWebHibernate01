package mx.gob.queretaro.service;

import java.util.List;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;

public interface ICountryService {

	List<Country> obtenerTodos() throws InternalException;

}
