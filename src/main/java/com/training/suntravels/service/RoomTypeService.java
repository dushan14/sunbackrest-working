package com.training.suntravels.service;

import com.training.suntravels.domain.RoomType;

import java.util.List;

public interface RoomTypeService
{
	List<RoomType> getAll();

	RoomType getRoomType( int id );
}
