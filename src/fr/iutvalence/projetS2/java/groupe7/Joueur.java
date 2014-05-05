package fr.iutvalence.projetS2.java.groupe7;

import java.util.Random;

public class Joueur extends Personnage
{

	public String nomDuJoueur ;
	
	/**
	 * superconstructeur
	 * @param position position du joueur
	 * @param orientation orientation du joueur
	 */
	public Joueur(Position position, Orientation orientation, String nomDuJoueur)
	{
		super(position, orientation);
		this.nomDuJoueur = nomDuJoueur ;
		
	}


	
	
}
