package com.training.suntravels.service;

import com.training.suntravels.domain.SearchRequestDTO;
import com.training.suntravels.domain.SearchResultDTO;

public interface SearchService
{
	SearchResultDTO getSearchResult( SearchRequestDTO searchRequestDTO );


}
