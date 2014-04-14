package logic;

import java.io.*;
import java.util.*;

class Node
        implements Comparable
{
        private int     value;
        private char    content;
        private ArrayList<Node> branches;

        public Node(char content, int value)
        {
                this.content  = content;
                this.value    = value;
        }

        public Node(ArrayList<Node> branches)
        {  
        		int value = 0;
        		Node smallest = new Node('a', 0);
        		if(branches != null && branches.size() > 0)
        			smallest = branches.get(0);
        		for(int j = 0; j < branches.size(); j++){
        			value += branches.get(j).value;
        			if(smallest.content > branches.get(j).content)
        				smallest = branches.get(j);
        		}
                this.content  = smallest.content;
                this.value    = value;
                this.branches = branches;
        }

        public int compareTo(Object arg)
        {
                Node other = (Node) arg;

                // Content value has priority and then the lowest letter
                if (this.value == other.value)
                        return this.content-other.content;
                else
                        return this.value-other.value;
        }

        ////////////////

        private HashMap printNode(HashMap hm, String path)
        {
        		if(branches == null)
        			hm.put(content,path);
        		else
        		{
        			for(int i = 0; i < branches.size(); i++)
        				branches.get(i).printNode(hm, path+'|'+i);
        		}
                return hm;                       

        }

        public static HashMap printTree(Node tree, String init)
        {
        		HashMap hm = new HashMap();
        		hm = tree.printNode(hm,init);
                return hm;
        }
}