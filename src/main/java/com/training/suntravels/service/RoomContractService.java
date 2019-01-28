package com.training.suntravels.service;

import com.training.suntravels.domain.RoomContract;

import java.util.List;

public interface RoomContractService
{
	List<RoomContract> getAll();

	List<RoomContract> getRoomContractByContract( int contractId );

	RoomContract getRoomContract(int contractId,int roomTypeId);
}
