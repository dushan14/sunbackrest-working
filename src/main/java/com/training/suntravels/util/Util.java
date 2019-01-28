package com.training.suntravels.util;

import org.apache.commons.math3.util.Precision;

public class Util
{
	public static double roundTo2Decimals( double d )
	{
		return Precision.round( d, 2 );
	}
}
