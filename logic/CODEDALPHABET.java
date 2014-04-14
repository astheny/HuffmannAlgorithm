package logic;

import java.math.*;

public class CODEDALPHABET {

	private int alphabetlength;
	private double[] probability;
	private int[] codednumber;
	private int codedalphabetlength;

	CODEDALPHABET()
	{
		alphabetlength = 1;
		probability = new double[1];
		probability[0] = 1;
		codednumber = new int[1];
		codednumber[0] = 0;
		codedalphabetlength = 1;
	}
	
	CODEDALPHABET(int alength, double[] probabilityvector, int calength)
	{
		alphabetlength = alength;
		probability = probabilityvector;
		codedalphabetlength = calength;
		codednumber = new int[1];
		codednumber[0] = 0;		
	}
	
	CODEDALPHABET(int alength, double[] probabilityvector,int[] cnumbers, int calength)
	{
		probability = probabilityvector;
		alphabetlength = alength;
		codednumber = cnumbers;
		codedalphabetlength = calength;
	}
	
	
	/*
	 *  Calculates the length of the longest word coded
	 */
	public int getMaxLength()
	{
		int retval = 0;
		if(!CheckProbability())
			return retval;
		for(int i = 0; i< codednumber.length; i++)	
			if(retval < Integer.toBinaryString(codednumber[i]).length())
				retval = Integer.toBinaryString(codednumber[i]).length();
		return retval;
	}

	/*
	 *  Calculates the average length of a coded word
	 */
	public double getAverageLength()
	{
		double retval = 0;
		if(!CheckProbability())
			return retval;		
		for(int i = 0; i < probability.length; i++)
			retval += probability[i]*Integer.toBinaryString(codednumber[i]).length();
		return retval;
	}
	
	/*
	 *  Calculates the entropy of the codedalphabet
	 */	
	public double getEntropy()
	{
		int r = codedalphabetlength;
		double retval = 0;
		if(!CheckProbability() || r < 2)
			return retval;
		for(int i = 0; i < probability.length; i++)		
			if(probability[i] != 0)
				retval += (Math.log(probability[i])/Math.log(r));
		return -retval;		
	}
	
	/*
	 *  Checks if all values are well defined
	 */
	public boolean CheckProbability()
	{
		int i = probability.length;
		if (i != alphabetlength)
			return false; //not enough values in probability
		if(codedalphabetlength < 2)
			return false; 
		if(i!= codednumber.length)
			return false;
		double sum = 0;
		for(int j = 0; j < i; j++)
			sum += probability[j];		
		if(sum != 1) //TODO: allow discpreancy? 
			return false; // no probaility distribution
		return true;
	}
	
	public boolean DoTheAlgorithm()
	{
		if(!CheckProbability())
			return false;
		int rest = alphabetlength%codedalphabetlength;
		//Fill probability array with dummy values
		
		return false;
	}
	
}
