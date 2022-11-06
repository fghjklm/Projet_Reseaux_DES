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
		//tests classiques
		String[] chaines ={"oihvonz", " akvn  anfd jbj", "pvnaioe1654ouvd", "pjvoqnv3684354geg"};

		for(String s : chaines) {
			assertEquals(des.bitsToString(des.stringToBits(s)) ,s);
		}

		//tests caractères spéciaux
		assertEquals(des.bitsToString(des.stringToBits("&é(-è_çà@=")) ,"&é(-è_çà@=");

	}

	@Test
	public void testGenerePermutation() {

		for (int i=0; i<100;i++) {
			int taille=rnd.nextInt(1000);
			int[] permut=des.generePermutation(taille);

			assertTrue(permut.length==taille);

			//pour chaque nombre entre 1 et taille inclus
			for (int j=1; j<taille+1;j++) {
				Boolean jPresent=false;
				for (int bloc : permut) {
					if (bloc==j) { jPresent=true;};
				}
				//on vérifie que ce nombre est contenu dans le tableau de permutation
				assertTrue(jPresent);
			}
		}
	}

	@Test
	public void testPermutation_testInvPermutation() {
		//tests pour bloc et permutation de même taille
		int tailleBloc=64;

		for (int i=0;i<100;i++) {
			int[] tab_permutation=des.generePermutation(tailleBloc);

			int[] bloc= new int[tailleBloc];

			for(int j=0;j<tailleBloc;j++) {
				bloc[j]=j+1;
			}

			int[] permutation=des.permutation(tab_permutation, bloc);
			int[] invPermutation = des.invPermutation(tab_permutation, permutation);
			assertFalse(Arrays.equals(permutation, invPermutation));
			assertTrue(Arrays.equals(bloc, invPermutation));
		}

		//tests pour bloc et permutation de taille différente (création de clé)
		int taillePermut=56;

		for (int i=0;i<100;i++) {
			int[] tab_permutation=des.generePermutation(taillePermut);

			int[] bloc= new int[tailleBloc];

			for(int j=0;j<tailleBloc;j++) {
				bloc[j]=j+1;
			}

			int[] permutation=des.permutation(tab_permutation, bloc);
			int[] invPermutation = des.invPermutation(tab_permutation, permutation);

			int[] blocTronque=new int[taillePermut];
			for (int k=0; k<blocTronque.length;k++) {
				blocTronque[k]=bloc[k];
			}
			assertTrue(Arrays.equals(blocTronque, invPermutation));
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
		//construction d'un tab trié avec les entiers de 1 à 28
		int[] bloc= new int[28];
		for (int i=0;i<28;i++) {
			bloc[i]=i+1;
		}
		int[] Decalzero= des.decalle_gauche(bloc, 0);
		int[] Decalun = des.decalle_gauche(bloc, 1);
		int[] Decalcinq=des.decalle_gauche(bloc, 5);
		int[] Decalmax=des.decalle_gauche(bloc, bloc.length);

		int[] Testun= {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,1};
		int[] Testcinq= {6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,1,2,3,4,5};

		assertTrue(Arrays.equals(bloc, Decalzero));
		assertTrue(Arrays.equals(Testun, Decalun));
		assertTrue(Arrays.equals(Testcinq, Decalcinq));
		assertTrue(Arrays.equals(bloc, Decalmax));
	}


	@Test
	public void testXor() {
		int[] tab1= {1,1,0,1,0,1,1,0,0,1};
		int[] tab2= {0,1,0,0,1,1,0,0,0,1};
		int[] tab3= {0,0,0,1,1,1,0,1,0,1};
		int[] tab4= {0,1,1,0,1,0,0,1,0,1};
		int[] tab5= {0,1,1,0,1,1,0,1,0,1};
		int[] tab6= {0,0,0,0,1,1,0,0,0,0};
		int[] tab7= {0,1,1,0,0,1,1,0,0,1};
		int[] tab8= {1,0,0,1,1,0,1,0,0,0};

		int[] result1= {1,0,0,1,1,0,1,0,0,0};
		int[] result2= {0,1,1,1,0,1,0,0,0,0};
		int[] result3= {0,1,1,0,0,0,0,1,0,1};
		int[] result4= {1,1,1,1,1,1,0,0,0,1};
		int[] result5= {1,0,0,0,0,1,1,1,0,1};
		int[] result6= {1,0,1,1,1,0,1,1,0,0};


		int[][][] Ltest= {{tab1,tab2},{tab3,tab4},{tab5,tab6},{tab7,tab8},{tab3,tab8},{tab1,tab5}};
		int[][] Lresult= {result1,result2,result3,result4,result5,result6};

		for(int i=0; i<Ltest.length;i++) {
			assertTrue(Arrays.equals(des.xor(Ltest[i][0],Ltest[i][1]), Lresult[i]));
		}
	}
	
	@Test
	public void testCreerS() {

		for (int i=0; i<100;i++) {
			int[][] S =des.creerS();

			for (int j=0; j<4;j++) {
				//vérifier que les 16 chiffres de 0 à 15 sont présents
				for(int k=0;k<16;k++) {
					Boolean kPresent=false;
					for (int bloc : S[j]) {
						if(bloc==k) {kPresent=true;};
					}
					assertTrue(kPresent);

				}
			}
		}
	}


	@Test
	public void testCrypte_testDecrypte() {
		String test1="Test sans caracteres speciaux";
		String test2="Test avec caractères spéciaux : réussi ?";
		String test3="Test avec une phrase très longue contenant des caractères spéciaux : J'ai très bien mangé et vous? Moi ça va bien car 2=3+(-1) \"#\" !^% *µ£$¨><{}";

		String[] L= {test1,test2,test3};

		for (String phrase : L) {
			int[] crypteP=des.crypte(phrase);
			String decrypteP=des.decrypte(crypteP);
			assertEquals(phrase,decrypteP);
		}
	}

	@Test
	public void testTripleDes() {
		TripleDes Tdes=new TripleDes();
		String test1="Test du triple sans caracteres speciaux";
		String test2="Test du triple avec caractères spéciaux : réussi ?";
		String test3="Test du triple avec une phrase très longue contenant des caractères spéciaux : J'ai très bien mangé et vous? Moi ça va bien car 2=3+(-1) \"# \" !^% *µ£$¨><{}";

		String[] L= {test1,test2,test3};

		for (String phrase : L) {
			int[] crypteP=Tdes.crypter(phrase);
			String decrypteP=Tdes.decrypter(crypteP);
			System.out.println(phrase);
			System.out.println(decrypteP);
			System.out.println();

			assertEquals(phrase,decrypteP);
		}
	}
}