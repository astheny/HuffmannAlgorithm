package logic;

import java.io.*;
import java.util.*;

public class OUTPUT {
	HashMap TheOutput;
	int[] frequency;
	
	OUTPUT(HashMap tOutput, int[] freq)
	{
		TheOutput = tOutput;
		frequency = freq;
	}
	
	public HashMap getTheMap(){
		return  TheOutput;
	}
	
	public int[] getTheFrequency(){
		return frequency;
	}
	
}
