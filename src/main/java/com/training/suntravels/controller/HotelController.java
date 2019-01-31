package com.training.suntravels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.suntravels.domain.Hotel;
import com.training.suntravels.domain.RoomTypeDTO;
import com.training.suntravels.domain.SearchRequestDTO;
import com.training.suntravels.domain.SearchResultDTO;
import com.training.suntravels.service.HotelService;
import com.training.suntravels.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/hotels")
public class HotelController
{
	@Autowired
	HotelService hotelService;

	@Autowired
	SearchService searchService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public List<Hotel> getAllHotels()
	{
		List<Hotel> all = hotelService.getAll();
		return all;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Hotel getHotelById( @PathVariable int id )
	{
		return hotelService.getHotel( id );
	}

	@RequestMapping(value = "/{hotelId}/roomtypes", method = RequestMethod.GET)
	public List<RoomTypeDTO> getHotelRooms( @PathVariable int hotelId )
	{
		return hotelService.getHotelRooms( hotelId );
	}

	@RequestMapping(value = "/{hotelId}/roomtypes/{roomTypeId}", method = RequestMethod.GET)
	public RoomTypeDTO getHotelRoomsById( @PathVariable int hotelId, @PathVariable int roomTypeId )
	{
		return hotelService.getHotelRoomsById( hotelId, roomTypeId );
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, consumes = { "application/json" })
	public SearchResultDTO getHotelsBySearch( @RequestBody String searchRequestJson,@RequestParam(name = "deep",defaultValue = "false") boolean isDeepSearch )
	{
		SearchRequestDTO searchRequestDTO;
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			searchRequestDTO = mapper.readValue( searchRequestJson, SearchRequestDTO.class );
			return searchService.getSearchResult( searchRequestDTO,isDeepSearch );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			return null;
		}

	}

}
