package com.training.suntravels.dao;

import com.training.suntravels.domain.Hotel;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

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

}
