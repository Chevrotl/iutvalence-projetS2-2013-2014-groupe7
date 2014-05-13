package fr.iutvalence.projetS2.java.groupe7;

import java.util.Random;

/**
 * Type Orientation
 * @author chevrotl
 *
 */
public enum Orientation
{
	/**
	 * Entite oriente vers le haut
	 */
	NORD,
	
	/**
	 * Entite oriente vers le bas
	 */
	SUD,
	
	/**
	 * Entite oriente vers la droite
	 */
	EST,
	
	/**
	 * Entite oriente vers la gauche
	 */
	OUEST ;
	
	/**
	 * renvoi une Orientation au hasard
	 * @return Orientation au hasard
	 */
	public static Orientation obtenirOrientationAleatoire()
	{
		Random random = new Random();
		int numeroOrientation = random .nextInt(Orientation.values().length);
		
		return Orientation.values()[numeroOrientation];
	}
	
	
}
