package com.training.suntravels.service;

import com.training.suntravels.dao.SearchResultDao;
import com.training.suntravels.domain.SearchRequest;
import com.training.suntravels.domain.SearchResult;
import com.training.suntravels.domain.SearchResultRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class SearchResultServiceImpl implements SearchResultService
{
	@Autowired
	SearchResultDao searchResultDao;

	@Autowired
	CostMarkupService costMarkupService;

	@Override
	@Transactional
	public List<SearchResult> getSearchResult( SearchRequest searchRequest )
	{
		List<SearchResult> results = searchResultDao.getResult( searchRequest.getCheckIn(), searchRequest.getNoOfNights(), searchRequest.getRoomAdultPairs() );

		int noOfConstraints = searchRequest.getRoomAdultPairs().size();

		if ( noOfConstraints == 1 )
		{

			int noOfRooms = searchRequest.getRoomAdultPairs().get( 0 ).getNoOfRooms();
			int noOfAdults = searchRequest.getRoomAdultPairs().get( 0 ).getNoOfAdults();
			int noOfNights = searchRequest.getNoOfNights();

			for ( SearchResult searchResult : results )
			{
				SearchResultRoom resultRoom = new SearchResultRoom( noOfNights, noOfAdults, noOfRooms );

				double markupPrice = costMarkupService.getCalculatedCostMarkup( noOfRooms, noOfAdults, noOfNights, searchResult.getUnitPricePerAdult() );
				resultRoom.setPrice( markupPrice );

				resultRoom.setHotelId( searchResult.getHotelId() );
				resultRoom.setHotel( searchResult.getHotel() );

				resultRoom.setRoomType( searchResult.getRoomType() );
				resultRoom.setRoomTypeId( searchResult.getRoomTypeId() );

				//				list.add(resultRoom);
				// TODO: 1/25/2019 add these into final wrapper and return
			}

		}
		else
		{
			HashMap<Integer, List<SearchResult>> resultsGroupedByHotelId = groupByHotelId( results );
			// TODO: 1/25/2019 from here calculate combinations inside the hotel rooms.


		}

		return results;
	}

	private HashMap<Integer, List<SearchResult>> groupByHotelId( List<SearchResult> searchResults )
	{
		HashMap<Integer, List<SearchResult>> resultsGroupByHotelId = new HashMap<>();

		searchResults.forEach( result -> {

					if ( resultsGroupByHotelId.containsKey( result.getHotelId() ) )
					{
						resultsGroupByHotelId.get( result.getHotelId() ).add( result );
					}
					else
					{
						ArrayList<SearchResult> roomCombinationsInHotel = new ArrayList<>();
						roomCombinationsInHotel.add( result );
						resultsGroupByHotelId.put( result.getHotelId(), roomCombinationsInHotel );
					}

				}
		);
		return resultsGroupByHotelId;

	}

}
