package defaut;

import static org.junit.Assert.*;

import java.util.Arrays;
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

		
		/*
        

		//test decale-gauche:
		int[] blocs= {1,2,3,4,5,6,8,56,12,0,7,8,9,10};
		int[] decalle=des.decalle_gauche(blocs, 25);
		//des.afficher_tab(decalle);
		
		
		
		

		int[] blocs = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int[][] blocs2 = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}};
		des.afficher_tab(blocs);
		des.afficher_tab_tab(blocs2);
		des.afficher_tab_tab(des.decoupage(blocs, 6));
		des.afficher_tab(des.recollage_bloc(des.decoupage(blocs, 4)));
		
		System.out.print(des.decoupage(blocs, 2).equals(blocs2));
		
		
		
		*/
		
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
				
				
				int[] testRandom=new int[permut.length];
				Arrays.sort(testRandom);
				assertFalse(testRandom==permut);
				//très faible probabilité que permut soit trié et seule manière de vérifier que permut est bien rempli aléatoirement
			}
		}
	}
	
	@Test
	public void testPermutation_testInvPermutation() {
		int taille=64;
		
		for (int i=0;i<100;i++) {
			int[] tab_permutation=des.generePermutation(taille);
			
			int[] bloc= new int[taille];

			for(int j=0;j<taille;j++) {
				bloc[j]=j+1;
			}
			
			int[] permutation=des.permutation(tab_permutation, bloc);
			int[] invPermutation = des.invPermutation(tab_permutation, permutation);
			
			assertTrue(Arrays.equals(bloc, invPermutation));
		}
	}
	
	@Test
	public void testDecoupage() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testRecollage_bloc() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testDecalle_gauche() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testXor() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testGenereCle() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFonction_S() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFonction_F() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testCrypte() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testDecrypte() {
		//fail("Not yet implemented");
	}
	
}
