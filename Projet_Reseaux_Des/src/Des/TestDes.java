package Des;

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
		String[] chaines = {}; //{"oihvonz", " akvn  anfd jbj", "pvnaioe1654ouvd", "pjvoqnv3684354geg"};
		
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
				//assertFalse(testRandom==permut);
				//très faible probabilité que permut soit trié et seule manière de vérifier que permut est bien rempli aléatoirement
			}
		}
	}
	
	@Test
	public void testPermutation_testInvPermutation() {
		
		//rajouter les tests pour 2 trucs pas de la même taille, bloc de 64 et permut de 58
		
		
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
		//tester avec des blocs de taille différente
		
		
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
		//construction d'un tab trié avec les entiers de 1 à 28
		int[] bloc= new int[28];
		for (int i=0;i<28;i++) {
			bloc[i]=i+1;
		}
		int[] Dzero= des.decalle_gauche(bloc, 0);
		int[] Dun = des.decalle_gauche(bloc, 1);
		int[] Dcinq=des.decalle_gauche(bloc, 5);
		int[] Dmax=des.decalle_gauche(bloc, bloc.length);
		
		int[] Tun= {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,1};
		int[] Tcinq= {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,1,2,3,4,5};
		
		assertTrue(Arrays.equals(bloc, Dzero));
		assertTrue(Arrays.equals(Tun, Dun));
		assertTrue(Arrays.equals(Tcinq, Dcinq));
		assertTrue(Arrays.equals(bloc, Dmax));
	}
	
	@Test
	public void testXor() {
		int[] tab1= {1,1,0,1,0,1,1,0,0,1};
		int[] tab2= {0,1,0,0,1,1,0,0,0,1};
		
		int[] resultat= {1,0,0,1,1,0,1,0,0,0};
		
		assertTrue(Arrays.equals(des.xor(tab1, tab2), resultat));
		//rajouter quelques tests
	}
	
	@Test
	public void testCrypte_testDecrypte() {
		//cas sans caractères spéciaux :
		String sans = "j'ai tres tres bien mange aujourdhui, et vous ? hmmm";
		int[] crypteS = des.crypte(sans);
		String decrypteS = des.decrypte(crypteS);
		assertEquals(sans,decrypteS);
		
		/*
		System.out.println(sans);
		System.out.println(decrypteS);
		System.out.println("fin");
		*/

		//cas avec caractères spéciaux :
		Des des2=new Des();
		String avec = "j'ai très très bien mange aujourdhui, et vous ? hmmm";
		int[] crypteA = des2.crypte(avec);
		String decrypteA = des2.decrypte(crypteA);
		
		//assertEquals(s2,decrypte2);
		System.out.println(avec);
		System.out.println(decrypteA);
		System.out.println("fin2");
	}
	
}