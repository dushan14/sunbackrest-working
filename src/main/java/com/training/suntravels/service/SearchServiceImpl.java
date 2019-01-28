package com.training.suntravels.service;

import com.training.suntravels.dao.SearchDao;
import com.training.suntravels.domain.SearchQueryDTO;
import com.training.suntravels.domain.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class SearchServiceImpl implements SearchService
{
	@Autowired
	SearchDao searchDao;

	@Autowired
	CostMarkupService costMarkupService;

	@Override
	@Transactional
	public List<SearchQueryDTO> getSearchResult( SearchRequest searchRequest )
	{
		List<SearchQueryDTO> results = searchDao.getResult( searchRequest.getCheckIn(), searchRequest.getNoOfNights(), searchRequest.getRoomAdultPairs() );

//		int noOfConstraints = searchRequest.getRoomAdultPairs().size();
//
//		if ( noOfConstraints == 1 )
//		{
//
//			int noOfRooms = searchRequest.getRoomAdultPairs().get( 0 ).getNoOfRooms();
//			int noOfAdults = searchRequest.getRoomAdultPairs().get( 0 ).getNoOfAdults();
//			int noOfNights = searchRequest.getNoOfNights();
//
//			for ( SearchQueryDTO searchResult : results )
//			{
//				SearchResultRoom resultRoom = new SearchResultRoom( noOfNights, noOfAdults, noOfRooms );
////
////				double markupPrice = costMarkupService.getCalculatedCostMarkup( noOfRooms, noOfAdults, noOfNights, searchResult.getUnitPricePerAdult() );
////				resultRoom.setPrice( markupPrice );
////
////				resultRoom.setHotelId( searchResult.getHotelId() );
////				resultRoom.setHotel( searchResult.getHotel() );
////
////				resultRoom.setRoomType( searchResult.getRoomType() );
////				resultRoom.setRoomTypeId( searchResult.getRoomTypeId() );
//
//				//				list.add(resultRoom);
//				// TODO: 1/25/2019 add these into final wrapper and return
//			}
//
//		}
//		else
//		{
//			HashMap<Integer, List<SearchQueryDTO>> resultsGroupedByHotelId = groupByHotelId( results );
//			// TODO: 1/25/2019 from here calculate combinations inside the hotel rooms.
//
//
//		}

		return results;
	}

	private HashMap<Integer, List<SearchQueryDTO>> groupByHotelId( List<SearchQueryDTO> searchQueryDTOS )
	{
		HashMap<Integer, List<SearchQueryDTO>> resultsGroupByHotelId = new HashMap<>();

		searchQueryDTOS.forEach( result -> {

					if ( resultsGroupByHotelId.containsKey( result.getHotelId() ) )
					{
						resultsGroupByHotelId.get( result.getHotelId() ).add( result );
					}
					else
					{
						ArrayList<SearchQueryDTO> roomCombinationsInHotel = new ArrayList<>();
						roomCombinationsInHotel.add( result );
						resultsGroupByHotelId.put( result.getHotelId(), roomCombinationsInHotel );
					}

				}
		);
		return resultsGroupByHotelId;

	}

}
