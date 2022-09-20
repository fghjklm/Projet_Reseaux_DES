package defaut;

public class TestDes {
	
	public static void main(String[] args) {
		Des des = new Des();
		for(int i : des.stringToBits("oui")) {
			System.out.println(i);
			
		}
		
		
		
		
		//test de GenerePermutation
		int[] testGenerePermutation=des.generePermutation(5);
		for(int i=0;i<testGenerePermutation.length;i++) {
			System.out.print(testGenerePermutation[i]);
			System.out.print(", ");
		}
		System.out.println();
		System.out.println(testGenerePermutation.length);

		
		
		
		//test de permutation
		int tailleTest=5;
		int[] bloc= new int[tailleTest];
		for(int i=0;i<tailleTest;i++) {
			bloc[i]=i;
		}
		int[] tab_permutation= {3,5,2,1,4};
		
		int[] testPermutation=des.permutation(tab_permutation, bloc);
		for(int i=0;i<testPermutation.length;i++) {
			System.out.print(testPermutation[i]);
			System.out.print(", ");
		}
		
		
		
		
		
		
		
		
		
		
		
	}

}
