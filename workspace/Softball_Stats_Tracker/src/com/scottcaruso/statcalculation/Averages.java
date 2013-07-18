/* Scott Caruso - Java 1307 - Week 2 Assignment
 * 
 * 7/18/2013
 */
package com.scottcaruso.statcalculation;

import java.text.NumberFormat;


//This class is not used in this version, but will be used later on. It will generate Batting and Slugging averages based on real data.
public class Averages {
	
	private static NumberFormat threeDecimals = NumberFormat.getInstance();
	
	//Formatting doesn't work in Week 2- it will be working when the data is fully surfaced in later weeks.
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
