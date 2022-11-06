package Des;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Des {

	private static int taille_bloc = 64;
	private static int taille_sous_bloc = 32;
	private static int nb_ronde = 16;
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
	private int[][] s;
	private int[][][] s_tab;

	private static int[] e = 
		{32, 1, 2 ,3, 4, 5, 
				4, 5, 6, 7, 8, 9, 
				8, 9, 10, 11, 12 ,13,
				12, 13 ,14, 15 ,16 ,17,
				16, 17, 18, 19, 20, 21,
				20, 21, 22, 23 ,24, 25, 
				24, 25, 26, 27, 28, 29,
				28, 29, 30, 31, 32, 1};

	private int[] masterKey;
	private ArrayList<int[]> tab_cles;

	public Des(){
		/// Initialisation de masterKey
		this.masterKey = new int[Des.taille_bloc];
		Random r = new Random();
		
		for (int i = 0; i< this.masterKey.length; i++) {
			this.masterKey[i]=r.nextInt(2);
		}
		/// Initialisation de tab_cles avec les 16 clés (utilisant genereCle() ) et s_tab avec les 16 tableau S (utilisant creerS() )
		this.tab_cles = new ArrayList<int[]>();
		this.s_tab = new int[nb_ronde][][];
		for(int i = 0; i < Des.nb_ronde; i++) {
			this.s_tab[i] = this.creerS();
			int[] cle = this.genereCle(i+1);
			this.tab_cles.add(cle);
		}
	}

	public Des(int[] masterkey, ArrayList<int[]> tab_cles, int[][][] s_tab) {	
		this.masterKey = masterkey;
		this.tab_cles = tab_cles;
		this.s_tab = s_tab;
	}

	//impossible de rendre private pour effectuer tests et pas besoin de noter protected pour visibilité package
	String getCles() {
		String s = "";
		s += this.tabToString(this.masterKey);
		s+= ";";
		s +=this.listTabToString(this.tab_cles);
		s+= ";";
		s +=this.tabTabTabToString(this.s_tab);
		return s;
	}

	int[][] creerS(){
		Random rnd=new Random();
		int[][] s = new int[4][16];
		HashSet<Integer> dejaPlace;
		for(int i =0; i<4; i++) {
			dejaPlace=new HashSet<Integer>();
			int indice = 0;
			for(int j = 0; j<16;j++) {
				int bloc;
				do {
					bloc=rnd.nextInt(16);
				}while (dejaPlace.contains(bloc));

				dejaPlace.add(bloc);
				s[i][indice] = bloc;
				indice++;	
			}
		}
		return s;
	}

	int[] stringToBits(String message) {
		String chaine_binaire="";
		for (char caractere : message.toCharArray()) {
			String lettre_binaire = String.format("%8s", Integer.toBinaryString(caractere)).replaceAll(" ", "0") ;
			// on transforme le lettre en binaire et les espaces deviennent des 0
			chaine_binaire+=lettre_binaire;
		}

		int[] tabBinaire = new int[chaine_binaire.length()];
		for(int i=0; i<tabBinaire.length; i++) {
			tabBinaire[i]=Character.getNumericValue(chaine_binaire.charAt(i));	
		}
		return tabBinaire;	
	}

	String bitsToString(int[] blocs) {
		String [] tabLettre=new String [blocs.length/8];
		String message="";
		int i,j;
		for(i=0 ; i<blocs.length/8;i++) {
			String lettre ="";
			for(j=i*8 ; j<8+8*i;j++) {
				lettre+= blocs[j];
			}
			tabLettre[i]=lettre;
		}
		for(i=0;i<tabLettre.length;i++) {
			message+=(char)Integer.parseInt(tabLettre[i],2);
		}
		return message;
	}

	int[] generePermutation (int taille) {
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

	int[] permutation(int[] tab_permutation, int[] bloc) {
		int[] blocPermute=new int[tab_permutation.length];
		for (int i=0;i<tab_permutation.length;i++) {
			blocPermute[i]=bloc[tab_permutation[i]-1];
		}
		return blocPermute;
	}

	int [][] decoupage (int[] bloc, int nbBlocs){
		assert(bloc.length%nbBlocs == 0);
		int[][] decoupage = new int[nbBlocs][bloc.length/nbBlocs];
		for(int i = 0; i < nbBlocs; i++) {
			int[] decoupei = new int[bloc.length/nbBlocs];
			for(int j = 0; j < bloc.length/nbBlocs; j++ ) {
				decoupei[j] = bloc[i*bloc.length/nbBlocs + j];
			}
			decoupage[i] = decoupei;

		}
		return decoupage;

	}

	int[] recollage_bloc(int[][] blocs) {
		int[] positions = new int[blocs.length];
		/// Initialisation d'un tableu de position contenant les positions de départ chaque 
		///  tableau de blocs dans le tableau recollage
		positions[0] = 0;
		for(int i = 1; i < blocs.length; i++) {
			positions[i] = positions[i-1] + blocs[i-1].length;
		}
		int[] recollage = new int[positions[blocs.length -1] + blocs[blocs.length-1].length];
		for(int i = 0; i < blocs.length;i++) {
			for(int j = 0; j < blocs[i].length; j++) {
				recollage[positions[i] + j] = blocs[i][j];
			}
		}
		return recollage;
	}

	public String tabToString(int[] tab) {
		String s = "";
		for(int i : tab) {
			s += String.valueOf(i) + ",";
		}
		s = s.substring(0, s.length()-1);
		return s;
	}

	public String tabTabToString(int[][] tab_tab) {
		String s = "";
		for(int[] tab : tab_tab) {
			s += this.tabToString(tab) + "b";
		}
		s = s.substring(0, s.length()-1);
		return s;
	}

	public String listTabToString(ArrayList<int []> tab_tab) {
		String s = "";
		for(int[] tab : tab_tab) {
			s += tabToString(tab) + "b";
		}
		s = s.substring(0, s.length()-1);
		return s;
	}

	public String tabTabTabToString(int[][][] tab_tab_tab) {
		String s = "";
		for(int[][] tab_tab : tab_tab_tab) {
			s += tabTabToString(tab_tab) +"c";
		}
		s = s.substring(0, s.length()-1);
		return s;
	}

	int[] invPermutation(int[] tab_permutation, int[] bloc) {
		int[] blocPermute=new int[tab_permutation.length];
		for (int i=0;i<tab_permutation.length;i++) {
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
		int[] permutation_56=this.generePermutation(56);
		int [] cle_56=this.permutation(permutation_56, this.masterKey);
		int [][] decoupe = this.decoupage(cle_56, 2);
		for (int i = 0; i < decoupe.length; i++) {
			decoupe[i] = this.decalle_gauche(decoupe[i], Des.tab_decalage[ronde-1]);
		}
		int [] recollage = this.recollage_bloc(decoupe);
		int[] permutation_48=this.generePermutation(48);
		int [] cle_48=this.permutation(permutation_48, recollage);
		return cle_48;
	}

	int[] xor( int[] tab1, int[] tab2) {
		int[] tab_xor = new int[tab1.length]; 
		for(int i =0; i < tab1.length; i++) {
			tab_xor[i]=Math.abs((tab1[i]-tab2[i]));
		}
		return tab_xor;
	}

	int [] fonction_S(int[] tab) {
		int lig = tab[0]*2 + tab[5];
		int col = tab[1]*8+tab[2]*4+tab[3]*2 +tab[4];
		int valeur = this.s[lig][col];
		int[] nouveau_tab = new int[4];

		String valeurBinaire= Integer.toBinaryString(valeur);
		while(valeurBinaire.length()<4){
			valeurBinaire = "0"+valeurBinaire;
		}
		for (int i=0; i<nouveau_tab.length;i++) {
			nouveau_tab[i]=valeurBinaire.charAt(i)-'0';
		}
		return nouveau_tab;
	}

	int[] fonction_F(int[] uneCle, int[] unD) {

		int[] extensionD = this.permutation(Des.e, unD);
		int[] exentionXorCle = this.xor(extensionD, uneCle);
		int[][] decoupe = this.decoupage(exentionXorCle, 8);
		for(int i = 0; i < decoupe.length; i++) {
			decoupe[i] = fonction_S(decoupe[i]);	
		}
		int[] recolle = this.recollage_bloc(decoupe); 
		return recolle;
	}

	public int [] crypte( String message) {
		int[] texte_binaire = this.stringToBits(message);
		int taille = texte_binaire.length;
		int nb_bloc = taille/Des.taille_bloc;
		int [] tab_a_crypte = new int[nb_bloc*Des.taille_bloc];
		int[] tab_non_crypte = new int[taille-nb_bloc*Des.taille_bloc];
		for(int i = 0; i <taille; i++) {
			if ( i < nb_bloc*Des.taille_bloc) {
				tab_a_crypte[i] = texte_binaire[i];
			}
			else {
				tab_non_crypte[i-nb_bloc*Des.taille_bloc] = texte_binaire[i];
			}

		}
		/// séparation en un bloc divisible par 64 et un bloc restant, ce dernier n'étant pas crypté
		if(nb_bloc > 0) {
			int[][] decoupe = this.decoupage(tab_a_crypte, nb_bloc);
			for(int k = 0; k < decoupe.length; k++) {
				int[] bloc = decoupe[k];
				if(bloc.length == Des.taille_bloc) {
					int[] permut = Des.perm_initiale;
					bloc = this.permutation(permut, bloc);
					int[][] decoupe_deux = this.decoupage(bloc, 2);
					int[] g = decoupe_deux[0];
					int[] d = decoupe_deux[1];
					///
					for(int i = 0; i < Des.nb_ronde; i++) {
						this.s = this.s_tab[i];
						int[] cle = this.tab_cles.get(i);
						int[] d_copy = d.clone();
						d = this.xor(g, this.fonction_F(cle, d));
						g = d_copy;
					}
					///
					decoupe_deux[0] = g;
					decoupe_deux[1] = d;
					bloc = this.recollage_bloc(decoupe_deux);;
					bloc = this.invPermutation(permut, bloc);
					decoupe[k] = bloc;
				}
				else {
					System.out.println("erreur cryptage : bloc de taille " + bloc.length);
				}
			}

			int[] recolle = this.recollage_bloc(decoupe);
			int[][] decoupe_total = new int[2][];
			decoupe_total[0] = recolle;
			decoupe_total[1] = tab_non_crypte;
			int [] recolle_total = this.recollage_bloc(decoupe_total);	
			return recolle_total;
		}
		else {
			return tab_non_crypte;
		}
	}

	public int [] crypte( int[] messageCode) {
		int taille = messageCode.length;
		int nb_bloc = taille/Des.taille_bloc;
		int [] tab_a_crypte = new int[nb_bloc*Des.taille_bloc];
		int[] tab_non_crypte = new int[taille-nb_bloc*Des.taille_bloc];
		for(int i = 0; i <taille; i++) {
			if ( i < nb_bloc*Des.taille_bloc) {
				tab_a_crypte[i] = messageCode[i];
			}
			else {
				tab_non_crypte[i-nb_bloc*Des.taille_bloc] = messageCode[i];
			}
		}
		/// séparation en un bloc divisible par 64 et un bloc restant, ce dernier n'étant pas crypté
		if(nb_bloc > 0) {
			int[][] decoupe = this.decoupage(tab_a_crypte, nb_bloc);
			for(int k = 0; k < decoupe.length; k++) {
				int[] bloc = decoupe[k];
				if(bloc.length == Des.taille_bloc) {
					int[] perm = Des.perm_initiale;
					bloc = this.permutation(perm, bloc);
					int[][] decoupe_deux = this.decoupage(bloc, 2);
					int[] g = decoupe_deux[0];
					int[] d = decoupe_deux[1];
					///
					for(int i = 0; i < Des.nb_ronde; i++) {
						this.s = this.s_tab[i];
						int[] cle = this.tab_cles.get(i);
						int[] d_save = d.clone();
						d = this.xor(g, this.fonction_F(cle, d));
						g = d_save;
					}
					///
					decoupe_deux[0] = g;
					decoupe_deux[1] = d;
					bloc = this.recollage_bloc(decoupe_deux);;
					bloc = this.invPermutation(perm, bloc);
					decoupe[k] = bloc;
				}
				else {
					System.out.println("erreur cryptage : bloc de taille " + bloc.length);
				}
			}
			int[] recolle = this.recollage_bloc(decoupe);
			int[][] decoupe_total = new int[2][];
			decoupe_total[0] = recolle;
			decoupe_total[1] = tab_non_crypte;
			int [] recolle_total = this.recollage_bloc(decoupe_total);	
			return recolle_total;
		}
		else {
			return tab_non_crypte;
		}	
	}

	public String decrypte(int[] messageCode) {
		int taille = messageCode.length;
		int nb_bloc = taille/Des.taille_bloc;
		int [] tab_a_decrypte = new int[nb_bloc*Des.taille_bloc];
		int[] tab_non_crypte = new int[taille-nb_bloc*Des.taille_bloc];
		for(int i = 0; i <taille; i++) {
			if ( i < nb_bloc*Des.taille_bloc) {
				tab_a_decrypte[i] = messageCode[i];
			}
			else {
				tab_non_crypte[i-nb_bloc*Des.taille_bloc] = messageCode[i];
			}
		}
		if (nb_bloc > 0 ) {
			int[][] decoupe = this.decoupage(tab_a_decrypte, nb_bloc);
			for(int i = 0; i < decoupe.length; i++) {
				int[] bloc = decoupe[i];
				int[] perm = Des.perm_initiale;
				bloc = this.permutation(perm, bloc);
				int[][] decoupe_deux = this.decoupage(bloc, 2);
				int[] g = decoupe_deux[0];
				int[] d = decoupe_deux[1];
				for(int j = 0; j < Des.nb_ronde; j++) {

					int[][] s_local = this.s_tab[Des.nb_ronde - j - 1];
					this.s = s_local;
					int[] cle = this.tab_cles.get(Des.nb_ronde -j - 1);
					int[] g_save = g.clone();
					g = this.xor(d, this.fonction_F(cle, g));
					d = g_save;
				}
				decoupe_deux[0] = g;
				decoupe_deux[1] = d;
				bloc = this.recollage_bloc(decoupe_deux);
				bloc = this.invPermutation(perm, bloc);
				decoupe[i] = bloc;
			}

			int[] recolle = this.recollage_bloc(decoupe);
			int[][] decoupe_total = new int[2][];
			decoupe_total[0] = recolle;
			decoupe_total[1] = tab_non_crypte;
			int [] recolle_total = this.recollage_bloc(decoupe_total);	
			return this.bitsToString(recolle_total);
		}
		else {
			return this.bitsToString(tab_non_crypte);
		}
	}

	public int[] decrypteTableau(int[] messageCode) {
		int taille = messageCode.length;
		int nb_bloc = taille/Des.taille_bloc;
		int [] tab_a_decrypte = new int[nb_bloc*Des.taille_bloc];
		int[] tab_non_crypte = new int[taille-nb_bloc*Des.taille_bloc];
		for(int i = 0; i <taille; i++) {
			if ( i < nb_bloc*Des.taille_bloc) {
				tab_a_decrypte[i] = messageCode[i];
			}
			else {
				tab_non_crypte[i-nb_bloc*Des.taille_bloc] = messageCode[i];
			}
		}
		if (nb_bloc > 0 ) {
			int[][] decoupe = this.decoupage(tab_a_decrypte, nb_bloc);
			for(int i = 0; i < decoupe.length; i++) {
				int[] bloc = decoupe[i];
				int[] perm = Des.perm_initiale;
				bloc = this.permutation(perm, bloc);
				int[][] decoupe_deux = this.decoupage(bloc, 2);
				int[] g = decoupe_deux[0];
				int[] d = decoupe_deux[1];
				for(int j = 0; j < Des.nb_ronde; j++) {

					int[][] s_local = this.s_tab[Des.nb_ronde - j - 1];
					this.s = s_local;
					int[] cle = this.tab_cles.get(Des.nb_ronde -j - 1);
					int[] g_save = g.clone();
					g = this.xor(d, this.fonction_F(cle, g));
					d = g_save;
				}
				decoupe_deux[0] = g;
				decoupe_deux[1] = d;
				bloc = this.recollage_bloc(decoupe_deux);
				bloc = this.invPermutation(perm, bloc);
				decoupe[i] = bloc;
			}

			int[] recolle = this.recollage_bloc(decoupe);
			int[][] decoupe_total = new int[2][];
			decoupe_total[0] = recolle;
			decoupe_total[1] = tab_non_crypte;
			int [] recolle_total = this.recollage_bloc(decoupe_total);	
			return recolle_total;
		}
		else {
			return tab_non_crypte;
		}
	}

	
	
	
	
	
	
	
}
