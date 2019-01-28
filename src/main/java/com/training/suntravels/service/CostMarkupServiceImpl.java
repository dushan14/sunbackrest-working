package com.training.suntravels.service;

import com.training.suntravels.util.Util;
import org.springframework.stereotype.Service;

@Service
public class CostMarkupServiceImpl implements CostMarkupService
{

	private double markupPercentage = 0.15;

	@Override
	public double getMarkupPrice( double cost )
	{
		double price = cost * ( 1 + markupPercentage );
		return Util.roundTo2Decimals( price );
	}

	public double getCost( int noOfRooms, int noOfAdults, int noOfNights, double unitPricePerAdult )
	{
		return Util.roundTo2Decimals( noOfRooms * noOfAdults * noOfNights * unitPricePerAdult );
	}

	@Override
	public double getCalculatedCostMarkup( int noOfRooms, int noOfAdults, int noOfNights, double unitPricePerAdult )
	{
		double cost = getCost( noOfRooms, noOfAdults, noOfNights, unitPricePerAdult );

		return Util.roundTo2Decimals( getMarkupPrice( cost ) );
	}
}
