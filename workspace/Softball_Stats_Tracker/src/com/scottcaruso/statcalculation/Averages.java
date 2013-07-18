package com.scottcaruso.statcalculation;

import java.text.NumberFormat;

public class Averages {
	
	private static NumberFormat threeDecimals = NumberFormat.getInstance();
	
	public static NumberFormat setFormatting(NumberFormat format,int numberOfDigits)
	{
		format.setMaximumFractionDigits(numberOfDigits);
		format.setMinimumFractionDigits(numberOfDigits);
		return format;		
	}

	public static float battingAverage(int atbats, int hits)
	{
		NumberFormat thisFormat = setFormatting(threeDecimals, 3);
		float ba = (float) hits/atbats;
		thisFormat.format(ba);
		
		return ba;
	}
	
	public static float sluggingPercentage(int atbats, int hits, int doubles, int triples, int homeruns)
	{
		NumberFormat thisFormat = setFormatting(threeDecimals, 3);
		int totalBases = Total_Bases.totalBases(hits, doubles, triples, homeruns);
		float slug = (float) atbats/totalBases;
		thisFormat.format(slug);
		
		return slug;
	}
	
}
