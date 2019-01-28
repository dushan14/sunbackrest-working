package com.training.suntravels.domain;

import com.training.suntravels.util.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultRoomDTO
{

	private int hotelId;
	private String hotel;

	private String roomType;
	private int roomTypeId;

	private int roomCapacity;

	private double unitPricePerNight;
	private double currentConditionPrice;

	public double getCurrentConditionPrice()
	{
		return Util.roundTo2Decimals( currentConditionPrice );
	}

}
