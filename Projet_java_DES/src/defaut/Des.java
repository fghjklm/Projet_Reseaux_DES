package defaut;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Des {
	
	private static int taille_bloc = 64;
	private static int taille_sous_bloc = 32;
	private static int nb_ronde = 1;
	private static int[] tab_decalage = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1};
	private static int[] perm_initiale = 
		{58, 50, 42, 34, 26, 18, 10, 2,
		60, 52, 44, 36, 28, 20, 12, 4,
		62, 54, 46, 38, 30, 22, 14, 6,
		64, 56, 48, 40, 32, 24, 16, 8, 
		57, 49, 41, 33, 25, 17, 9, 1, 
		59, 51, 43, 35, 27, 19, 11, 3,
		61, 53, 45, 37, 29, 21, 13, 5,
		63, 55, 47, 39, 31, 23, 15, 7};
	private static int[][] S = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, 
						{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
						{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
						{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
	private static int[] E = 
		{32, 1, 2 ,3, 4, 5, 
		4, 5, 6, 7, 8, 9, 
		8, 9, 10, 11, 12 ,13,
		12, 13 ,14, 15 ,16 ,17,
		16, 17, 18, 19, 20, 21,
		20, 21, 22, 23 ,24, 25, 
		24, 25, 26, 27, 28, 29,
		28, 29, 30, 31, 32, 1};
	
	private int[] masterKey;
	private ArrayList<ArrayList<Integer>> tab_cles;
	
	public Des(){
		this.masterKey = new int[64];
		Random r = new Random();
		for (int i = 0; i< this.masterKey.length; i++) {
			this.masterKey[i]=r.nextInt(2);
		}
		this.tab_cles = new ArrayList<ArrayList<Integer>>();
		
		
	}
	
	public int[] stringToBits(String message) {
		
		byte[] tableau = message.getBytes(StandardCharsets.US_ASCII);
		int[] tab_int = new int[tableau.length];
		for(int i = 0; i<tableau.length; i++ ) {
			tab_int[i] = (int)tableau[i];
		}
		int[] tab_binaire = new int[tab_int.length*8];
		for(int i =0; i < tab_int.length; i++) {
			int entier = tab_int[i];
			for(int j = 7; j >=0; j--) {
				if (entier-Math.pow(2,  j) >= 0){
					entier = (int) (entier-Math.pow(2, j));
					tab_binaire[8*i + 7-j] = 1;	
					
				}
				else {
					tab_binaire[8*i+7-j] = 0;
				}
			}
			assert(entier ==0);
		}
		
		
		return tab_binaire;
	}
	public String bitsToString(int[] blocs) {
		assert(blocs.length%8 == 0);
		int [] tab_int = new int[blocs.length/8];
		for(int i = 0; i < tab_int.length; i++) {
			int entier = 0;
			for(int j = 0; j < 8; j++) {
				entier += Math.pow(2,7-j) *blocs[8*i + j];		
			}
			tab_int[i] = entier;
		}
		byte[] tableau = new byte[tab_int.length];
		for(int i = 0; i < tab_int.length; i++) {
			tableau[i] = (byte)tab_int[i];
		}
		return new String(tableau, StandardCharsets.US_ASCII);
	}

	
	public int[] generePermutation (int taille) {
		Random rnd=new Random();
		int[] permutation= new int[taille];
		HashSet<Integer> dejaPlace=new HashSet<Integer>();
		int indice=0;
		
		for (int i=0; i<taille;i++) {
			int bloc;
			do {
				bloc=rnd.nextInt(taille)+1;
			}while (dejaPlace.contains(bloc));
			
			dejaPlace.add(bloc);
			permutation[indice]=bloc;
			indice++;
		}
		
		return permutation;
	}
	
	
	public int[] permutation(int[] tab_permutation, int[] bloc) {
		int[] blocPermute=new int[bloc.length];
		for (int i=0;i<bloc.length;i++) {
			blocPermute[i]=bloc[tab_permutation[i]-1];
		}
		
		return blocPermute;
	}
	
	public int [][] decoupage (int[] bloc, int nbBlocs){
		assert(bloc.length%nbBlocs == 0);
		int[][] découpage = new int[nbBlocs][bloc.length/nbBlocs];
		for(int i = 0; i < nbBlocs; i++) {
			int[] petite_decoupe = new int[bloc.length/nbBlocs];
			for(int j = 0; j < bloc.length/nbBlocs; j++ ) {
				petite_decoupe[j] = bloc[i*bloc.length/nbBlocs + j];
			}
			découpage[i] = petite_decoupe;
			
		}
		return découpage;
		
	}
	
	public int[] recollage_bloc(int[][] blocs) {
		int[] recollage = new int[blocs.length*blocs[0].length];
		for(int i = 0; i < blocs.length;i++) {
			for(int j = 0; j < blocs[i].length; j++) {
				recollage[i*blocs[i].length + j] = blocs[i][j];
			}
		}
		
		return recollage;
		
	}
	
	public void afficher_tab(int[] tab) {
		String s = "[";
		for(int i : tab) {
			s += String.valueOf(i) + ", ";
			
		}
		s += "]";
		System.out.println(s);
	}
	
	public void afficher_tab_tab(int[][] tab_tab) {
		System.out.println("[");
		for(int[] tab : tab_tab) {
			afficher_tab(tab);
		}
		System.out.println("]");
	}
	
	
	
	int[] invPermutation(int[] tab_permutation, int[] bloc) {
		int[] blocPermute=new int[bloc.length];
		for (int i=0;i<bloc.length;i++) {
			blocPermute[tab_permutation[i]-1]=bloc[i];
		}
		
		return blocPermute;
	}
	
	int[] decalle_gauche(int[] bloc, int nbCran) {
		
		int[] decalle=new int[bloc.length];
		
		for (int i=0; i<bloc.length;i++) {
			decalle[Math.floorMod(i-nbCran, bloc.length)]=bloc[i];
		}
		return decalle;
	}
	

	int[] genereCle(int ronde) {
		int[] cle=new int[64];
		int[] permutation=this.generePermutation(64);
		cle=this.permutation(permutation, this.masterKey);
		//nbr de ronde commence à 1!!
		for (int i=0;i<this.masterKey.length;i++) {
			
		}
		
		
		return cle;
	}

}
