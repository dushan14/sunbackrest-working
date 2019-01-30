package com.training.suntravels.service;

import com.training.suntravels.domain.Hotel;
import com.training.suntravels.domain.RoomTypeDTO;

import java.util.List;

public interface HotelService
{
	List<Hotel> getAll();

	Hotel getHotel( int id );

	Integer saveHotel( Hotel hotel );

	List<RoomTypeDTO> getHotelRooms( int hotelId );

	RoomTypeDTO getHotelRoomsById( int hotelId, int roomTypeId );

}
