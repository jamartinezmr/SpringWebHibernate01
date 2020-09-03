package mx.gob.queretaro.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {
	private final Logger log = Logger.getLogger(getClass().getName());

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("mx.gob.queretaro.model");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}


	@Bean
	public DataSource dataSource() {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = null;

		try {
			dataSource = (DataSource) jndiTemplate.lookup("java:/DScursobase");
		} catch (NamingException e) {
			log.error("Ocurrió un error al obtener el DataSource.");
		}

		return dataSource;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();

		transactionManager.setSessionFactory(sessionFactory().getObject());

		return transactionManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		// https://www.dokry.com/508
		properties.put("hibernate.hbm2ddl.auto", "validate"); //update, //drop_create
		properties.put("hibernate.enable_lazy_load_no_trans", "true");

		return properties;
	}
}
