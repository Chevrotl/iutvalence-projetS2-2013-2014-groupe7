package fr.iutvalence.projetS2.java.groupe7;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * Classe representant un zombie, c'est un personnage non jouable
 * @author Timoth�e
 *
 *
 */ 

public class Zombie extends Personnage
{
	/**
	 * feuille de sprite du personnage du zombie
	 */
	private SpriteSheet spriteZombie;


	/**
	 * constructeur
	 * @param x position x du zombie
	 * @param y position y du zombie
	 * @param orientation orientation du zombie
	 */
	public Zombie(float x, float y, Orientation orientation)
	{
		
		super(x,y, orientation);
		
		// On test directement dans le constructeur la validite du sprite
		try
		{
			this.spriteZombie = new SpriteSheet("/graphismes/sprites/zombi.png", 32, 32);
			
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
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 3), 100); 
			return animationARenvoyer;
		}
		//Ouest, immobile
		case 1:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 1), 100);
			return animationARenvoyer;
		}
		//Sud, immobile
		case 2:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 0), 100);			
			return animationARenvoyer;
		}
		//Est, immobile
		case 3:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 2), 100);			
			return animationARenvoyer;
		}
		//Nord, mobile
		case 4:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(0, 3), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 3), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(2, 3), 100);		
			return animationARenvoyer;
		}
		//Ouest, mobile
		case 5:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(0, 1), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 1), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(2, 1), 100);
			return animationARenvoyer;
		}
		//Sud, mobile
		case 6:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(0, 0), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 0), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(2, 0), 100);
			return animationARenvoyer;
		}
		//Est, mobile
		case 7:{
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(0, 2), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(1, 2), 100);
			animationARenvoyer.addFrame(this.spriteZombie.getSprite(2, 2), 100);		
			return animationARenvoyer;
		}
		}
		return null;
	}

	
	
	
}