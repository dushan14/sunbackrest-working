package com.training.suntravels.dao;

import com.training.suntravels.domain.Hotel;
import com.training.suntravels.domain.RoomTypeDTO;

import java.util.List;

public interface HotelDao
{
	List<Hotel> getAll();

	Hotel getHotel( int id );

	Integer saveHotel( Hotel hotel );

	List<RoomTypeDTO> getHotelRooms( int hotelId );

	RoomTypeDTO getHotelRoomsById( int hotelId, int roomTypeId );
}
