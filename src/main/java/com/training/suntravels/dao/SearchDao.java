package com.training.suntravels.dao;

import com.training.suntravels.domain.RoomAdultCondition;
import com.training.suntravels.domain.SearchQueryDTO;

import java.util.Date;
import java.util.List;

public interface SearchDao
{
	List<SearchQueryDTO> getResult( Date checkIn, int noOfNights, List<RoomAdultCondition> roomAdultPairs );
}
