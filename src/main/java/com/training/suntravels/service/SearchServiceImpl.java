package com.training.suntravels.service;

import com.training.suntravels.dao.SearchDao;
import com.training.suntravels.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.toList;

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
		List<RoomAdultCondition> conditions = searchRequestDTO.getConditions();

		ArrayList<SearchQueryDTO> results = ( ArrayList<SearchQueryDTO> ) searchDao.getResult( searchRequestDTO.getCheckIn(), searchRequestDTO.getNoOfNights(), conditions );

		// creating a list to work and allocate rooms
		ArrayList<SearchQueryDTO> clonedResults = ( ArrayList<SearchQueryDTO> ) results.clone();

		int noOfNights = searchRequestDTO.getNoOfNights();

		// Create searchResultDto by including searchRequestDto
		SearchResultDTO searchResultDTO = new SearchResultDTO( searchRequestDTO );

		int noOfConstraints = conditions.size();

		if ( noOfConstraints == 1 )
		{

			for ( SearchQueryDTO searchQueryDTO : clonedResults )
			{
				// create combinations for each allocations as it is one kondition
				CombinationDTO combinationDTO = new CombinationDTO( searchQueryDTO.getHotelId(), searchQueryDTO.getHotel() );

				// create units inside combination for each condition
				// here only condition 0 is available
				CombinationUnitDTO unitDTO = allocateSearchQueryDtoToConditionAndProduceCombinationUnit( conditions.get( 0 ), searchQueryDTO, noOfNights );
				combinationDTO.addUnitResult( unitDTO );

				// add combination for search allocations
				searchResultDTO.addCombination( combinationDTO );
			}

			// sort search allocations by combination price
			searchResultDTO.sortCombinationsByTotalPriceAndFinalizeSearchResult();

			return searchResultDTO;

		}
		else
		{
			HashMap<Integer, List<SearchQueryDTO>> resultsGroupedByHotelId = groupByHotelId( clonedResults );

			HashMap<Integer, List<SearchQueryDTO>> resultsGroupedByHotelIdClone = ( HashMap<Integer, List<SearchQueryDTO>> ) resultsGroupedByHotelId.clone();

			// sort conditions for optimizing room allocation
			Collections.sort( conditions );

			for ( Map.Entry<Integer, List<SearchQueryDTO>> hotelResult : resultsGroupedByHotelIdClone.entrySet() )
			{
				//	setLowPriceSolutionFromHotel( conditions, noOfNights, searchResultDTO, hotelResult.getValue() );

				setAllSolutionsForHotel( searchResultDTO, conditions, hotelResult.getValue(), noOfNights );

			}

			// sort search allocations by combination price
			searchResultDTO.sortCombinationsByTotalPriceAndFinalizeSearchResult();

			return searchResultDTO;

		}

	}

	private SearchResultDTO setLowPriceSolutionFromHotel( List<RoomAdultCondition> conditions, int noOfNights, SearchResultDTO searchResultDTO, List<SearchQueryDTO> hotelResult )
	{
		// getting combination for the hotel if the hotel can satisfy
		CombinationDTO combinationDTO = checkAndAssignHotelForEveryConditionIfSatisfy( hotelResult, conditions, noOfNights );

		if ( combinationDTO != null )
		{
			searchResultDTO.addCombination( combinationDTO );
		}

		return searchResultDTO;
	}

	private SearchResultDTO setAllSolutionsForHotel( SearchResultDTO searchResultDTO, List<RoomAdultCondition> sortedConditions, List<SearchQueryDTO> hotelResult, int noOfNights )
	{

		List<CombinationDTO> combinationDTOList = new ArrayList<>();
		CombinationDTO combinationDTO = new CombinationDTO( hotelResult.get( 0 ).getHotelId(), hotelResult.get( 0 ).getHotel() );

		List<RoomAdultCondition> clonedConditions = ( List<RoomAdultCondition> ) ( ( ArrayList ) sortedConditions ).clone();

		// recursive function to find all combinations and add them into list

		checkHotelForEveryCombinationAndAddCombinationsToList( clonedConditions, hotelResult, noOfNights, combinationDTO, combinationDTOList );

		searchResultDTO.addCombinations( combinationDTOList );
		return searchResultDTO;
	}

	private CombinationDTO checkAndAssignHotelForEveryConditionIfSatisfy( List<SearchQueryDTO> hotelResult, List<RoomAdultCondition> sortedConditions, int noOfNights )
	{
		// sort hotel room results by price
		Collections.sort( hotelResult );

		CombinationDTO combinationDTO = new CombinationDTO( hotelResult.get( 0 ).getHotelId(), hotelResult.get( 0 ).getHotel() );

		for ( RoomAdultCondition condition : sortedConditions )
		{
			boolean satisfiedForCondition = false;
			for ( SearchQueryDTO queryDTO : hotelResult )
			{
				if ( isSatisfyCondition( condition, queryDTO ) )
				{
					CombinationUnitDTO unitDTO = allocateSearchQueryDtoToConditionAndProduceCombinationUnit( condition, queryDTO, noOfNights );
					combinationDTO.addUnitResult( unitDTO );

					satisfiedForCondition = true;
					break;
				}
			}
			if ( !satisfiedForCondition )
			{

				// skip this hotel
				return null;
			}
		}
		// came satisfying all conditions
		return combinationDTO;

	}

	private void checkHotelForEveryCombinationAndAddCombinationsToList( List<RoomAdultCondition> sortedConditions, List<SearchQueryDTO> availabilitiesOfRoomTypes, int noOfNights, CombinationDTO combinationDTO, List<CombinationDTO> combinationDTOList )
	{
		if ( sortedConditions.size() > 0 )
		{
			RoomAdultCondition condition = sortedConditions.get( 0 );

			List<SearchQueryDTO> satisfyingRoomTypesForCondition = getSatisfyingRoomTypesForCondition( condition, availabilitiesOfRoomTypes );

			Collections.sort( satisfyingRoomTypesForCondition );

			List<RoomAdultCondition> sortedConditionsClonedFresh = cloneConditionList( sortedConditions );

			// this needs cloned objects inside list not only list cloning
			List<SearchQueryDTO> satisfyingRoomTypesForConditionClonedFresh = cloneAvailabilityList( satisfyingRoomTypesForCondition );

			for ( int i = 0; i < satisfyingRoomTypesForCondition.size(); i++ )
			{

				// to always start with a new unallocated satisfyingList and a un removed conditionsList in iterations
				// previous loop iterations means another combination. So this needs to go with fresh lists given by method.
				// but it should reduce in recursively to maintain combination availability
				List<RoomAdultCondition> sortedConditionsCloned = cloneConditionList( sortedConditionsClonedFresh );

				// this needs cloned objects inside list not only list cloning
				List<SearchQueryDTO> satisfyingRoomTypesForConditionCloned = cloneAvailabilityList( satisfyingRoomTypesForConditionClonedFresh );

				SearchQueryDTO queryDTO = satisfyingRoomTypesForConditionCloned.get( i );

				CombinationUnitDTO unitDTO = allocateSearchQueryDtoToConditionAndProduceCombinationUnit( condition, queryDTO, noOfNights );

				CombinationDTO newCombination = new CombinationDTO( queryDTO.getHotelId(), queryDTO.getHotel() );

				newCombination.addSetOfResults( combinationDTO.getAllocations() );

				newCombination.addUnitResult( unitDTO );

				sortedConditionsCloned.remove( 0 );
				if ( sortedConditionsCloned.size() == 0 )
				{
					// adding complete combination when every condition has a match
					combinationDTOList.add( newCombination );
				}

				// recursive way to go to next condition
				checkHotelForEveryCombinationAndAddCombinationsToList( sortedConditionsCloned, satisfyingRoomTypesForConditionCloned, noOfNights, newCombination, combinationDTOList );

			}

		}

	}

	private List<RoomAdultCondition> cloneConditionList( List<RoomAdultCondition> conditions )
	{

		// no need to clone inner objects here
		List<RoomAdultCondition> clonedList = ( List<RoomAdultCondition> ) ( ( ArrayList<RoomAdultCondition> ) conditions ).clone();
		return clonedList;
	}

	private List<SearchQueryDTO> cloneAvailabilityList( List<SearchQueryDTO> queryDTOS )
	{

		// need to clone inner objects here
		List<SearchQueryDTO> clonedList = queryDTOS.stream().map( queryDTO -> queryDTO.cloneObj( queryDTO ) ).collect( toList() );
		return clonedList;
	}

	private void cleanResultForAvailability( List<CombinationDTO> combinationDTOList, List<RoomAdultCondition> conditions )
	{

		for ( Iterator<CombinationDTO> it = combinationDTOList.iterator(); it.hasNext(); )
		{
			CombinationDTO combinationDTO = it.next();
			if ( combinationDTO.getAllocations().size() != conditions.size() )
			{
				it.remove();
			}
		}
	}

	private CombinationUnitDTO allocateSearchQueryDtoToConditionAndProduceCombinationUnit( RoomAdultCondition condition, SearchQueryDTO searchQueryDTO, int noOfNights )
	{

		SearchResultRoomDTO resultRoom = new SearchResultRoomDTO();
		resultRoom.setUnitPricePerNight( costMarkupService.getMarkupPrice( searchQueryDTO.getUnitPricePerAdult() ) );

		double markupPrice = costMarkupService.getCalculatedCostMarkup( condition.getNoOfRooms(), condition.getNoOfAdults(), noOfNights, searchQueryDTO.getUnitPricePerAdult() );
		resultRoom.setCurrentConditionPrice( markupPrice );

		resultRoom.grabDetails( searchQueryDTO );

		CombinationUnitDTO combinationUnitDTO = new CombinationUnitDTO( condition, resultRoom );

		// reduce allocating rooms from available room count
		searchQueryDTO.allocate( condition.getNoOfRooms() );

		return combinationUnitDTO;
	}

	public boolean isSatisfyCondition( RoomAdultCondition condition, SearchQueryDTO queryDTO )
	{
		// condition checks equal or greater than noOfAdults
		if ( condition.getNoOfAdults() <= queryDTO.getAdultsPerRoom() && condition.getNoOfRooms() <= queryDTO.getAvailableRooms() )
		{
			return true;
		}
		else
			return false;
	}

	public List<SearchQueryDTO> getSatisfyingRoomTypesForCondition( RoomAdultCondition condition, List<SearchQueryDTO> queryDTOS )
	{
		List<SearchQueryDTO> satisfyingRoomTypes = new ArrayList<>();
		for ( SearchQueryDTO queryDTO : queryDTOS )
		{
			if ( isSatisfyCondition( condition, queryDTO ) )
			{
				satisfyingRoomTypes.add( queryDTO );
			}
		}
		return satisfyingRoomTypes;

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
