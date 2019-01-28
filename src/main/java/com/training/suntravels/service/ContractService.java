package com.training.suntravels.service;

import com.training.suntravels.domain.Contract;

import java.util.List;

public interface ContractService
{
	List<Contract> getAll();

	Contract getContract( int id );
}
