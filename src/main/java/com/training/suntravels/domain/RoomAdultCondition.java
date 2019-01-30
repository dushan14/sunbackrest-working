package com.training.suntravels.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAdultCondition implements Comparable<RoomAdultCondition>
{
	private int noOfAdults;
	private int noOfRooms;

	@Override
	public int compareTo( RoomAdultCondition o )
	{
		if ( noOfAdults == o.noOfAdults )
			return 0;
		else if ( noOfAdults > o.noOfAdults )
			return 1;
		else
			return -1;
	}
}
