package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomAdultPair;
import com.training.suntravels.domain.SearchQueryDTO;

import java.util.Date;
import java.util.List;

public interface SearchDao
{
	List<SearchQueryDTO> getResult( Date checkIn, int noOfNights, List<RoomAdultPair> roomAdultPairs );
}
