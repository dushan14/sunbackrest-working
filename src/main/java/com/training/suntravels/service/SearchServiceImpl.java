package com.training.suntravels.service;

import com.training.suntravels.dao.SearchDao;
import com.training.suntravels.domain.*;
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
	public SearchResultDTO getSearchResult( SearchRequestDTO searchRequestDTO )
	{
		List<SearchQueryDTO> results = searchDao.getResult( searchRequestDTO.getCheckIn(), searchRequestDTO.getNoOfNights(), searchRequestDTO.getConditions() );

		SearchResultDTO searchResultDTO = new SearchResultDTO();
		searchResultDTO.setSearchRequest( searchRequestDTO );

		int noOfConstraints = searchRequestDTO.getConditions().size();

		if ( noOfConstraints == 1 )
		{


			int noOfRooms = searchRequestDTO.getConditions().get( 0 ).getNoOfRooms();
			int noOfAdults = searchRequestDTO.getConditions().get( 0 ).getNoOfAdults();
			int noOfNights = searchRequestDTO.getNoOfNights();

			for ( SearchQueryDTO searchQueryDTO : results )
			{
				CombinationDTO combinationDTO=new CombinationDTO();

				CombinationUnitDTO unitOfResult=new CombinationUnitDTO();
				unitOfResult.setCondition(  searchRequestDTO.getConditions().get( 0 ));

				SearchResultRoomDTO resultRoom = new SearchResultRoomDTO();
				resultRoom.setUnitPricePerNight( costMarkupService.getMarkupPrice( searchQueryDTO.getUnitPricePerAdult() ) );

				double markupPrice = costMarkupService.getCalculatedCostMarkup( noOfRooms, noOfAdults, noOfNights, searchQueryDTO.getUnitPricePerAdult() );
				resultRoom.setCurrentConditionPrice( markupPrice );

				resultRoom.setRoomCapacity( searchQueryDTO.getAdultsPerRoom() );

				resultRoom.setHotelId( searchQueryDTO.getHotelId() );
				resultRoom.setHotel( searchQueryDTO.getHotel() );

				resultRoom.setRoomType( searchQueryDTO.getRoomType() );
				resultRoom.setRoomTypeId( searchQueryDTO.getRoomTypeId() );

				unitOfResult.setRoomDetails( resultRoom );

				combinationDTO.addUnitResult( unitOfResult );
				combinationDTO.calculateTotalPrice();

				searchResultDTO.addCombination( combinationDTO );
			}

			searchResultDTO.sortCombinationsByTotalPrice();

			return searchResultDTO;

		}
		else
		{
			HashMap<Integer, List<SearchQueryDTO>> resultsGroupedByHotelId = groupByHotelId( results );
			// TODO: 1/25/2019 from here calculate combinations inside the hotel rooms.

			return searchResultDTO;

		}


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
