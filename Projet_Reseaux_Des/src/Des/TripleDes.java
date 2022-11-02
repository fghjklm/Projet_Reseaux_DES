package Des;

public class TripleDes {
	
	Des des1;
	Des des2;
	Des des3;
	
	public TripleDes() {
		des1 = new Des();
		des2 = new Des();
		des3 = new Des();
		
	}
	public TripleDes(Des des1, Des des2, Des des3) {
		this.des1 = des1;
		this.des2 = des2;
		this.des3 = des3;
	}
	
	public int[] crypter(String message) {
		return this.des1.crypte(this.des2.crypte(this.des3.crypte(message)));
		
	}
	
	public String decrypter(int[] messageCode) {
		return this.des3.decrypte(this.des2.decrypteTableau(this.des1.decrypteTableau(messageCode)));
	}
	
	public String getCles() {
		String s = "";
		s+= des1.getCles();
		s+= "a";
		s+= des2.getCles();
		s+= "a";
		s+= des3.getCles();
		return s;
	}

}
