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

//	private int hotelId;
//	private String hotel;

	private String roomType;
	private int roomTypeId;

	private int adultsPerRoom;

	private double unitPricePerNight;
	private double currentConditionPrice;

	public double getCurrentConditionPrice()
	{
		return Util.roundTo2Decimals( currentConditionPrice );
	}

	public void grabDetails( SearchQueryDTO searchQueryDTO){
		this.roomType=searchQueryDTO.getRoomType();
		this.roomTypeId=searchQueryDTO.getRoomTypeId();
		this.adultsPerRoom =searchQueryDTO.getAdultsPerRoom();

	}

}
