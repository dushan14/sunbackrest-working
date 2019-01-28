package com.training.suntravels.controller;

import com.training.suntravels.domain.RoomContract;
import com.training.suntravels.service.RoomContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/roomcontracts")
public class RoomContractController
{

	@Autowired
	RoomContractService roomContractService;


	@RequestMapping(value = {"","/"},method = RequestMethod.GET)
	public List<RoomContract> getAllRoomContracts(){
		return roomContractService.getAll();
	}

	@RequestMapping(value = "/{contractId}", method = RequestMethod.GET)
	public List<RoomContract> getRoomContractByContract( @PathVariable int contractId)
	{
		return roomContractService.getRoomContractByContract(contractId);
	}

	@RequestMapping(value = "/{contractId}/{roomTypeId}", method = RequestMethod.GET)
	public RoomContract getRoomContract( @PathVariable int contractId,@PathVariable int roomTypeId)
	{
		return roomContractService.getRoomContract(contractId,roomTypeId);
	}

}
