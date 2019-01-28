package com.training.suntravels.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CombinationDTO implements Comparable<CombinationDTO>
{
	private List<CombinationUnitDTO> result = new ArrayList<>();

	private double totalPrice;

	public void calculateTotalPrice(){
		for(CombinationUnitDTO unitDTO:this.result){
			totalPrice+=unitDTO.getRoomDetails().getCurrentConditionPrice();
		}
	}

	public void addUnitResult( CombinationUnitDTO unitOfResult )
	{
		this.result.add( unitOfResult );
	}

	@Override
	public int compareTo( CombinationDTO o )
	{
		if(totalPrice==o.totalPrice)
			return 0;
		else if(totalPrice>o.totalPrice)
			return 1;
		else
			return -1;
	}
}
