package fr.iutvalence.projetS2.java.groupe7;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Joueur extends Personnage
{

	public String nomDuJoueur ;
	
	private SpriteSheet spriteJoueur; 
	
	private static final int POSITION_JOUEUR_X_PAR_DEFAUT = 5;
	private static final int POSITION_JOUEUR_Y_PAR_DEFAUT = 5;
	
	
	/**
	 * superconstructeur
	 * @param position position du joueur
	 * @param orientation orientation du joueur
	 */
	public Joueur(float x, float y, Orientation orientation, String nomDuJoueur)
	{
		
		super(x,y, orientation);
		this.nomDuJoueur = nomDuJoueur ;
		
	}
	


	/**
	 * Feuille de sprite du personnage princpal, on charge l'image avec toute les mouvement du personnage, puis la taille de chaque mouvement
	 * besoin d'un try/catch sinon ne compile pas 
	 * Parce que le lien peut etre faux et donc soulever une erreur
	 */
		{
	try
	{
	this.spriteJoueur = new SpriteSheet("/graphismes/sprites/PersonnagePrincipal.png", 32, 32)  ;
	}
	catch (SlickException e)
	{
		e.printStackTrace();
	}
		}

	
	/**
	 * methode renvoyant l'animation du sprite suivant l'orienation est si il est en mouvement ou non
	 * @param orientation oriention du sprite duquel on veut l'animation
	 * @param enMouvement savoir si le personnage est mobile ou non
	 * @return l'animation du sprite
	 */
	public  Animation animationPersonnage(Orientation orientation, boolean enMouvement) 
	{
		Animation animationARenvoyer = new Animation();
		
		if(enMouvement)
		{
			switch(orientation)
			{
			case NORD: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 3), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 3), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 3), 100);
				}
			
			case SUD: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 0), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 0), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 0), 100);
				}
			
			case EST: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 2), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 2), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 2), 100);
				}
			
			case OUEST: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 1), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 1), 100);
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 1), 100);
				}
			}
		}
		else
		{
			switch(orientation)
			{
			case NORD: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 3), 100);
				}
			
			case SUD: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 0), 100);
				}
			
			case EST: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 2), 100);
				}
			
			case OUEST: {
				animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 1), 100);
				}
		}
}
		return animationARenvoyer;
	
}
}
	

