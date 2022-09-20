package defaut;

public class TestDes {
	
	public static void main(String[] args) {
		Des des = new Des();
		for(int i : des.stringToBits("oui")) {
			System.out.println(i);
			
		}
		
	}

}
