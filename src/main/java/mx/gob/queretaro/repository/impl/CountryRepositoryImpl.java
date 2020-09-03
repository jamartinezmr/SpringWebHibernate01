package mx.gob.queretaro.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.Country;
import mx.gob.queretaro.repository.ICountryRepository;

@Repository
public class CountryRepositoryImpl implements ICountryRepository {
	private final SessionFactory sessionFactory;
	private final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public CountryRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Country> obtenerTodos() throws InternalException {
		try {
			Session session = sessionFactory.getCurrentSession();

			return session.createQuery("SELECT NEW Country(c.countryId, c.country) FROM Country c ORDER BY countryId ASC").list();
			//return session.createNamedQuery("Country.findAll").list();
		} catch (Exception ex) {
			log.error("Ocurrio un error al obtener los paises", ex);
			throw new InternalException("Ocurrio un error al obtener los paises");
		}
	}

}
