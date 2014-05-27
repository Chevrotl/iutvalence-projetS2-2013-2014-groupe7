package fr.iutvalence.projetS2.java.groupe7;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * @author Lucas
 *Classe representant le joueur du jeu, c'est un personnage particulier
 */
public class Joueur extends Personnage
{

	/**
	 * Nom du joueur
	 */
	public String nomDuJoueur ;

	/**
	 * feuille de sprite du personnage du joueur
	 */
	private SpriteSheet spriteJoueur;
	
	
	/** Coordonnée des murs en fonction de la position du personnage */
	private int coordonneeMurNord = 832;
	private int coordoneeMurEst = 1152;
	private int coordoneeMurSud = 1248;
	private int coordonneeMurOuest = 576;
	
	private boolean aTranslate = false ;

	private int nombreTranslateVertical = 0;
	private int nombreTranslateHorizontal =0;
	
	
	/**
	 * constructeur
	 * @param x position x du joueur
	 * @param y position y du joueur
	 * @param orientation orientation du joueur
	 * @param nomDuJoueur nom du joueur (permet de differencier des mobs)
	 */
	public Joueur(float x, float y, Orientation orientation, String nomDuJoueur)
	{
		super(x,y, orientation);
		

		
		this.nomDuJoueur = nomDuJoueur ;
		
		// On test directement dans le constructeur la validite du sprite
		try
		{
			this.spriteJoueur = new SpriteSheet("/graphismes/sprites/PersonnagePrincipal.png", 32, 32);
			
			for(int numDirection = 0; numDirection<this.animationSprite.length ; numDirection++)
			{
				this.animationSprite[numDirection]=animationPersonnage(numDirection);
			}
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}



	/**
	 * methode renvoyant l'animation du sprite suivant l'orienation est si il est en mouvement ou non
	 * @param numeroDirection numero de la direction du personnage
	 * @return l'animation du sprite
	 */
	public  Animation animationPersonnage(int numeroDirection) 
	{
		
		Animation animationARenvoyer = new Animation();
		switch(numeroDirection)
		{
		
		//Nord, immobile
		case 0: { 
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 3), 100); 
			return animationARenvoyer;
		}
		//Ouest, immobile
		case 1:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 1), 100);
			return animationARenvoyer;
		}
		//Sud, immobile
		case 2:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 0), 100);			
			return animationARenvoyer;
		}
		//Est, immobile
		case 3:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 2), 100);			
			return animationARenvoyer;
		}
		//Nord, mobile
		case 4:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 3), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 3), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 3), 100);		
			return animationARenvoyer;
		}
		//Ouest, mobile
		case 5:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 1), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 1), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 1), 100);
			return animationARenvoyer;
		}
		//Sud, mobile
		case 6:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 0), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 0), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 0), 100);
			return animationARenvoyer;
		}
		//Est, mobile
		case 7:{
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(0, 2), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(1, 2), 100);
			animationARenvoyer.addFrame(this.spriteJoueur.getSprite(2, 2), 100);		
			return animationARenvoyer;
		}
		}
		return null;
	
	}
	

	
		public void doitTranslate()
		{
			if (this.x >= this.coordoneeMurEst)
			{
				this.coordoneeMurEst += DeplacementState.LARGEUR_MAP ;
				this.coordonneeMurOuest += DeplacementState.LARGEUR_MAP ;
				this.nombreTranslateHorizontal-- ;
				this.aTranslate = true ;
			}
			if (this.x <= this.coordonneeMurOuest)
			{
				this.coordoneeMurEst -= DeplacementState.LARGEUR_MAP ;
				this.coordonneeMurOuest -= DeplacementState.LARGEUR_MAP ;
				this.nombreTranslateHorizontal++;
				this.aTranslate = true ;
			}
			if (this.y <= this.coordonneeMurNord)
			{
				this.coordonneeMurNord -= DeplacementState.HAUTEUR_MAP;
				this.coordoneeMurSud -= DeplacementState.HAUTEUR_MAP;
				this.nombreTranslateVertical++;
				this.aTranslate = true ;
			}
			if (this.y >= this.coordoneeMurSud)
			{
				this.coordonneeMurNord += DeplacementState.HAUTEUR_MAP ;
				this.coordoneeMurSud += DeplacementState.HAUTEUR_MAP ;
				this.nombreTranslateVertical--;
				this.aTranslate = true ;
			}			

		}
		
	
	public int getNombreTranslateVertical()
	{
		return this.nombreTranslateVertical;
	}

	public int getNombreTranslateHorizontal()
	{
		return this.nombreTranslateHorizontal;
	}



	public int getCoordonneeMurNord()
	{
		return this.coordonneeMurNord;
	}



	public int getCoordoneeMurEst()
	{
		return this.coordoneeMurEst;
	}



	public int getCoordoneeMurSud()
	{
		return this.coordoneeMurSud;
	}



	public int getCoordonneeMurOuest()
	{
		return this.coordonneeMurOuest;
	}
	
	
	
	
}
	
