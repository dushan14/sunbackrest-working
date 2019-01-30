package com.training.suntravels.util;

import org.apache.commons.math3.util.Precision;

import java.util.Calendar;
import java.util.Date;

public class Util
{
	public static double roundTo2Decimals( double d )
	{
		return Precision.round( d, 2 );
	}

	public static Date addDaysToDate( Date date, int days )
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime( date );
		cal.add( Calendar.DATE, days );
		return cal.getTime();
	}
}
