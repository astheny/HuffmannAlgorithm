package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Font;

import logic.*;

import java.io.*;
import java.util.*;
import java.math.*;
import javax.swing.JTextPane;

public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtDuplicate;
	private JButton btnBerechnen;
	private JButton btnHinzufgen;
	private JComboBox cBoxCodeAlphLength;
	private JComboBox cBoxReplicationNumber;
	private JTextArea txtInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Huffman Algorithmus");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodealphabetlnge = new JLabel("Codealphabetl\u00E4nge");
		
		cBoxCodeAlphLength = new JComboBox();		
		cBoxReplicationNumber = new JComboBox();
		
		for(int i = 2; i<27; i++) {
			cBoxCodeAlphLength.addItem(i); 
			cBoxReplicationNumber.addItem(i);
		}
		
		btnBerechnen = new JButton("Berechnen");
		
		btnHinzufgen = new JButton("Hinzuf\u00FCgen");
		
		txtDuplicate = new JTextField();
		txtDuplicate.setColumns(10);
		
		txtInput = new JTextArea();
		txtInput.setLineWrap(true);
		txtInput.setWrapStyleWord(true);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtInput, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodealphabetlnge)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cBoxCodeAlphLength, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtDuplicate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cBoxReplicationNumber, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnBerechnen)
							.addGap(8))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnHinzufgen)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodealphabetlnge)
								.addComponent(cBoxCodeAlphLength, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBerechnen))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cBoxReplicationNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnHinzufgen)
								.addComponent(txtDuplicate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(txtInput, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)))
					.addContainerGap())
		);

		btnHinzufgen.addActionListener(this);
		btnBerechnen.addActionListener(this);
		txtInput.addMouseListener(new ContextMenuMouseListener());
		contentPane.setLayout(gl_contentPane);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnBerechnen)
		{
			String inputstring = txtInput.getText().replace('\n', ' ').replace('\r',' ');
			HuffmanAlgorithm TheAlgorithm = new HuffmanAlgorithm(inputstring , cBoxCodeAlphLength.getSelectedIndex()+2);
			if(IsValidInputString(inputstring)){
				OUTPUT theResult = TheAlgorithm.getTheResult();
				String outpustring = BuildTheOutputString(theResult);
				infoBox(outpustring,"output");
			}
		}		
		
		if(e.getSource() == btnHinzufgen)
		{
			int i = cBoxReplicationNumber.getSelectedIndex()+2 ;
			String s_fromtextfield = txtDuplicate.getText();
			String s_toadd = "";
			for (int j = 0; j<i; j++)
			{
				s_toadd += s_fromtextfield;
			}
			txtInput.append(s_toadd);
		} 
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
	 				entropy +=  prob * Math.log(prob)/Math.log(cBoxCodeAlphLength.getSelectedIndex()+2);
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
