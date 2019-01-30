package com.training.suntravels.service;

import com.training.suntravels.dao.RoomTypeDao;
import com.training.suntravels.domain.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService
{

	@Autowired
	RoomTypeDao roomTypeDao;

	@Override
	public List<RoomType> getAll()
	{
		return roomTypeDao.getAll();
	}

	@Override
	public RoomType getRoomType( int id )
	{
		return roomTypeDao.getRoomType( id );
	}
}
