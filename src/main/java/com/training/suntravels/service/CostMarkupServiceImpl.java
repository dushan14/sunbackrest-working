package com.training.suntravels.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:business.properties" })
public class CostMarkupServiceImpl implements CostMarkupService
{

	@Value("${markupPercentage}")
	private double markupPercentage;

	@Override
	public double getMarkupPrice( double cost )
	{
		double price =cost*(1+markupPercentage);
		return price;
	}

	public double getCost( int noOfRooms, int noOfAdults, int noOfNights, double unitPricePerAdult )
	{
		return noOfRooms*noOfAdults*noOfNights*unitPricePerAdult;
	}

	@Override
	public double getCalculatedCostMarkup( int noOfRooms, int noOfAdults, int noOfNights, double unitPricePerAdult )
	{
		double cost=getCost( noOfRooms, noOfAdults,  noOfNights, unitPricePerAdult);

		return getMarkupPrice( cost );
	}
}
