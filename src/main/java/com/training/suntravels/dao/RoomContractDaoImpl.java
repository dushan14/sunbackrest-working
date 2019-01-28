package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomContract;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoomContractDaoImpl implements RoomContractDao
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RoomContract> getAll()
	{
		return entityManager.createQuery( "from RoomContract", RoomContract.class ).getResultList();
	}

	@Override
	public List<RoomContract> getRoomContractByContract( int contractId )
	{
		return entityManager.createQuery( "from RoomContract where contract.id=:contractId", RoomContract.class )
				.setParameter( "contractId", contractId )
				.getResultList();
	}

	@Override
	public RoomContract getRoomContract( int contractId, int roomTypeId )
	{
		return entityManager.createQuery( "from RoomContract where contract.id=:contractId and roomType.id=:roomTypeId  ", RoomContract.class )
				.setParameter( "contractId", contractId )
				.setParameter( "roomTypeId", roomTypeId )
				.getSingleResult();
	}
}
