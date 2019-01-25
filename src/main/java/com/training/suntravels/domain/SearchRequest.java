package com.training.suntravels.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SearchRequest
{
	private Date checkIn;
	private int noOfNights;
	private List<RoomAdultPair> roomAdultPairs;
}
