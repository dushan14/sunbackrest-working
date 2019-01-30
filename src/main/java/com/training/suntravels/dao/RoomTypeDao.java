package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomType;

import java.util.List;

public interface RoomTypeDao
{
	List<RoomType> getAll();

	RoomType getRoomType( int id );
}
