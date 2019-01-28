package com.training.suntravels.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "")
public class TravelServiceController
{

	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String welcome()
	{
		return "Sun Travels REST API";
	}
}
