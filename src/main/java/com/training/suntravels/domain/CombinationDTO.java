package com.training.suntravels.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CombinationDTO implements Comparable<CombinationDTO>,Cloneable
{
	int hotelId;

	String hotel;

	private List<CombinationUnitDTO> allocations = new ArrayList<>();

	private double totalPrice;

	public CombinationDTO( int hotelId, String hotel )
	{
		this.hotelId = hotelId;
		this.hotel = hotel;
	}

	public void calculateTotalPrice()
	{
		for ( CombinationUnitDTO unitDTO : this.allocations )
		{
			totalPrice += unitDTO.getRoomDetails().getCurrentConditionPrice();
		}
	}

	public void addUnitResult( CombinationUnitDTO unitOfResult )
	{
		this.allocations.add( unitOfResult );
	}

	@Override
	public int compareTo( CombinationDTO o )
	{
		if ( totalPrice == o.totalPrice )
			return 0;
		else if ( totalPrice > o.totalPrice )
			return 1;
		else
			return -1;
	}

	public void addSetOfResults( List<CombinationUnitDTO> result )
	{
		this.allocations.addAll( result );
	}

	public Object clone()throws CloneNotSupportedException{
		return super.clone();
	}

}
