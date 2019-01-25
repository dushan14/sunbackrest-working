package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResult
{

	private int resultId;

	private int hotelId;

	private String hotel;

	private int roomTypeId;

	private String roomType;

	private int availableRooms;

	private int adultsPerRoom;

	private double unitPricePerAdult;

	private String currency;

}
