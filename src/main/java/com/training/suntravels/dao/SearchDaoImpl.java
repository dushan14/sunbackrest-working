package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomAdultCondition;
import com.training.suntravels.domain.SearchQueryDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.training.suntravels.util.Util.addDaysToDate;

@Repository
public class SearchDaoImpl implements SearchDao
{

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<SearchQueryDTO> getResult( Date checkIn, int noOfNights, List<RoomAdultCondition> roomAdultPairs )
	{

		Date checkOut = addDaysToDate( checkIn, noOfNights );

		String queryStr1 = "select distinct "
				+ "NEW com.training.suntravels.domain.SearchQueryDTO("
				+ " rc.contract.hotel.id, rc.roomType.id, rc.noOfRooms, rc.roomType.noOfAdults"
				+ ", rc.contract.hotel.name, rc.roomType.name, rc.price, rc.contract.currency"
				+ " )"
				+ " from"
				+ " RoomContract rc where"
				+ " rc.contract.validFrom<=:checkIn"
				+ " and"
				+ " rc.contract.validTo>=:checkOut"
				+ " and ("
				+ " (rc.roomType.noOfAdults>=:noOfAdultsCond0 and rc.noOfRooms>=:noOfRoomsCond0)";

		for ( int i = 1; i < roomAdultPairs.size(); i++ )
		{
			queryStr1 += " or (rc.roomType.noOfAdults>=:noOfAdultsCond" + i + " and rc.noOfRooms>=:noOfRoomsCond" + i + ")";
		}

		queryStr1 += ")";

		TypedQuery<SearchQueryDTO> searchQueryDTOTypedQuery = entityManager
				.createQuery( queryStr1, SearchQueryDTO.class )
				.setParameter( "checkIn", checkIn )
				.setParameter( "checkOut", checkOut );

		int i = 0;
		for ( RoomAdultCondition pair : roomAdultPairs )
		{
			searchQueryDTOTypedQuery.setParameter( "noOfAdultsCond" + i, pair.getNoOfAdults() ).setParameter( "noOfRoomsCond" + i, pair.getNoOfRooms() );
			i++;
		}

		return searchQueryDTOTypedQuery
				.getResultList();

	}
}
