/* Scott Caruso
 * Java I 1307
 * Week 3 Assignment
 */
package com.scottcaruso.statcalculation;

public class Averages {
	
	//Calculate batting average
	public static float battingAverage(int atbats, int hits)
	{
		float ba = (float) hits/atbats;	
		return ba;
	}
	
	//Use the Total_Bases ENUM to generate a slugging percentage
	public static float sluggingPercentage(int atbats, int hits, int doubles, int triples, int homeruns)
	{
		int totalBases = Total_Bases.totalBases(hits, doubles, triples, homeruns);
		float slug = (float) totalBases/atbats;
		return slug;
	}
	
}
