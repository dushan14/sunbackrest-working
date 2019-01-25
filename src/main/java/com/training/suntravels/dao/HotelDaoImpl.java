package com.training.suntravels.dao;

import com.training.suntravels.domain.Hotel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("hotelDao")
public class HotelDaoImpl extends AbstractDao<Integer, Hotel> implements HotelDao
{

	@SuppressWarnings("unchecked")
	public List<Hotel> getAllHotels()
	{
		Criteria criteria = createEntityCriteria();
		return ( List<Hotel> ) criteria.list();
	}

	@Override
	public Integer saveHotel( Hotel hotel )
	{
//		persist( hotel );
//		saveOrUpdate( hotel );
		Serializable save = getSession().save( hotel );

		return (Integer)save;
	}

}
