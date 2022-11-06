package Interface_graphique;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import Des.TripleDes;

public class FenetreCrypter extends JFrame implements MouseListener {
	
	private JLabel l1, l2, l3;
	private JTextField jt1;
	private JButton b, bmessage, bclés;
	String clés_path, message_path;
	public FenetreCrypter() {

		super("Cryptage d'un message");
		this.clés_path = "";
		this.message_path  = "";
		this.setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new GridLayout(3,1));
		JPanel p2 = new JPanel(new GridLayout(3,1));
		this.l1 = new JLabel("Message à crypter :");
		this.l2 = new JLabel("Nom du fichier contenant le message crypté :");
		this.l3 = new JLabel("Nom du fichier contenant les clés :");
		this.jt1 = new JTextField();

		this.b = new JButton("envoyer");
		this.bmessage = new JButton("Fichier du message");
		this.bclés = new JButton("Fichier des clés");
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		p2.add(jt1);
		p2.add(bmessage);
		p2.add(bclés);

		this.add(p1,BorderLayout.WEST);
		this.add(p2,BorderLayout.CENTER);
		this.add(b,BorderLayout.SOUTH);
		b.addMouseListener(this);
		bmessage.addMouseListener(this);
		bclés.addMouseListener(this);
		this.setSize(800,250);
		this.setVisible(true);
	}
	public String tabToString(int[] tab) {
		String s = "";
		for(int i : tab) {
			s += String.valueOf(i) + ",";
			
		}
		s = s.substring(0, s.length()-1);
		return s;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == bmessage) {
			JFileChooser choose = new JFileChooser(
			        FileSystemView
			        .getFileSystemView()
			        .getHomeDirectory()
			    );

			choose.setDialogTitle("Selectionnez un fichier message");
		    choose.setAcceptAllFileFilterUsed(false);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		    choose.addChoosableFileFilter(filter);
		    int res = choose.showSaveDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
			      File file = choose.getSelectedFile();
			      
			      message_path = file.getAbsolutePath();
			      bmessage.setText(message_path);
			    
			  
			}
			
		}
		if(e.getSource() == bclés) {
			JFileChooser choose = new JFileChooser(
			        FileSystemView
			        .getFileSystemView()
			        .getHomeDirectory()
			    );

			choose.setDialogTitle("Selectionnez un fichier clés");
		    choose.setAcceptAllFileFilterUsed(false);
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("txt", "txt");
		    choose.addChoosableFileFilter(filter);
		    int res = choose.showSaveDialog(null);
			if (res == JFileChooser.APPROVE_OPTION) {
			      File file = choose.getSelectedFile();
			      
			      clés_path = file.getAbsolutePath();
			      bclés.setText(clés_path);
			    
			  
			}
			
		}
		if(e.getSource() == b) {
			if (jt1.getText().equals("") == false & message_path.equals("") == false & clés_path.equals("") == false){
				String message = jt1.getText();
				TripleDes triple = new TripleDes();
				int[] tableau_crypte = triple.crypter(message);
				String tableau = tabToString(tableau_crypte);
				String clees = triple.getCles();
				 try {
			            FileOutputStream crypte = new FileOutputStream(message_path);
	
			            byte[] array = tableau.getBytes();
	
			            // Writes byte to the file
			            crypte.write(array);
			            crypte.close();
			            FileOutputStream cles = new FileOutputStream(clés_path);
	
			            byte[] arrays2 = clees.getBytes();
	
			            // Writes byte to the file
			            cles.write(arrays2);
			            cles.close();
			            
			        }
	
			        catch(Exception ee) {
			            System.out.println(ee.getStackTrace());
			        }
				this.setVisible(false);
				FenetreNotification f = new FenetreNotification("message crypté");
			}
			else {				  
				FenetreErreur fe = new FenetreErreur("Les champs doivent être remplis");
			}
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