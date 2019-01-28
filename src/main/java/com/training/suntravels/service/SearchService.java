package com.training.suntravels.service;

import com.training.suntravels.domain.SearchQueryDTO;
import com.training.suntravels.domain.SearchRequest;

import java.util.List;

public interface SearchService
{
	List<SearchQueryDTO> getSearchResult( SearchRequest searchRequest );


}
