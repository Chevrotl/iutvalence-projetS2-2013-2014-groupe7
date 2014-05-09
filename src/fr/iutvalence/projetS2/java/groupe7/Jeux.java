package fr.iutvalence.projetS2.java.groupe7;


import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
		this.map.render(0, 0, 0);
		
		
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
		/**
		 * Future position du personnage, servira a savoir si il peut se deplacer sur une case qui n'est pas bloquée
		 */
		float futurePositionX = this.joueur.getPositionPersonnage().X ;
		float futurePositionY = this.joueur.getPositionPersonnage().Y ;
			
		  if (this.estMouvant) {
			  
		        switch (this.joueur.getOrientationPersonnage()) {
		            case NORD: futurePositionY = (this.joueur.getPositionPersonnage().Y -= .1f * delta ); break;
		            case OUEST: futurePositionX = (this.joueur.getPositionPersonnage().X  -= .1f * delta); break;
		            case SUD: futurePositionY = (this.joueur.getPositionPersonnage().Y += .1f * delta); break;
		            case EST: futurePositionX = (this.joueur.getPositionPersonnage().X += .1f * delta); break;
		        }
		        
		        if(!estUneCaseInterdite(futurePositionX, futurePositionY))
		        {
		        	this.joueur.setPositionPersonnageX(futurePositionX);
		        	this.joueur.setPositionPersonnageY(futurePositionY);
		        }
		        else
		        {
		        	this.estMouvant = false ;
		        }}
			        
		        
		 
		
	}

	/**
	 * Methode permettant de renvoyer un boolean suivant si le personnage a le droit d'aller sur la case
	 * @param floatPositionX future position X du personnage
	 * @param floatPositionY future position Y du personnage
	 * @return boolean true : il peut se deplacer, false il ne peut pas
	 */
	private boolean estUneCaseInterdite(float floatPositionX, float floatPositionY)
	{
		boolean deplacementInterdit ;
		//Changement de type de float en int car la methode getTileImage a comme parametre des int.
		//La case interdite se fait suivant la position absolue de la case, et non pas sa position en pixel : on convertit
	    int positionX = ((int)floatPositionX) / this.map.getTileWidth() ;
	    int positionY = ((int)floatPositionY) / this.map.getTileHeight();
	    
	    //Methode permetant de renvoyer qu'un certain type de case (en consequence, les case ou le perso ne peut pas bouger)
		Image caseInterdite = this.map.getTileImage(positionX, positionY, this.map.getLayerIndex("bloc"));
		
		//On verifie si il existe une case bloque,  si oui le personnage ne bouge pas, sinon il peut se deplacer
		return deplacementInterdit = (caseInterdite != null);
	
		
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
        
        case Input.KEY_ESCAPE: System.exit(0);
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
