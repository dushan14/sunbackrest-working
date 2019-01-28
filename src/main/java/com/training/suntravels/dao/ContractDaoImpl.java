package com.training.suntravels.dao;

import com.training.suntravels.domain.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContractDaoImpl implements ContractDao

{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contract> getAll()
	{
		return entityManager.createQuery( "from Contract", Contract.class ).getResultList();
	}

	@Override
	public Contract getContract( int id )
	{
		return entityManager.find( Contract.class, id );
	}
}
