package com.training.suntravels.controller;

import com.training.suntravels.domain.RoomType;
import com.training.suntravels.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/roomtypes")
public class RoomTypeController
{
	@Autowired
	RoomTypeService roomTypeService;

	@RequestMapping(value = {"","/"},method = RequestMethod.GET)
	public List<RoomType> getAllRoomTypes(){
		return roomTypeService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoomType getRoomType( @PathVariable int id )
	{
		return roomTypeService.getRoomType( id );
	}
}
