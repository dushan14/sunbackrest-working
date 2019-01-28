package com.training.suntravels.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.training.suntravels.configuration")
@PropertySource({ "classpath:oracledb.properties" })
public class PersistenceConfiguration
{
	@Autowired
	private Environment environment;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean em
				= new LocalContainerEntityManagerFactoryBean();
		em.setDataSource( dataSource() );
		em.setPackagesToScan( new String[] { "com.training.suntravels.domain" } );

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter( vendorAdapter );
		em.setJpaProperties( hibernateProperties() );

		return em;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource( dataSource() );
		sessionFactory.setPackagesToScan( new String[] { "com.training.suntravels.domain" } );
		sessionFactory.setHibernateProperties( hibernateProperties() );
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager()
	{
		HibernateTransactionManager transactionManager =
				new HibernateTransactionManager();
		transactionManager.setSessionFactory( sessionFactory().getObject() );
		return transactionManager;
	}

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName( environment.getRequiredProperty( "jdbc.driverClassName" ) );
		dataSource.setUrl( environment.getRequiredProperty( "jdbc.url" ) );
		dataSource.setUsername( environment.getRequiredProperty( "jdbc.username" ) );
		dataSource.setPassword( environment.getRequiredProperty( "jdbc.password" ) );
		return dataSource;
	}

	private Properties hibernateProperties()
	{
		Properties properties = new Properties();
		properties.put( "hibernate.dialect", environment.getRequiredProperty( "hibernate.dialect" ) );
		properties.put( "hibernate.show_sql", environment.getRequiredProperty( "hibernate.show_sql" ) );
		properties.put( "hibernate.format_sql", environment.getRequiredProperty( "hibernate.format_sql" ) );
		return properties;
	}

	//	@Bean
	//	@Autowired
	//	public HibernateTransactionManager transactionManager( SessionFactory s )
	//	{
	//		HibernateTransactionManager txManager = new HibernateTransactionManager();
	//		txManager.setSessionFactory( s );
	//		return txManager;
	//	}

}
