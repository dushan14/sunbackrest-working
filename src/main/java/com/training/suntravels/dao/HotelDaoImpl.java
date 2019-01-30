package com.training.suntravels.dao;

import com.training.suntravels.domain.Hotel;
import com.training.suntravels.domain.RoomTypeDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("hotelDao")
public class HotelDaoImpl implements HotelDao
{

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession()
	{

		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Hotel> getAll()
	{
		return entityManager.createQuery( "from Hotel", Hotel.class ).getResultList();
	}

	@Override
	public Hotel getHotel( int id )
	{
		return entityManager.find( Hotel.class, id );
	}

	@Override
	public Integer saveHotel( Hotel hotel )
	{
		return ( Integer ) getSession().save( hotel );
	}

	@Override
	public List<RoomTypeDTO> getHotelRooms( int hotelId )
	{
		String queryStr1 = "select "
				+ "NEW com.training.suntravels.domain.RoomTypeDTO("
				+ " rt.id, rt.name, rt.noOfAdults, rc.noOfRooms, rc.price, rc.contract.currency"
				+ " )"
				+ " from"
				+ " RoomType rt, RoomContract rc "
				+ " where"
				+ " rt.id=rc.roomType.id"
				+ " and"
				+ " rt.hotel.id=:hotelId";

		TypedQuery<RoomTypeDTO> searchQueryDTOTypedQuery = entityManager
				.createQuery( queryStr1, RoomTypeDTO.class )
				.setParameter( "hotelId", hotelId );

		return searchQueryDTOTypedQuery
				.getResultList();
	}

	@Override
	public RoomTypeDTO getHotelRoomsById( int hotelId, int roomTypeId )
	{
		String queryStr1 = "select "
				+ "NEW com.training.suntravels.domain.RoomTypeDTO("
				+ " rt.id, rt.name, rt.noOfAdults, rc.noOfRooms, rc.price, rc.contract.currency"
				+ " )"
				+ " from"
				+ " RoomType rt, RoomContract rc "
				+ " where"
				+ " rt.id=rc.roomType.id"
				+ " and"
				+ " rt.hotel.id=:hotelId"
				+ " and"
				+ " rt.id=:roomTypeID";

		TypedQuery<RoomTypeDTO> searchQueryDTOTypedQuery = entityManager
				.createQuery( queryStr1, RoomTypeDTO.class )
				.setParameter( "hotelId", hotelId )
				.setParameter( "roomTypeID", roomTypeId );

		return searchQueryDTOTypedQuery
				.getSingleResult();
	}
}
