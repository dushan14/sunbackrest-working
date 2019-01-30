package com.training.suntravels.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchQueryDTO implements Comparable<SearchQueryDTO>, Cloneable
{

	private int hotelId;

	private int roomTypeId;

	private int noOfRooms;

	private int availableRooms;

	private int adultsPerRoom;

	private String hotel;

	private String roomType;

	private double unitPricePerAdult;

	private String currency;

	public SearchQueryDTO( int hotelId, int roomTypeId, int noOfRooms, int availableRooms, int adultsPerRoom, String hotel, String roomType, double unitPricePerAdult, String currency )
	{
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.noOfRooms = noOfRooms;
		this.availableRooms = availableRooms;
		this.adultsPerRoom = adultsPerRoom;
		this.hotel = hotel;
		this.roomType = roomType;
		this.unitPricePerAdult = unitPricePerAdult;
		this.currency = currency;
	}

	public SearchQueryDTO cloneObj( SearchQueryDTO queryDTO )
	{
		return new SearchQueryDTO( queryDTO.getHotelId(), queryDTO.roomTypeId, queryDTO.noOfRooms, queryDTO.availableRooms, queryDTO.adultsPerRoom, queryDTO.hotel, queryDTO.roomType, queryDTO.unitPricePerAdult, queryDTO.currency );
	}

	public SearchQueryDTO( int hotelId, int roomTypeId, int noOfRooms, int adultsPerRoom )
	{
		this.availableRooms = noOfRooms;

		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.noOfRooms = noOfRooms;
		this.adultsPerRoom = adultsPerRoom;
	}

	public SearchQueryDTO( int hotelId, int roomTypeId, int noOfRooms, int adultsPerRoom, String hotel, String roomType, double unitPricePerAdult, String currency )
	{
		this.availableRooms = noOfRooms;

		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.noOfRooms = noOfRooms;
		this.adultsPerRoom = adultsPerRoom;
		this.hotel = hotel;
		this.roomType = roomType;
		this.unitPricePerAdult = unitPricePerAdult;
		this.currency = currency;
	}

	public SearchQueryDTO( int hotelId, int roomTypeId, int noOfRooms, int adultsPerRoom, String hotel, String roomType, int unitPricePerAdult, String currency )
	{
		this.availableRooms = noOfRooms;

		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
		this.noOfRooms = noOfRooms;
		this.adultsPerRoom = adultsPerRoom;
		this.hotel = hotel;
		this.roomType = roomType;
		this.unitPricePerAdult = unitPricePerAdult;
		this.currency = currency;
	}

	public void allocate( int noOfRoomsAllocating )
	{
		this.availableRooms -= noOfRoomsAllocating;
	}

	@Override
	public int compareTo( SearchQueryDTO o )
	{
		if ( unitPricePerAdult == o.unitPricePerAdult )
			return 0;
		else if ( unitPricePerAdult > o.unitPricePerAdult )
			return 1;
		else
			return -1;
	}

}
