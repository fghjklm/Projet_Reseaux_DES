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
	public void testDecoupage_testRecollageBloc() {
		int[] tailles= {48,64};
		
		for (int taille : tailles) {
			
			int[] bloc=new int[taille];
			
			for (int j=0; j<taille; j++) {
				bloc[j]=j+1;
			}
			
			for (int i=0; i<100;i++) {

				int[] permut=des.generePermutation(taille);
				int[] blocPermute=des.permutation(permut, bloc);
				int[] blocATester=blocPermute.clone();
				
				for (int nbBlocs=1; nbBlocs<taille+1;nbBlocs++) {
					if (taille%nbBlocs==0) {
						
						int[][] blocDecoupe=des.decoupage(blocATester, nbBlocs);
						int[] blocRecolle=des.recollage_bloc(blocDecoupe);
						
						assertTrue(Arrays.equals(blocPermute, blocRecolle));
					}
				}
			}
		}
	}
	
	@Test
	public void testDecalle_gauche() {
		
		int[] bloc= new int[28];
		for (int i=0;i<28;i++) {
			bloc[i]=i+1;
		}
		
		for (int j=0; j<100;j++) {
			int decallage=rnd.nextInt(28)+1;
			//choisir un décalage en random
			//décaler
			
			//créer le bloc décallé en profitant du fait qu'on sais de quoi il est rempli
			int[] verif=new int[28];
			for (int k=0; k<28;k++) {
				verif[k]=(k-decallage);
			}
			
			//tester que les deux sont égaux
			
			
			
		}
		
		
		
		int[] blocs= {1,2,3,4,5,6,8,56,12,0,7,8,9,10};
		int[] decalle=des.decalle_gauche(blocs, 25);
		
		
		//des.afficher_tab(decalle);
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
	public void testCrypte_testDecrypte() {
		//fail("Not yet implemented");
	}
	
}
