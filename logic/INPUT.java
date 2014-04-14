package logic;

import java.util.ArrayList;
import java.util.List;

public class INPUT {
	private int alphabetlength; //Länge des zu verschlüßelnden Alphabets
	private double[] probability; // Vorkommen der Zeichen
	private int codedalphabetlength;
	
	// default constructor
	public INPUT()
	{
		alphabetlength = 1;
		probability = new double[1];
		probability[0] = 1;	
		codedalphabetlength = 2;
	}
	
	// creates a new INPUT object with parameter alength, each letter has same probability
	public INPUT(int alength)
	{
		alphabetlength = alength;
		probability = new double[alength];
		for(int i = 0; i < probability.length; i++)
			probability[i] = 1/alength;
		codedalphabetlength = 2;
	}
	
	// creates a new INPUT object from parameters alength, probabilityvector
	public INPUT(int alength, double[] probabilityvector)
	{
		alphabetlength = alength;
		probability = probabilityvector;
		codedalphabetlength = 2;
	}
	
	// creates a new INPUT object from parameters alength, probabilityvector
	public INPUT(int alength, double[] probabilityvector, int calphabetlength)
	{
		alphabetlength = alength;
		probability = probabilityvector;
		codedalphabetlength = calphabetlength;
	}
	
	//checks if the values stored in probability define a PD
	public boolean CheckProbability()
	{
		int i = probability.length;
		if (i != alphabetlength)
			return false; //not enough values in probability
		double sum = 0;
		for(int j = 0; j < i; j++)
			sum += probability[j];		
		if(sum != 1) //TODO: allow discpreancy? 
			return false; // no probaility distribution
		return true;
	}

	public int getAlphabetLength() 
	{
		return alphabetlength;
	}

	public double[] getProbabilityvector() 
	{
		return probability;
	}

	public int getCodedAlphabetLength() 
	{
		return codedalphabetlength;
	}
}
