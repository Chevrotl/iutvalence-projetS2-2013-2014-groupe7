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


	private static final int HAUTEUR_MAP = 416 ; //13 cases
	private static final int LARGEUR_MAP = 544; //17 cases
	private static final int NOMBRES_CASES_LARGEUR = LARGEUR_MAP / 32 ;
	private static final int NOMBRES_CASES_HAUTEUR = HAUTEUR_MAP / 32 ;
	
	/**
	 * Joueur actuel, crée dans l'init 
	 */
	private Joueur joueur ;
	
	//utile pour le deplacement case par case, = aux coordonnée de pop du joueur
	private float caseAAtteindreXEst = 224 ;
	private float caseAAtteindreXOuest = 224 ;
	private float caseAAtteindreYNord = 224 ;
	private float caseAAtteindreYSud = 224 ; 
	
	//lors du premier deplacement sur la carte, les coordonnée du deplacement ne se modifient pas de la meme maniere
	//on est oblige de separer le premier deplacement des autres
	private boolean aDejaBougeX = false;
	private boolean aDejaBougeY = false;
	
	//provient du blog, permet l'animation du sprite
	private Animation[] animations = new Animation[8];
	private int numeroDirection = 0 ;
	
	
	
	private boolean estMouvant ;
	/**
	 * map actuelle
	 */
	TiledMap map ;
	
	private boolean debogueurActive;

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

		   g.drawAnimation(this.joueur.getAnimationSprite()[(this.numeroDirection + (this.estMouvant ? 4 : 0))], 
				   this.joueur.getPositionPersonnage().X-16, this.joueur.getPositionPersonnage().Y-32);
		   
		   //debugueur, permet d'afficher des variables
		   if(this.debogueurActive)
		   {
			   Color colorPoliceDebug = new Color((float)0.8, 0, 0);
			   g.setColor(colorPoliceDebug);
		  
			   g.drawString("X : "+this.joueur.getPositionPersonnage().X+" Y :"+this.joueur.getPositionPersonnage().Y , 20, 40);
		   
		   g.drawString("caseAAteindreYNord : "+this.caseAAtteindreYNord, 20, 60);
		   g.drawString("caseAAteindreYSud : "+this.caseAAtteindreYSud, 20, 80);
		   g.drawString("caseAAteindreXEst : "+this.caseAAtteindreXEst, 20, 100);
		   g.drawString("caseAAteindreXOuest : "+this.caseAAtteindreXOuest, 20, 120);
		   g.drawString("aDejaBougeX : "+this.aDejaBougeX,20,140);
		   g.drawString("aDejaBougeY : "+this.aDejaBougeY,20,160);
		   }
	
	}

	
	@Override
	public void init(GameContainer container) throws SlickException
	{
		// TODO Auto-generated method stub
		
		this.map  = new TiledMap("graphismes/maps/MapCentrale.tmx");
		this.joueur = new Joueur(224,224, Orientation.SUD, "Link") ; 
		
		}



	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		/**
		 * Future position du personnage, servira a savoir si il peut se deplacer sur une case qui n'est pas bloquée
		 */
		float futurePositionX = this.joueur.getPositionPersonnage().X ;
		float futurePositionY = this.joueur.getPositionPersonnage().Y ;
				
		
		
		//calcul des coordonnée pour le deplacement case par case
		
		switch(this.joueur.getOrientationPersonnage())
		{
		case NORD: {
			if((int)futurePositionY == (int)this.caseAAtteindreYNord)
	    	{
				if(this.aDejaBougeY)
				{
					this.estMouvant = false ;
					this.caseAAtteindreYNord -= 32;
		    		this.caseAAtteindreYSud -= 32 ;
		    	
				}
				else
				{
					this.estMouvant = false ;
					this.caseAAtteindreYNord -= 32;
					//les coordonnée de la case a atteindre sud ne change pas du coup
					this.aDejaBougeY = true;
				}
	    	}	
		}break;
		case OUEST: {
			if((int)futurePositionX == (int)this.caseAAtteindreXOuest)
	    	{
				if(this.aDejaBougeX)
				{
					this.estMouvant = false ;
					this.caseAAtteindreXEst -= 32;
					this.caseAAtteindreXOuest -= 32;
				}
				else
				{
					this.estMouvant = false ;
					this.caseAAtteindreXOuest -= 32;
					this.aDejaBougeX = true ;
				}
	    	}
		}break;
		
			case SUD: {
				if((int)futurePositionY == (int)this.caseAAtteindreYSud)
		    	{
					if(this.aDejaBougeY)
					{
						this.estMouvant = false ;
						this.caseAAtteindreYNord += 32;
			    		this.caseAAtteindreYSud += 32 ;
			    	
					}
					else
					{
						this.estMouvant = false ;
						this.caseAAtteindreYSud += 32;
						this.aDejaBougeY = true;;
					}
		    	}	
			}break;
			case EST: {
				if((int)futurePositionX == (int)this.caseAAtteindreXEst)
		    	{
					if(this.aDejaBougeX)
					{
						this.estMouvant = false ;
						this.caseAAtteindreXEst += 32;
						this.caseAAtteindreXOuest += 32;
					}
					else
					{
						this.estMouvant = false ;
						this.caseAAtteindreXEst += 32;
						this.aDejaBougeX = true ;
					}
		    	
		    	}
			}break;
		}
	
		
		
		
	
		
		
		  if (this.estMouvant) {
			    
		        switch (this.joueur.getOrientationPersonnage()) {
		        
		        case NORD: futurePositionY = (this.joueur.getPositionPersonnage().Y -= .1f * delta);  break;		            				
				case OUEST: futurePositionX = (this.joueur.getPositionPersonnage().X  -= .1f * delta); break;
		        case SUD: futurePositionY = (this.joueur.getPositionPersonnage().Y += .1f * delta); break;
		        case EST: futurePositionX = (this.joueur.getPositionPersonnage().X += .1f * delta); break;
				default:
					break;
		            
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
	/**
	 * methode qui gere le relachement d'une touche
	 */
//	public void keyReleased(int key, char c)
//	{
//		this.estMouvant = false ;
//	}
//	
	
	
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
