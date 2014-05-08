package fr.iutvalence.projetS2.java.groupe7;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tests.SpriteSheetFontTest;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Classe gerant l'affichage de la carte
 * @author Lucas
 *
 */
public class Jeux extends BasicGame
{


	private static final int HAUTEUR_MAP = 416 ;
	private static final int LARGEUR_MAP = 544;
	
	/**
	 * Joueur actuel, crée dans l'init 
	 */
	private Joueur joueur ;
	
	//provient du blog
	private Animation[] animations = new Animation[8];
	private int numeroDirection = 0 ;
	
	
	
	private boolean estMouvant ;
	/**
	 * map actuelle
	 */
	TiledMap map ;

	public Jeux()
	{
		super("Jeux");
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		// generation de la map
		this.map.render(0, 0);
		
		
		// creation d'une ombre sous les pieds du personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(this.joueur.getPositionPersonnage().X - 10, this.joueur.getPositionPersonnage().Y - 4, 20, 10);		
		
		
		//on baisse l'animation par rapport au coordonnée reelle du personnage pour pouvoir ajuster sa vraie position (en dessous des pieds et non pas en haut a gauche du sprite)

		   g.drawAnimation(this.joueur.getAnimationSprite()[(this.numeroDirection + (this.estMouvant ? 4 : 0))], this.joueur.getPositionPersonnage().X-16, this.joueur.getPositionPersonnage().Y-30);
	}

	
	@Override
	public void init(GameContainer container) throws SlickException
	{
		// TODO Auto-generated method stub
		
		this.map  = new TiledMap("graphismes/maps/MapCentrale.tmx");
		this.joueur = new Joueur(200,200, Orientation.SUD, "Link") ; 
		


		}



	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
			
		  if (this.estMouvant) {
			  
		        switch (this.joueur.getOrientationPersonnage()) {
		            case NORD: this.joueur.setPositionPersonnageY(this.joueur.getPositionPersonnage().Y -= .1f * delta ); break;
		            case OUEST: this.joueur.setPositionPersonnageX(this.joueur.getPositionPersonnage().X  -= .1f * delta); break;
		            case SUD: this.joueur.setPositionPersonnageY(this.joueur.getPositionPersonnage().Y += .1f * delta); break;
		            case EST: this.joueur.setPositionPersonnageX(this.joueur.getPositionPersonnage().X += .1f * delta); break;
		        }
		        
		  }
		
	}

	/**
	 * methode qui gere l'appui des touches au clavier
	 */
	public void keyPressed(int key, char c)
	{
	
		switch(key){
        case Input.KEY_UP:    this.joueur.setOrientationPersonnage(Orientation.NORD);this.estMouvant = true ; this.numeroDirection = 0;break;
        case Input.KEY_LEFT:  this.joueur.setOrientationPersonnage(Orientation.OUEST);this.estMouvant = true ; this.numeroDirection = 1;  break;
        case Input.KEY_DOWN:  this.joueur.setOrientationPersonnage(Orientation.SUD);this.estMouvant = true ; this.numeroDirection = 2;  break;
        case Input.KEY_RIGHT: this.joueur.setOrientationPersonnage(Orientation.EST);this.estMouvant = true ; this.numeroDirection = 3; break;
		}
	}
	/**
	 * methode qui gere le relachement d'une touche
	 */
	public void keyReleased(int key, char c)
	{
		this.estMouvant = false ;
	}
	
	
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Jeux()) ; 
			app.setDisplayMode(LARGEUR_MAP, HAUTEUR_MAP,false);
			app.setShowFPS(false);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	

}
