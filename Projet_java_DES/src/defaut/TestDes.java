package defaut;

public class TestDes {
	
	public static void main(String[] args) {
		Des des = new Des();
		String[] chaines = {"oihvonz", " akvn  anfd jbj", "ùpvnaioe1654ouvd", "pjvoqnv3684<354g<eg", "é"};
		for(String s : chaines) {
			assert(des.bitsToString(des.stringToBits(s)) == s);
		}
		System.out.println(des.bitsToString(des.stringToBits("é")) == "é");
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
		System.out.println();
		int[] blocs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int[][] blocs2 = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}};
		des.afficher_tab(blocs);
		des.afficher_tab_tab(blocs2);
		des.afficher_tab_tab(des.decoupage(blocs, 6));
		des.afficher_tab(des.recollage_bloc(des.decoupage(blocs, 4)));
		
		System.out.print(des.decoupage(blocs, 2).equals(blocs2));
		
		
		
		
		
		
		
		
		
		
		
	}

}
