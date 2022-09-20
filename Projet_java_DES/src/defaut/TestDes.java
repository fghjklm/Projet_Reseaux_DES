package defaut;

public class TestDes {
	
	public static void main(String[] args) {
		Des des = new Des();
		String[] chaines = {"oihvonz", " akvn  anfd jbj", "ùpvnaioe1654ouvd", "pjvoqnv3684<354g<eg"};
		for(String s : chaines) {
			assert(des.bitsToString(des.stringToBits(s)) == s);
		}
		//test de GenerePermutation
		int[] testGenerePermutation=des.generePermutation(5);
		/*
		for(int i=0;i<testGenerePermutation.length;i++) {
			System.out.print(testGenerePermutation[i]);
			System.out.print(", ");
		}
		System.out.println();
		System.out.println(testGenerePermutation.length);
		*/

		
		
		
		//test de permutation et invPermutation
		int tailleTest=64;
		int[] bloc= new int[tailleTest];

		for(int i=0;i<tailleTest;i++) {
			bloc[i]=i;
		}
		int[] tab_permutation= 
			{58, 50, 42, 34, 26, 18, 10, 2,
					60, 52, 44, 36, 28, 20, 12, 4,
					62, 54, 46, 38, 30, 22, 14, 6,
					64, 56, 48, 40, 32, 24, 16, 8, 
					57, 49, 41, 33, 25, 17, 9, 1, 
					59, 51, 43, 35, 27, 19, 11, 3,
					61, 53, 45, 37, 29, 21, 13, 5,
					63, 55, 47, 39, 31, 23, 15, 7};
		
		int[] testPermutation=des.permutation(tab_permutation, bloc);
		/*
		 * for(int i=0;i<testPermutation.length;i++) {
			System.out.print(testPermutation[i]);
			System.out.print(", ");
		}
		System.out.println();
		*/
		
		
		int[] testInvPermutation = des.invPermutation(tab_permutation, testPermutation);
		/*
		 * for(int i=0;i<testInvPermutation.length;i++) {
			System.out.print(testInvPermutation[i]);
			System.out.print(", ");
		}
		System.out.println();
		
		for(int i=0;i<bloc.length;i++) {
			System.out.print(bloc[i]);
			System.out.print(", ");
		}
		System.out.println();
		*/
		
		System.out.println(bloc.equals(bloc));
        
        
        
	}

}
