package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchQueryDTO
{

//	private int resultId;

	private int hotelId;

//	private String hotel;

	private int roomTypeId;

//	private String roomType;

	private int availableRooms;

	private int adultsPerRoom;

//	private double unitPricePerAdult;
//
//	private String currency;

	public SearchQueryDTO( int hotelId, int roomTypeId, int availableRooms, int adultsPerRoom )
	{
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.availableRooms = availableRooms;
		this.adultsPerRoom = adultsPerRoom;
	}
}
