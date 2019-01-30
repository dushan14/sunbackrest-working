package com.training.suntravels.service;

import com.training.suntravels.dao.ContractDao;
import com.training.suntravels.domain.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService
{
	@Autowired
	ContractDao contractDao;

	@Override
	public List<Contract> getAll()
	{
		return contractDao.getAll();
	}

	@Override
	public Contract getContract( int id )
	{
		return contractDao.getContract( id );
	}
}
