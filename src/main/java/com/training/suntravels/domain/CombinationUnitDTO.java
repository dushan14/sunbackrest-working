package com.training.suntravels.domain;

import lombok.Data;

@Data
public class CombinationUnitDTO
{
	private RoomAdultCondition condition;

	private SearchResultRoomDTO roomDetails;

	public CombinationUnitDTO( RoomAdultCondition condition, SearchResultRoomDTO resultRoom )
	{
		this.condition = condition;
		this.roomDetails = resultRoom;
	}
}
