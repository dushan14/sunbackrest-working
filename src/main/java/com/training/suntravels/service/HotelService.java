package com.training.suntravels.service;

import com.training.suntravels.domain.Hotel;

import java.util.List;

public interface HotelService
{
	List<Hotel> getAll();

	Hotel getHotel(int id);

	Integer saveHotel( Hotel hotel );
}
