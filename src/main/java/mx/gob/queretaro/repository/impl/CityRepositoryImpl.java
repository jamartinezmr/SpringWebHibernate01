package mx.gob.queretaro.repository.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.gob.queretaro.exception.InternalException;
import mx.gob.queretaro.model.City;
import mx.gob.queretaro.repository.ICityRepository;

@Repository
public class CityRepositoryImpl implements ICityRepository {

	private final SessionFactory sessionFactory;
	private final Logger log = Logger.getLogger(getClass().getName());

	@Autowired
	public CityRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<City> obtenerTodos() throws InternalException {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<City> cq = cb.createQuery(City.class);
			Root <City> root = cq.from(City.class);
			cq.select(root);
			// cq.multiselect(root.get("cityId"), root.get("city"), root.get("lastUpdate"));
			Query query = session.createQuery(cq);

			return query.getResultList();
		} catch (Exception ex) {
			log.error("Ocurrio un eror al obtener las ciudades", ex);
			throw new InternalException("Ocurrio un eror al obtener las ciudades");
		}
	}

	@Override
	public City obtenerPorId(short id) throws InternalException {
		try {
			Session session = sessionFactory.getCurrentSession();

			return session.get(City.class, id);
		} catch (Exception ex) {
			log.error(String.format("Ocurrio un eror al obtener la ciudad con el %d", id), ex);
			throw new InternalException(String.format("Ocurrio un eror al obtener la ciudad con el %d", id));
		}
	}

	@Override
	public void guardar(City city) throws InternalException {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.save(city);

		} catch (Exception ex) {
			log.error("Ocurrio un eror al guardar la ciudad", ex);
			throw new InternalException("Ocurrio un eror al guardar la ciudad");
		}

	}

}

