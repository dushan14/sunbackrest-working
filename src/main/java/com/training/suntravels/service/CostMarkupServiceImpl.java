package com.training.suntravels.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

//@PropertySource({ "classpath:business.properties" })
@Service
public class CostMarkupServiceImpl implements CostMarkupService
{

//	@Value("${markupPercentage}")
//	private double markupPercentage;

	@Override
	public double getMarkupPrice( double cost )
	{
//		double price =cost*(1+markupPercentage);
		double price =cost*(1+0.15);
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
