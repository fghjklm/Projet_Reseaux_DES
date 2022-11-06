package Interface_graphique;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Des.Des;
import Des.TripleDes;

public class FenetreDecrypter extends JFrame implements MouseListener {
	
	private JLabel l1, l2;
	private JTextField jt1, jt2;
	private JButton b;
	public FenetreDecrypter() {

		super("Decryptage d'un message");
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(2,1));
		JPanel p2 = new JPanel(new GridLayout(2,1));
		this.l1 = new JLabel("Fichier du message crypté");
		this.l2 = new JLabel("Fichier des clés");

		this.jt1 = new JTextField();
		this.jt2 = new JTextField();

		this.b = new JButton("envoyer");
		p1.add(l1);
		p1.add(l2);
		p2.add(jt1);
		p2.add(jt2);
		this.add(p1,BorderLayout.WEST);
		this.add(p2,BorderLayout.CENTER);
		this.add(b,BorderLayout.SOUTH);
		b.addMouseListener(this);
		this.setSize(400,250);
		this.setVisible(true);
	}
	
	public int[] StringToTab(String s) {
		String[] tab_string = s.split(",");
		int[] tab = new int[tab_string.length];
		for(int i =0;i<tab.length;i++) {
			tab[i] = Integer.valueOf(tab_string[i]);
		}
		return tab;
	}
	
	public int[][] StringToTabTab(String s) {
		String[] tab_string = s.split("b");
		int[][] tab_tab = new int[tab_string.length][];
		for(int i =0;i<tab_tab.length;i++) {
			tab_tab[i] = StringToTab(tab_string[i]);
		}
		return tab_tab;
	}
	
	public ArrayList<int[]> StringToArrayTab(String s){
		String[] tab_string = s.split("b");
		ArrayList<int[]> array = new ArrayList<int[]>();
		for(String ss : tab_string) {
			array.add(StringToTab(ss));
		}
		return array;
	}
	
	public int[][][] StringToTabTabTab(String s) {
		String[] tab_string = s.split("c");
		int[][][] tab_tab_tab = new int[tab_string.length][][];
		for(int i =0;i<tab_tab_tab.length;i++) {
			tab_tab_tab[i] = StringToTabTab(tab_string[i]);
		}
		return tab_tab_tab;
	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (jt1.getText().equals("") == false & jt2.getText().equals("") == false){
			String cles = "";
			String message = "";
			try {
				 

	            // Creates a FileInputStream
	            FileInputStream file = new FileInputStream(jt2.getText() + ".txt");

	            // Creates a BufferedInputStream
	            BufferedInputStream input = new BufferedInputStream(file);

	            // Reads first byte from file
	            int i = input .read();
	            while (i != -1) {
	                cles += ((char) i);

	                // Reads next byte from the file
	                i = input.read();
	            }
	            file.close();
	            input.close();
	         // Creates a FileInputStream
	            FileInputStream file1 = new FileInputStream(jt1.getText() + ".txt");

	            // Creates a BufferedInputStream
	            BufferedInputStream input1 = new BufferedInputStream(file1);

	            // Reads first byte from file
	            int j = input1.read();
	            while (j != -1) {
	                message += ((char) j);

	                // Reads next byte from the file
	                j = input1.read();
	            }
	            file1.close();
	            input1.close();
			}
		    catch (Exception ee) {
			   
			   FenetreErreur fe = new FenetreErreur("Fichiers non présents");
		       ee.getStackTrace();
		   
		    }
			if (message.equals("") == false & cles.equals("") == false) {
				 String[] dees = cles.split("a");	            	       
			     String[] lestab1 = dees[0].split(";");	              
			     int[] masterkey1 = StringToTab(lestab1[0]);	              
			     ArrayList<int[]>tab_cles1 = StringToArrayTab(lestab1[1]);	           
			     int[][][] s_tab1 = StringToTabTabTab(lestab1[2]);	             
			     Des des1 = new Des(masterkey1, tab_cles1, s_tab1);	       
			     String[] lestab2 = dees[1].split(";");	       
			     int[] masterkey2 = StringToTab(lestab2[0]);	       
			     ArrayList<int[]>tab_cles2 = StringToArrayTab(lestab2[1]);	       
			     int[][][] s_tab2 = StringToTabTabTab(lestab2[2]);	       	       
			     Des des2 =  new Des(masterkey2, tab_cles2, s_tab2);	            
			     String[] lestab3 = dees[2].split(";");	       
			     int[] masterkey3 = StringToTab(lestab3[0]);	       
			     ArrayList<int[]>tab_cles3 = StringToArrayTab(lestab3[1]);	       
			     int[][][] s_tab3= StringToTabTabTab(lestab3[2]);	       
			     Des des3 =  new Des(masterkey3, tab_cles3, s_tab3);      
			     TripleDes triple = new TripleDes(des1,des2,des3);
		         int[] tab_crypte = this.StringToTab(message);   
			     String message_decrypte = triple.decrypter(tab_crypte);	       
				 this.setVisible(false);
				 FenetreNotification f = new FenetreNotification("Le message décrypté est : " + message_decrypte);
				
			}
			
	       
		}
		else {				  
			FenetreErreur fe = new FenetreErreur("Les deux fichiers doivent être renseignés");
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FenetreDecrypter f = new FenetreDecrypter();
	}

}