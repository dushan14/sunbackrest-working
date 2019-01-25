package com.training.suntravels.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.suntravels.domain.Contract;
import com.training.suntravels.domain.Hotel;
import com.training.suntravels.domain.SearchRequest;
import com.training.suntravels.domain.SearchResult;
import com.training.suntravels.service.ContractService;
import com.training.suntravels.service.HotelService;
import com.training.suntravels.service.SearchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TravelServiceController
{

	@Autowired
	HotelService hotelService;

	@Autowired
	SearchResultService searchResultService;

	@Autowired
	ContractService contractService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome()
	{
		return "Hi Welcome !";
	}


	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public List<Hotel> getAllHotels()
	{
		List<Hotel> all = hotelService.getAllHotels();
		return all;
	}

	@RequestMapping(value = "/hotels/search", method = RequestMethod.GET, consumes = { "application/json" })
	public List<SearchResult> getHotelsBySearch( @RequestBody String searchRequestJson )
	{
		SearchRequest searchRequest;
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			searchRequest = mapper.readValue(searchRequestJson, SearchRequest.class);
			return searchResultService.getSearchResult( searchRequest );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
			return null;
		}

	}


	@RequestMapping(value = "/ct",method = RequestMethod.GET)
	public List<Contract> getContracts(){
		return contractService.getAll();
	}
}
