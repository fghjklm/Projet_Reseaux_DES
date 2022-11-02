package Interface_graphique;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreNotification extends JFrame implements MouseListener{
	
	private String message;
	
	public FenetreNotification(String message) {
		super("Notification");
		this.message = message;
		this.add(new JLabel(message),BorderLayout.CENTER);
		JButton b = new JButton("ok");
		this.add(b,BorderLayout.SOUTH);
		b.addMouseListener(this);
		this.setSize(500,200);
		this.setVisible(true);
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		this.dispose();
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
