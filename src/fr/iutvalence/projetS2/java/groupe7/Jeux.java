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


	public static final int HAUTEUR_MAP = 416 ; //13 cases 450
	public static final int LARGEUR_MAP = 576; //17 cases 550
	public static final int NOMBRES_CASES_LARGEUR = LARGEUR_MAP / 32 ;
	public static final int NOMBRES_CASES_HAUTEUR = HAUTEUR_MAP / 32 ;
	private static final int POSITION_SPAWN_JOUEUR_X = 864;
	private static final int POSITION_SPAWN_JOUEUR_Y = 1120;
	/**
	 * Joueur actuel, crée dans l'init 
	 */
	private Joueur joueur ;

	private Zombie zombie ;

	//provient du blog, permet l'animation du sprite
	private Animation[] animations = new Animation[8];
	private int numeroDirection = 0 ;


	
	/**
	 * map actuelle
	 */
	TiledMap map ;
	
	private boolean debogueurActive;
	
	private boolean premierTranslate = false ;
	

	public Jeux()
	{
		super("Jeux");
		
	}


	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		// generation de la map 
		//decalage de l'affichage de la map pour venir sur la carte ou spawn le joueur
			g.translate(-LARGEUR_MAP, -2*HAUTEUR_MAP);
			
		this.map.render(0, 0, 0);
		this.map.render(0, 0, 1);
		this.map.render(0, 0, 2);
		
		
		
	

		// creation d'une ombre sous les pieds du personnage
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(this.joueur.x - 10, this.joueur.y - 4, 20, 10);		


		//on baisse l'animation par rapport au coordonnée reelle du personnage pour pouvoir ajuster sa vraie position (en dessous des pieds et non pas en haut a gauche du sprite)

		g.drawAnimation(this.joueur.getAnimationSprite()[(this.numeroDirection + (this.joueur.estEnMouvement ? 4 : 0))], 
				this.joueur.x-16, this.joueur.y-32);


	//	g.drawAnimation(this.zombie.getAnimationSprite()[(this.numeroDirection + (this.joueur.estEnMouvement ? 4 : 0))], 
		//		this.zombie.x-16,this.zombie.y-32);





		//debugueur, permet d'afficher des variables
		if(this.debogueurActive)
		{
			Color colorPoliceDebug = new Color((float)0.8, 0, 0);
			g.setColor(colorPoliceDebug);

			g.drawString("X : "+this.joueur.x+" Y : "+this.joueur.y , 590, 450);
			//g.drawString("X : "+this.zombie.x+" Y : "+this.zombie.x,200,40);

			g.drawString("caseAAteindreYNord : "+this.joueur.caseAAtteindreYNord, 590, 470);
			g.drawString("caseAAteindreYSud : "+this.joueur.caseAAtteindreYSud, 590, 490);
			g.drawString("caseAAteindreXEst : "+this.joueur.caseAAtteindreXEst, 590, 510);
			g.drawString("caseAAteindreXOuest : "+this.joueur.caseAAtteindreXOuest, 590, 530);
			g.drawString("estUneCaseInterdite : "+this.estUneCaseInterdite(this.joueur.x, this.joueur.y), 590, 550);
			g.drawString("premierTranslate : "+this.premierTranslate, 590, 570);
			

		}
		
		this.map.render(0, 0, 3);


			
	
	}


	@Override
	public void init(GameContainer container) throws SlickException
	{

		this.map  = new TiledMap("graphismes/maps/new map/MapCentrale.tmx");

	//	this.zombie = new Zombie(100, 100, Orientation.EST);

		this.joueur = new Joueur(POSITION_SPAWN_JOUEUR_X, POSITION_SPAWN_JOUEUR_Y, Orientation.NORD, "Link") ; 



	}



	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		/**
		 * Future position du personnage, servira a savoir si il peut se deplacer sur une case qui n'est pas bloquée
		 */
		float futurePositionX = this.joueur.x ;
		float futurePositionY = this.joueur.y ;

		
		//calcul des coordonnée pour le deplacement case par case
		this.joueur.deplacementDUneCase(futurePositionX, futurePositionY);
		
		//Mouvement fluide, besoin de laisser ca la car intevention de la variable delta
		
	
		if (this.joueur.estEnMouvement) {

			switch (this.joueur.getOrientationPersonnage()) {

			case NORD: futurePositionY = (this.joueur.y -= .1f * delta);  break;		            				
			case OUEST: futurePositionX = (this.joueur.x  -= .1f * delta); break;
			case SUD: futurePositionY = (this.joueur.y += .1f * delta); break;
			case EST: futurePositionX = (this.joueur.x += .1f * delta); break;
			default:
				break;

			}

			//detection des collision pour le joueur humain
			if(!estUneCaseInterdite(futurePositionX, futurePositionY))
			{
				this.joueur.setPositionPersonnageX(futurePositionX);
				this.joueur.setPositionPersonnageY(futurePositionY);
			}
			else
			{
				this.joueur.estEnMouvement = false ;
			}

	
		}


	}



	/**
	 * Methode permettant de renvoyer un boolean suivant si le personnage a le droit d'aller sur la case
	 * @param floatPositionX future position X du personnage
	 * @param floatPositionY future position Y du personnage
	 * @return boolean true : il peut se deplacer, false il ne peut pas
	 */
	public boolean estUneCaseInterdite(float floatPositionX, float floatPositionY)
	{
		
		//Changement de type de float en int car la methode getTileImage a comme parametre des int.
		//La case interdite se fait suivant la position absolue de la case, et non pas sa position en pixel : on convertit
		int positionX = ((int)floatPositionX) / this.map.getTileWidth() ;
		int positionY = ((int)floatPositionY) / this.map.getTileHeight();

		//Methode permetant de renvoyer qu'un certain type de case (en consequence, les case ou le perso ne peut pas bouger)
		Image caseInterdite = this.map.getTileImage(positionX, positionY, this.map.getLayerIndex("bloc"));

		//On verifie si il existe une case bloque,  si oui le personnage ne bouge pas, sinon il peut se deplacer
		return (caseInterdite != null);


	}






	/**
	 * methode qui gere l'appui des touches au clavier
	 */
	public void keyPressed(int key, char c)
	{

		switch(key){
		case Input.KEY_UP:    this.joueur.setOrientationPersonnage(Orientation.NORD); this.joueur.estEnMouvement = true ;  this.numeroDirection = 0;break;
		case Input.KEY_LEFT:  this.joueur.setOrientationPersonnage(Orientation.OUEST); this.joueur.estEnMouvement = true ; this.numeroDirection = 1;  break;
		case Input.KEY_DOWN:  this.joueur.setOrientationPersonnage(Orientation.SUD); this.joueur.estEnMouvement = true ; this.numeroDirection = 2;  break;
		case Input.KEY_RIGHT: this.joueur.setOrientationPersonnage(Orientation.EST); this.joueur.estEnMouvement = true ; this.numeroDirection = 3; break;

		case Input.KEY_F2 : { if(this.debogueurActive)
		{
			this.debogueurActive = false ; break;
		}
		else 
		{
			this.debogueurActive = true ; break;
		}
		}
		case Input.KEY_ESCAPE: System.exit(0);
		}
	}


	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new Jeux()) ; 
			app.setDisplayMode(LARGEUR_MAP, HAUTEUR_MAP,false);
			app.setShowFPS(true);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}


}
