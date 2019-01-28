package com.training.suntravels.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class SearchResultDTO
{
	private SearchRequestDTO searchRequest;

	private List<CombinationDTO> resultCombinations = new ArrayList<>();

	public void addCombination(CombinationDTO combinationDTO){
		this.resultCombinations.add( combinationDTO );
	}

	public void sortCombinationsByTotalPrice()
	{
		Collections.sort( resultCombinations );
	}
}
