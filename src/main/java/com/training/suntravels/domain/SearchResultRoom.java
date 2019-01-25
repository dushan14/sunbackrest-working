package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultRoom
{
	private int hotelId;
	private String hotel;

	private String roomType;
	private int roomTypeId;

	private int searchedNights;
	private int searchedAdultsPerRoom;
	private int searchedNoOfRooms;
	private double price;

	public SearchResultRoom( int searchedNights, int searchedAdultsPerRoom, int searchedNoOfRooms )
	{
		this.searchedNights = searchedNights;
		this.searchedAdultsPerRoom = searchedAdultsPerRoom;
		this.searchedNoOfRooms = searchedNoOfRooms;
	}
}
