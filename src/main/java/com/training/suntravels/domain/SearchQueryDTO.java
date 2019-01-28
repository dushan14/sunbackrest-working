package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchQueryDTO
{

	private int hotelId;

	private int roomTypeId;

	private int availableRooms;

	private int adultsPerRoom;

	private String hotel;

	private String roomType;

	private double unitPricePerAdult;

	private String currency;

	public SearchQueryDTO( int hotelId, int roomTypeId, int availableRooms, int adultsPerRoom )
	{
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.availableRooms = availableRooms;
		this.adultsPerRoom = adultsPerRoom;
	}

	public SearchQueryDTO( int hotelId, int roomTypeId, int availableRooms, int adultsPerRoom, String hotel, String roomType, double unitPricePerAdult, String currency )
	{
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.availableRooms = availableRooms;
		this.adultsPerRoom = adultsPerRoom;
		this.hotel = hotel;
		this.roomType = roomType;
		this.unitPricePerAdult = unitPricePerAdult;
		this.currency = currency;
	}

	public SearchQueryDTO( int hotelId, int roomTypeId, int availableRooms, int adultsPerRoom, String hotel, String roomType, int unitPricePerAdult, String currency )
	{
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.availableRooms = availableRooms;
		this.adultsPerRoom = adultsPerRoom;
		this.hotel = hotel;
		this.roomType = roomType;
		this.unitPricePerAdult = unitPricePerAdult;
		this.currency = currency;
	}
}
