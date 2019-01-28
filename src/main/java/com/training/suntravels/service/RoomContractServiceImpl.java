package com.training.suntravels.service;

import com.training.suntravels.dao.RoomContractDao;
import com.training.suntravels.domain.RoomContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomContractServiceImpl implements RoomContractService
{

	@Autowired
	RoomContractDao roomContractDao;

	@Override
	public List<RoomContract> getAll()
	{
		return roomContractDao.getAll();
	}

	@Override
	public List<RoomContract> getRoomContractByContract( int contractId )
	{
		return roomContractDao.getRoomContractByContract( contractId );
	}

	@Override
	public RoomContract getRoomContract( int contractId, int roomTypeId )
	{
		return roomContractDao.getRoomContract( contractId, roomTypeId );
	}

}
