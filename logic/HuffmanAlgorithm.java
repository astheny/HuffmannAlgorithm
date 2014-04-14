/**
 * @author Stefan Heyder
 * @date 24.05.2013
 * @version 0.1
 */

package logic;


import java.io.*;
import java.util.*;

//This class will implement the HuffmanAlgorithm
public class HuffmanAlgorithm {
	private INPUT input;
	private OUTPUT output;
	
	HuffmanAlgorithm()
	{
		input = new INPUT();
	}
	
	HuffmanAlgorithm(int alength)
	{
		input = new INPUT(alength);
	}
	
	HuffmanAlgorithm(int alength, double[] probability)
	{
		input = new INPUT(alength, probability);
	}
	
	public HuffmanAlgorithm(String StringToProcess, int codedalphabetlength)
	{
		OUTPUT theresult = processFile(StringToProcess,codedalphabetlength);
		output = theresult;
	}
	
	 private static OUTPUT processFile(String fileContents, int alphabetlength)
     {
             int[] frequency = new int['Z'-'A'+1];           // Frequency table of each letter
             TreeSet<Node> trees     = new TreeSet<Node>();  // List containing all trees -- ORDERED!

             // Build the frequency table of each letter
             for (int i=0; i<fileContents.length(); i++)
             {
                     char ch = Character.toUpperCase(fileContents.charAt(i));
                     if ((ch >= 'A') && (ch <= 'Z'))
                             ++frequency[ch - 'A'];
             }

             // Build up the initial trees
             for (int i=0; i<'Z'-'A'+1; i++)
             {
                     if (frequency[i] > 0)
                     {
                             Node n = new Node((char)('A'+i), frequency[i]);
                             trees.add(n);
                     }
             }
             int j = 1;
             if(alphabetlength != 2){
	             while(trees.size()%(alphabetlength-1) != 1){
	            	 Node n = new Node((char)('Z'+j),0);
	            	 trees.add(n); 
	            	 j++;
	             }
             }             
             

             // Huffman algoritm
             while (trees.size() > alphabetlength-1)
             {
            	 	 ArrayList treesToRemove = new ArrayList();
                     for(int i=0; i<alphabetlength;i++)
                     {
                    	 Node toAdd = (Node) trees.first();
                    	 treesToRemove.add(toAdd);
                    	 trees.remove(toAdd);
                     }

                     Node merged = new Node(treesToRemove);
                     trees.add(merged);
             }
             HashMap retval = new HashMap();
             // Print the resulting tree
             if(trees.size() >  0){
            	 Node theTree = (Node) trees.first();
                 trees.remove(theTree);
                 retval.putAll(Node.printTree(theTree,""));
             }           
             OUTPUT theOutput = new OUTPUT(retval, frequency);
             return theOutput;
     }
	
	 public OUTPUT getTheResult()
	 {
		 return output;
	 }
}
