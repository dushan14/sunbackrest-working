package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoomTypeDaoImpl implements RoomTypeDao
{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RoomType> getAll()
	{
		List<RoomType> roomTypes = entityManager.createQuery( "from RoomType", RoomType.class ).getResultList();
		return roomTypes;
	}

	@Override
	public RoomType getRoomType( int id )
	{
		return entityManager.find( RoomType.class, id );
	}
}
