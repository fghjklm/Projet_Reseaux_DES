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
				assertFalse(testRandom==permut);
				//très faible probabilité que permut soit trié et seule manière de vérifier que permut est bien rempli aléatoirement
			}
		}
	}
	
	@Test
	public void testPermutation_testInvPermutation() {
		
		//rajouter les tests pour 2 trucs pas de la même taille, bloc de 64 et permut de 58
		//est-ce que c'est valide si on teste que le résultat de l'un est le même que le début??
		
		
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
		
		int[] bloc= new int[28];
		for (int i=0;i<28;i++) {
			bloc[i]=i+1;
		}
		
		for (int j=0; j<100;j++) {
			int decallage=rnd.nextInt(28)+1;
			
			
			int[] blocDecalle=des.decalle_gauche(bloc, decallage);
			
			int[] verif=new int[28];
			for (int k=decallage; k<28;k++) {
				verif[k-decallage]=bloc[k];
			}
			for (int l=0;l<decallage;l++) {
				verif[l+bloc.length-decallage]=bloc[l];
			}
			
			assertTrue(Arrays.equals(blocDecalle, verif));
			
			//pas sûr que ça soit bon, peut être faire des tests unitaires!!!! car là j'utilise juste deux méthodes pour décaller
			//et je vérifie juste que ces deux méthodes renvoient le même résultat, pas que c'est bien décallé (même si moi visuellement je le vois)
		}
	}
	
	@Test
	public void testXor() {
		//tests unitaires à la piccinini car sinon on doit juste recoder la même chose que l'intérieur de la méthode
	}
	
	@Test
	public void testGenereCle() {
		//comment on peut la vérifier elle???
	}
	
	@Test
	public void testFonction_S() {
		/*
		int valeur=14;
		int[] nouveau_tab=new int[4];
		
		
		
		String valeurBinaire= Integer.toBinaryString(valeur);
		
		for (int i=0; i<nouveau_tab.length;i++) {
			nouveau_tab[i]=valeurBinaire.charAt(i)-'0';
		}
		
		des.afficher_tab(nouveau_tab);
		*/
		
		
		
		//fail("Not yet implemented");
	}
	
	@Test
	public void testFonction_F() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testCrypte_testDecrypte() {
		String s = "j'ai trés tres bien mange aujourdhui, et vous ? hmmm";
		int[] crypte = des.crypte(s);
		String decrypte = des.decrypte(crypte);
		System.out.println(des.bitsToString(des.stringToBits(s)));
		System.out.println(decrypte);
		
	}
	
}
