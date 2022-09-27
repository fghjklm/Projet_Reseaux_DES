package defaut;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestDes {
	
	Des des;
	Random rnd;
	
	@Before
	public void setUp() throws Exception {
		des = new Des();
		rnd=new Random();

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
		//System.out.println(bloc.equals(bloc));
        

		//test decale-gauche:
		int[] blocs= {1,2,3,4,5,6,8,56,12,0,7,8,9,10};
		int[] decalle=des.decalle_gauche(blocs, 25);
		//des.afficher_tab(decalle);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStringToBits_testBitToString() {
		String[] chaines = {"oihvonz", " akvn  anfd jbj", "pvnaioe1654ouvd", "pjvoqnv3684354geg"};
		
		for(String s : chaines) {
			assertEquals(des.bitsToString(des.stringToBits(s)) ,s);
		}
		/*
		 * assertEquals(des.bitsToString(des.stringToBits("é")) ,"é");
		 * à reprendre pour que ça prenne en compte les caractères "complexes"!
		 */
	}
	
	@Test
	public void testGenerePermutation() {
		
		for (int i=0; i<100;i++) {
			int taille=rnd.nextInt(1000);
			int[] permut=des.generePermutation(taille);

			assertTrue(permut.length==taille);
			
			for (int j=1; j<taille+1;j++) {
				Boolean jPresent=false;
				for (int bloc : permut) {
					if (bloc==j) { jPresent=true;};
				}
				assertTrue(jPresent);
			}
		}
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
