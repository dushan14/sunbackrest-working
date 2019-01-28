package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomContract;

import java.util.List;

public interface RoomContractDao
{
	List<RoomContract> getAll();

	List<RoomContract> getRoomContractByContract( int contractId );

	RoomContract getRoomContract( int contractId, int roomTypeId );

}
