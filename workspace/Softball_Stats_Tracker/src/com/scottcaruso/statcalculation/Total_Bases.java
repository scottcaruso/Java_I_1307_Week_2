/* Scott Caruso
 * Java I 1307
 * Week 3 Assignment
 */
package com.scottcaruso.statcalculation;


//The Total Bases stat is used to calculate slugging percentages. The number of bases per hit type is static and will never change.
public enum Total_Bases {

	SINGLE(1),
	DOUBLE(2),
	TRIPLE(3),
	HOMERUN(4);
	
	private final int numberOfBases;
	
	Total_Bases(int value)
	{
		this.numberOfBases = value;
	}
	
	public static int totalBases(int hits, int doubles, int triples, int homeruns)
	{
		int singles = (hits-doubles-triples-homeruns);	
		int totalBases = singles + (doubles*DOUBLE.numberOfBases) + (triples*TRIPLE.numberOfBases) + (homeruns*HOMERUN.numberOfBases);
		
		return totalBases;
	}
}
