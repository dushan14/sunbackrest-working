package com.training.suntravels.dao;

import com.training.suntravels.domain.Hotel;

import java.util.List;

public interface HotelDao
{
	List<Hotel> getAll();

	Hotel getHotel( int id );

	Integer saveHotel(Hotel hotel);

}
