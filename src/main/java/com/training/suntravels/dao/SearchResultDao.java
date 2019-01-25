package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomAdultPair;
import com.training.suntravels.domain.SearchResult;

import java.util.Date;
import java.util.List;

public interface SearchResultDao
{
	List<SearchResult> getResult( Date checkIn, int noOfNights, List<RoomAdultPair> roomAdultPairs );
}
