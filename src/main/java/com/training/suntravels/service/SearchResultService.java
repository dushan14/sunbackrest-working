package com.training.suntravels.service;

import com.training.suntravels.domain.RoomAdultPair;
import com.training.suntravels.domain.SearchRequest;
import com.training.suntravels.domain.SearchResult;

import java.util.Date;
import java.util.List;

public interface SearchResultService
{
	List<SearchResult> getSearchResult( SearchRequest searchRequest );


}
