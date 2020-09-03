package mx.gob.queretaro.repository;

import java.util.List;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.City;

public interface ICityRepository {

	List<City> obtenerTodos() throws InternalException;

	City obtenerPorId(short id) throws InternalException;

	void guardar(City city) throws InternalException;

}
