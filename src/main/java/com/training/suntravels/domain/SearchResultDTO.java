package com.training.suntravels.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class SearchResultDTO
{
	private SearchRequestDTO searchRequest;

	private int resultCombinationsSize;

	private List<CombinationDTO> resultCombinations = new ArrayList<>();

	public SearchResultDTO( SearchRequestDTO searchRequest )
	{
		this.searchRequest = searchRequest;
	}

	public void addCombination( CombinationDTO combinationDTO )
	{
		combinationDTO.calculateTotalPrice();
		this.resultCombinations.add( combinationDTO );
	}

	public void addCombinations( List<CombinationDTO> combinationDTOS )
	{
		combinationDTOS.forEach( combinationDTO -> combinationDTO.calculateTotalPrice() );
		this.resultCombinations.addAll( combinationDTOS );
	}

	public void sortCombinationsByTotalPriceAndFinalizeSearchResult()
	{
		Collections.sort( resultCombinations );
		this.resultCombinationsSize = resultCombinations.size();
	}
}
