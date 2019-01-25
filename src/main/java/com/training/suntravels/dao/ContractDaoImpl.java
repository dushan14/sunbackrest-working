package com.training.suntravels.dao;

import com.training.suntravels.domain.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ContractDaoImpl  implements ContractDao

{
	@PersistenceContext
	protected EntityManager entityManager;

//	public ContractDaoImpl(){
//		setClazz(Contract.class );
//	}

	@Override
	public List<Contract> getContracts()
	{
		List<Contract> all = entityManager.createQuery( "from Contract"  )
				.getResultList();
		return all;
	}
}
