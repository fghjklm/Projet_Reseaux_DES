package Interface_graphique;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame implements MouseListener{
	
	private JButton bcrypter;
	private JButton bdecrypter;
	private JButton bFermer;
	
	public Menu() {
		super("application principale");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(1,2));

		this.bcrypter = new JButton("Crypter");
		this.bdecrypter = new JButton("Decrypter");
		p1.add(this.bcrypter);
		p1.add(this.bdecrypter);
		this.bFermer = new JButton("Fermer");
		
		this.bcrypter.addMouseListener(this);
		this.bdecrypter.addMouseListener(this);
		this.bFermer.addMouseListener(this);
		
		this.add(p1,BorderLayout.CENTER);
		this.add(bFermer,BorderLayout.SOUTH);
		this.setSize(800,500);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu m = new Menu();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.bcrypter) {
			FenetreCrypter f = new FenetreCrypter();
			
		}
		if (e.getSource() == this.bdecrypter) {
			FenetreDecrypter f = new FenetreDecrypter();
		}
		if (e.getSource() == this.bFermer) {
			this.dispose();
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

}

