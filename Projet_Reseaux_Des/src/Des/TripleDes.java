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
		return this.des3.crypte(this.des2.crypte(this.des1.crypte(message)));
		
	}
	
	public String decrypter(int[] messageCode) {
		return this.des1.decrypte(this.des2.decrypteTableau(this.des2.decrypteTableau(messageCode)));
	}

}
