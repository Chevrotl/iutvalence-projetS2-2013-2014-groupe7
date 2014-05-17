package fr.iutvalence.projetS2.java.groupe7;

/**
 * @author Lucas
 * 
 */



public enum Carte
{	
	

	CARTE_PRINCIPALE {
		
		//TODO Gerer les trasnlate avec des multiples des coordonnées
		
		int coordonneeMurNord = 832 ;
		int coordonneeMurSud = 832 - Jeux.HAUTEUR_MAP ;
		int coordonneeMurOuest = 576 ;
		int coordonneeMurEst = 576 + Jeux.LARGEUR_MAP ; 
	};

	
}
