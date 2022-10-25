package Interface_graphique;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Des.TripleDes;

public class FenetreCrypter extends JFrame implements MouseListener {
	
	private JLabel l1;
	private JTextField jt1;
	private JButton b;
	public FenetreCrypter() {

		super("Cryptage d'un message");
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,1));
		JPanel p2 = new JPanel(new GridLayout(1,1));
		this.l1 = new JLabel("Message à crypter :");

		this.jt1 = new JTextField();

		this.b = new JButton("envoyer");
		p1.add(l1);

		p2.add(jt1);
		this.add(p1,BorderLayout.WEST);
		this.add(p2,BorderLayout.CENTER);
		this.add(b,BorderLayout.SOUTH);
		b.addMouseListener(this);
		this.setSize(400,250);
		this.setVisible(true);
	}
	public String tabToString(int[] tab) {
		String s = "[";
		for(int i : tab) {
			s += String.valueOf(i) + ",";
			
		}
		s = s.substring(0, s.length()-1);
		s += "]";
		return s;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if (jt1.getText().equals("") == false){
			String message = jt1.getText();
			TripleDes triple = new TripleDes();
			int[] tableau_crypte = triple.crypter(message);
			String tableau = tabToString(tableau_crypte);
			String clees = triple.getCles();
			 try {
		            FileOutputStream crypte = new FileOutputStream("messagecrypte.txt");

		            byte[] array = tableau.getBytes();

		            // Writes byte to the file
		            crypte.write(array);
		            crypte.close();
		            FileOutputStream cles = new FileOutputStream("cles.txt");

		            byte[] arrays2 = clees.getBytes();

		            // Writes byte to the file
		            cles.write(arrays2);
		            cles.close();
		            
		        }

		        catch(Exception ee) {
		            ee.getStackTrace();
		        }
			this.setVisible(false);
		}
		else {				  
			FenetreErreur fe = new FenetreErreur("Le message à crypter ne peut pas être vide");
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
		FenetreCrypter f = new FenetreCrypter();
	}

}