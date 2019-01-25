package com.training.suntravels.service;

public interface CostMarkupService
{
	double getMarkupPrice( double cost );

	double getCost( int noOfRooms, int noOfAdults, int noOfNights, double unitPricePerAdult );

	double getCalculatedCostMarkup( int noOfRooms, int noOfAdults, int noOfNights, double unitPricePerAdult );
}
