package fr.iutvalence.projetS2.java.groupe7;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
	Joueur joueur ;
	
	/**
	 * creation de deux variables pour faciliter la lecture du code
	 */
	
	
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
		// TODO Auto-generated method stub
		this.map.render(0, 0);
		// creation d'une ombre sous les pieds du personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(this.joueur.getPositionPersonnage().X - 10, this.joueur.getPositionPersonnage().Y - 4, 20, 10);		
		
		//on baisse l'animation par rapport au coordonnée reelle du personnage pour pouvoir ajuster sa vraie position (en dessous des pieds et non pas en haut a gauche du sprite)
		g.drawAnimation(this.joueur.animationPersonnage(this.joueur.getOrientationPersonnage(), this.joueur.estEnMouvement), this.joueur.getPositionPersonnage().X -16, this.joueur.getPositionPersonnage().Y-30);
	
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
		// TODO Auto-generated method stub
		  if (this.joueur.estEnMouvement) {
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
		this.joueur.estEnMouvement = true ;
		switch(key){
        case Input.KEY_UP:    this.joueur.setOrientationPersonnage(Orientation.NORD); break;
        case Input.KEY_LEFT:  this.joueur.setOrientationPersonnage(Orientation.OUEST);  break;
        case Input.KEY_DOWN:  this.joueur.setOrientationPersonnage(Orientation.SUD);  break;
        case Input.KEY_RIGHT: this.joueur.setOrientationPersonnage(Orientation.EST);  break;
		}
	}
	/**
	 * methode qui gere le relachement d'une touche
	 */
	public void keyReleased(int key, char c)
	{
		this.joueur.estEnMouvement = false ;
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Jeux()) ; // a changer pour new Jeu
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
