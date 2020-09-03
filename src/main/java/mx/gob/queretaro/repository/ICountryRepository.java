package mx.gob.queretaro.repository;

import java.util.List;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;

public interface ICountryRepository {

	List<Country> obtenerTodos() throws InternalException;

}
