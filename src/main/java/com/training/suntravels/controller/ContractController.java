package com.training.suntravels.controller;

import com.training.suntravels.domain.Contract;
import com.training.suntravels.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/contracts")
public class ContractController
{

	@Autowired
	ContractService contractService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public List<Contract> getAllContracts()
	{
		return contractService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Contract getHotelById( @PathVariable int id )
	{
		return contractService.getContract( id );
	}

}
