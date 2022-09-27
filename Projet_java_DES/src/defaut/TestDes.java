package defaut;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDes {

	@Before
	public void setUp() throws Exception {
		Des des = new Des();
		String[] chaines = {"oihvonz", " akvn  anfd jbj", "ùpvnaioe1654ouvd", "pjvoqnv3684<354g<eg", "é"};
		for(String s : chaines) {
			assert(des.bitsToString(des.stringToBits(s)) == s);
		}
		System.out.println(des.bitsToString(des.stringToBits("é")) == "é");
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
		int[] blocs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int[][] blocs2 = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}};
		des.afficher_tab(blocs);
		des.afficher_tab_tab(blocs2);
		des.afficher_tab_tab(des.decoupage(blocs, 6));
		des.afficher_tab(des.recollage_bloc(des.decoupage(blocs, 4)));
		
		System.out.print(des.decoupage(blocs, 2).equals(blocs2));
		
		
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
        
        
		
		//test decale-gauche:
		int[] blocs= {1,2,3,4,5,6,8,56,12,0,7,8,9,10};
		int[] decalle=des.decalle_gauche(blocs, 25);
		des.afficher_tab(decalle);
	}

	@Test
	public void testStringToBits() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testBitsToString() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGenerePermutation() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPermutation() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvPermutation() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDecoupage() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRecollage_bloc() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDecalle_gauche() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testXor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGenereCle() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFonction_S() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testFonction_F() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCrypte() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDecrypte() {
		fail("Not yet implemented");
	}
	
}
