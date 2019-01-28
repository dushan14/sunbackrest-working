package com.training.suntravels.dao;

import com.training.suntravels.domain.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Repository("hotelDao")
public class HotelDaoImpl extends AbstractDao<Integer, Hotel> implements HotelDao
{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession()
	{

		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Hotel> getAll()
	{
		return entityManager.createQuery( "from Hotel", Hotel.class ).getResultList();
	}

	@Override
	public Hotel getHotel( int id )
	{
		return entityManager.find( Hotel.class, id );
	}

	@Override
	public Integer saveHotel( Hotel hotel )
	{
		return ( Integer ) getSession().save( hotel );
	}
}
