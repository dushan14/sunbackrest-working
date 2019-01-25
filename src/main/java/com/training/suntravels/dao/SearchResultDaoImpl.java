package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomAdultPair;
import com.training.suntravels.domain.SearchResult;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SearchResultDaoImpl implements SearchResultDao
{

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<SearchResult> getResult( Date checkIn, int noOfNights, List<RoomAdultPair> roomAdultPairs )
	{

		String checkInDateStr = new SimpleDateFormat( "yyyy/MM/dd" ).format( checkIn );
		String checkInDateStrSql = "TO_DATE('" + checkInDateStr + "', 'yyyy/mm/dd')";
		String chekOutDateStrSql = "TO_DATE('" + checkInDateStr + "', 'yyyy/mm/dd')+" + noOfNights;

		String queryStr = "SELECT H.ID AS HOTEL_ID,"
				+ "  H.NAME AS HOTEL,"
				+ "  RT.ID AS ROOM_TYPE_ID,"
				+ "  RT.NAME AS ROOM_TYPE,"
				+ "  RC.NO_OF_ROOMS  AS AVAILABLE_ROOMS,"
				+ "  RT.NO_OF_ADULTS AS ADULTS_PER_ROOM,"
				+ "  RC.PRICE AS UNIT_PRICE_PER_ADULT,"
				+ "  C.CURRENCY "
				+ " FROM DK_CONTRACT C "
				+ " INNER JOIN DK_ROOM_CONTRACT RC "
				+ " ON C.ID=RC.CONTRACT_ID "
				+ " INNER JOIN DK_ROOM_TYPE RT "
				+ " ON RC.ROOM_TYPE_ID=RT.ID "
				+ " INNER JOIN DK_HOTEL H "
				+ " ON H.ID=C.HOTEL_ID "
				+ " WHERE C.VALID_FROM  <= " + checkInDateStrSql
				+ " AND C.VALID_TO      >= " + chekOutDateStrSql
				+ " AND (RT.NO_OF_ADULTS>= " + roomAdultPairs.get( 0 ).getNoOfAdults()
				+ " AND RC.NO_OF_ROOMS  >= " + roomAdultPairs.get( 0 ).getNoOfRooms()
				+ ") ";
		//		+ " OR (RT.NO_OF_ADULTS >= ? AND RC.NO_OF_ROOMS  >=?)";    // this will be repeated according to roomAdultPairs

		// for handling multiple room adults constraints
		for ( RoomAdultPair pair : roomAdultPairs.subList( 1, roomAdultPairs.size() ) )
		{
			queryStr += " OR (RT.NO_OF_ADULTS >= " + pair.getNoOfAdults() + " AND RC.NO_OF_ROOMS  >= " + pair.getNoOfRooms() + ")";
		}

		SQLQuery sqlQuery = getSession().createSQLQuery( queryStr );

		List<Object[]> list = sqlQuery.list();

		List<SearchResult> results = new ArrayList<>();
		int index=0;
		for ( Object[] a : list )
		{
			SearchResult searchResult = new SearchResult();

			searchResult.setResultId( index++ );

			searchResult.setHotelId( ( ( BigDecimal ) a[0] ).intValue() );
			searchResult.setHotel( ( String ) a[1] );
			searchResult.setRoomTypeId( ( int ) a[2] );
			searchResult.setRoomType( ( String ) a[3] );
			searchResult.setAvailableRooms( ( ( BigDecimal ) a[4] ).intValue() );
			searchResult.setAdultsPerRoom( ( ( BigDecimal ) a[5] ).intValue() );
			searchResult.setUnitPricePerAdult( ( ( BigDecimal ) a[6] ).doubleValue() );
			searchResult.setCurrency( ( String ) a[7] );

			results.add( searchResult );
		}
		return results;

	}
}
