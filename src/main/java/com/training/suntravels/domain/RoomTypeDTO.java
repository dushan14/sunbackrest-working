package com.training.suntravels.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class RoomTypeDTO
{
	int id;

	String name;

	int adultsPerRoom;

	int availableRooms;

	@JsonIgnore
	double price;

	double markupPrice;

	String currency;

	public RoomTypeDTO( int id, String name, int adultsPerRoom, int availableRooms, int price, String currency )
	{
		this.id = id;
		this.name = name;
		this.adultsPerRoom = adultsPerRoom;
		this.availableRooms = availableRooms;
		this.price = price;
		this.currency = currency;
	}

	public RoomTypeDTO( int id, String name, int adultsPerRoom, int availableRooms, double price, String currency )
	{
		this.id = id;
		this.name = name;
		this.adultsPerRoom = adultsPerRoom;
		this.availableRooms = availableRooms;
		this.price = price;
		this.currency = currency;
	}
}
