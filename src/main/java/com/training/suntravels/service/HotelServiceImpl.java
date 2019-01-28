package com.training.suntravels.service;

import com.training.suntravels.dao.HotelDao;
import com.training.suntravels.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hotelService")
public class HotelServiceImpl implements HotelService
{
	@Autowired
	private HotelDao hotelDao;

	@Override
	public List<Hotel> getAll()
	{
		return hotelDao.getAll();
	}

	@Override
	public Hotel getHotel( int id )
	{
		return hotelDao.getHotel(id);
	}

	@Override
	@Transactional
	public Integer saveHotel( Hotel hotel )
	{
		return hotelDao.saveHotel( hotel );
	}
}
