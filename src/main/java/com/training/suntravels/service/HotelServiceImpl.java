package com.training.suntravels.service;

import com.training.suntravels.dao.HotelDao;
import com.training.suntravels.domain.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService
{
	@Autowired
	private HotelDao hotelDao;

	@Override
	@Transactional
	public List<Hotel> getAllHotels()
	{
		return hotelDao.getAllHotels();
	}
}
