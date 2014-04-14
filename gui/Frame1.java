package gui;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.*;
import java.io.*;
import java.util.*;
import java.math.*;

class Frame1 extends JFrame implements ActionListener {
	JTextArea inputtextfield = new JTextArea(8,20);	
	JButton submitbutton = new JButton("Algorithmus ausführen");
	JTextArea duplicatetextfield = new JTextArea(1,10);
	JButton buttonaddtext = new JButton("Zeichen hinzufügen");
	JLabel resultslabel = new JLabel("");
	JLabel calLabel = new JLabel("Codealphabetlänge");
	JComboBox calCBox = new JComboBox();
	JComboBox calCBox2 = new JComboBox();	
	JLabel spacer;
	
	public Frame1(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.rowHeights = new int[]{0, 0, 500};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{500, 100, 50, 100, 0};
		getContentPane().setLayout(gridBagLayout);
		setTitle("Huffman Algorithmus");
		GridBagConstraints gridbagconstrscroll = new GridBagConstraints();
		GridBagConstraints gridbagconstrscroll2 = new GridBagConstraints();
		GridBagConstraints gridbagconstrcalCBox2 = new GridBagConstraints();
		GridBagConstraints gridbagconstrbuttonaddtext = new GridBagConstraints();
		GridBagConstraints gridbagconstrcalLabel = new GridBagConstraints();
		GridBagConstraints gridbagconstrcalCBox = new GridBagConstraints();
		GridBagConstraints gridbagconstrsubmitbutton = new GridBagConstraints();
		GridBagConstraints gridbagconstrresultslabel = new GridBagConstraints();
		
		for(int i = 2; i<27; i++) {
			calCBox.addItem(i); 
			calCBox2.addItem(i);
		}		
		
		inputtextfield.setLineWrap(true);
		inputtextfield.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(inputtextfield,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gridbagconstrscroll.fill = GridBagConstraints.BOTH;
		gridbagconstrscroll.gridx = 0;
		gridbagconstrscroll.gridy = 0;
		gridbagconstrscroll.gridwidth = 1;
		gridbagconstrscroll.gridheight = 3;		
		getContentPane().add(scroll,gridbagconstrscroll);
		
		JScrollPane scroll2 = new JScrollPane(duplicatetextfield,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gridbagconstrscroll2.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrscroll2.gridx = 1;
		gridbagconstrscroll2.gridy = 0;
		gridbagconstrscroll2.gridwidth = 1;
		gridbagconstrscroll2.gridheight = 1;
		getContentPane().add(scroll2,gridbagconstrscroll2);
		
		gridbagconstrcalCBox2.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrcalCBox2.gridx = 2;
		gridbagconstrcalCBox2.gridy = 0;
		gridbagconstrcalCBox2.gridwidth = 1;
		gridbagconstrcalCBox2.gridheight = 1;
		getContentPane().add(calCBox2,gridbagconstrcalCBox2);
		
		gridbagconstrbuttonaddtext.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrbuttonaddtext.gridx = 3;
		gridbagconstrbuttonaddtext.gridy = 0;
		gridbagconstrbuttonaddtext.gridwidth = 1;
		gridbagconstrbuttonaddtext.gridheight = 1;
		getContentPane().add(buttonaddtext,gridbagconstrbuttonaddtext);
		
		gridbagconstrcalLabel.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrcalLabel.gridx = 1;
		gridbagconstrcalLabel.gridy = 1;
		gridbagconstrcalLabel.gridwidth = 1;
		gridbagconstrcalLabel.gridheight = 1;
		getContentPane().add(calLabel,gridbagconstrcalLabel);
		
		gridbagconstrcalCBox.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrcalCBox.gridx = 2;
		gridbagconstrcalCBox.gridy = 1;
		gridbagconstrcalCBox.gridwidth = 1;
		gridbagconstrcalCBox.gridheight = 1;
		getContentPane().add(calCBox,gridbagconstrcalCBox);
		
		gridbagconstrsubmitbutton.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrsubmitbutton.gridx = 3;
		gridbagconstrsubmitbutton.gridy = 1;
		gridbagconstrsubmitbutton.gridwidth = 1;
		gridbagconstrsubmitbutton.gridheight = 1;
		getContentPane().add(submitbutton,gridbagconstrsubmitbutton);

		gridbagconstrresultslabel.fill = GridBagConstraints.HORIZONTAL;
		gridbagconstrresultslabel.gridx = 4;
		gridbagconstrresultslabel.gridy = 1;
		gridbagconstrresultslabel.gridwidth = 1;
		gridbagconstrresultslabel.gridheight = 1;
		getContentPane().add(resultslabel,gridbagconstrresultslabel);
		
		submitbutton.addActionListener(this);
		buttonaddtext.addActionListener(this);
		inputtextfield.addMouseListener(new ContextMenuMouseListener());
		
		pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == submitbutton)
		{
			String inputstring = inputtextfield.getText().replace('\n', ' ').replace('\r',' ');
			HuffmanAlgorithm TheAlgorithm = new HuffmanAlgorithm(inputstring , calCBox.getSelectedIndex()+2);
			if(IsValidInputString(inputstring)){
				OUTPUT theResult = TheAlgorithm.getTheResult();
				String outpustring = BuildTheOutputString(theResult);
				infoBox(outpustring,"output");
			}
		}		
		
		if(e.getSource() == buttonaddtext)
		{
			int i = calCBox2.getSelectedIndex()+2 ;
			String s_fromtextfield = duplicatetextfield.getText();
			String s_toadd = "";
			for (int j = 0; j<i; j++)
			{
				s_toadd += s_fromtextfield;
			}
			inputtextfield.append(s_toadd);
		}
		//TODO: Popup: Invalid input string
		
	}
	
	private boolean IsValidInputString(String s)
	{		
		for(int i = 0; i < s.length(); i++ ){
			 char ch = Character.toUpperCase(s.charAt(i));
             if ((ch >= 'A') && (ch <= 'Z')){
            	return true;
             }
		}			
		return false;
	}
	
	private String BuildTheOutputString(OUTPUT TheOutput){
		String retval = "Buchstabe \tCodewort \tVorkommen \r\n";
		String[] preretval = new String[100];
		String part = "";
		int[] frequency = TheOutput.getTheFrequency();
		int total = 0;
		for(int i = 0; i< frequency.length; i++)
		{
			total += frequency[i];
		}
		double length = 0;
		double entropy = 0;
		HashMap map = TheOutput.getTheMap();
		int i = 0;
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        char value = (char) pairs.getKey();
	        if(pairs.getKey() != null)
	        {
	        	 String codedchar = (String) pairs.getValue();
	        	 if(!codedchar.isEmpty() && codedchar != null && value >= 'A' && value <='Z'){
	 				codedchar = codedchar.replace("|", "");				
	 				part =  value + "\t"+ codedchar + "\t" + frequency[value - 'A'] +"\r\n" ;
	 				double prob = frequency[value - 'A']*(1.0/total);
	 				length += codedchar.length() * prob;
	 				entropy +=  prob * Math.log(prob)/Math.log(calCBox.getSelectedIndex()+2);
	 				preretval[i]= part;
	 				i++;
	 			}
	       }
	        it.remove(); 
	    }
		
		for(int j = 'A'; j < 'Z'+1; j++){
					
			for(int k = 0; k < 26; k++){
				if(preretval[k] != null)
					if(preretval[k].charAt(0) == j){
						retval += preretval[k];
						break;
					}
			}
		}
		retval += "\r\nEntropie: \t" + -entropy + "\r\n" + "Länge: \t" + length + "\r\n" + "Redundanz: \t" + (length+entropy) ;
		return retval;
	}
	
	public static void infoBox(String infoMessage, String location)
    {
		JOptionPane.showMessageDialog(null, new JTextArea(infoMessage));
    }
	
}