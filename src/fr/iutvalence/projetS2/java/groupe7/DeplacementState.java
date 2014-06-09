package fr.iutvalence.projetS2.java.groupe7;

import java.nio.file.attribute.PosixFilePermissions;

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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tests.SpriteSheetFontTest;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Classe gerant l'affichage de la carte
 * 
 * @author Lucas
 * 
 */
public class DeplacementState extends BasicGameState
{

	public static final int ID = 1;
	
	private StateBasedGame game;
	
	public static final int HAUTEUR_MAP = 416; // 13 cases 450
	public static final int LARGEUR_MAP = 576; // 17 cases 550
	public static final int NOMBRES_CASES_LARGEUR = LARGEUR_MAP / 32;
	public static final int NOMBRES_CASES_HAUTEUR = HAUTEUR_MAP / 32;
	private static final int POSITION_SPAWN_JOUEUR_X = 864;
	private static final int POSITION_SPAWN_JOUEUR_Y = 1120;

	private Joueur joueur;

	private Zombie[] zombies = new Zombie[3];

	private int numeroDirection = 0;

	private static TiledMap map;
	private boolean debogueurActive;

	private int time = 0 ;

	private boolean uneSecondeEcoulee = false ;
	private int nombreSecondeEcoulee = 0 ;

	private boolean cercleCombat = false ;

	public static TiledMap getMap()
	{
		return map;
	}

	/**
	 * methode qui gere l'appui des touches au clavier
	 */
	public void keyPressed(int key, char c)
	{

		switch (key)
		{
		case Input.KEY_UP:
			this.joueur.setOrientationPersonnage(Orientation.NORD);
			this.joueur.estEnMouvement = true;
			this.joueur.numeroDirection = 0;
			break;
		case Input.KEY_LEFT:
			this.joueur.setOrientationPersonnage(Orientation.OUEST);
			this.joueur.estEnMouvement = true;
			this.joueur.numeroDirection = 1;
			break;
		case Input.KEY_DOWN:
			this.joueur.setOrientationPersonnage(Orientation.SUD);
			this.joueur.estEnMouvement = true;
			this.joueur.numeroDirection = 2;
			break;
		case Input.KEY_RIGHT:
			this.joueur.setOrientationPersonnage(Orientation.EST);
			this.joueur.estEnMouvement = true;
			this.joueur.numeroDirection = 3;
			break;

		case Input.KEY_F2:
			this.debogueurActive = !this.debogueurActive ;
			break;
		case Input.KEY_A:
			this.cercleCombat = !this.cercleCombat ;
			break;
			
		case Input.KEY_ESCAPE:
			System.exit(0);
		}
	}
	
	/**
	 * Methode qui gere le relachement d'une touche
	 */
	public void keyReleased(int key, char c)
	{
		this.joueur.estEnMouvement = false ;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{

		this.map = new TiledMap("graphismes/maps/new map/MapCentrale.tmx");


		this.zombies[0] = new Zombie(672, 1028, Orientation.SUD) ;
		this.zombies[1] = new Zombie(768, 1120, Orientation.OUEST);
		this.zombies[2] = new Zombie(992, 672, Orientation.EST);
	

		this.joueur = new Joueur(POSITION_SPAWN_JOUEUR_X, POSITION_SPAWN_JOUEUR_Y, Orientation.NORD, "Link");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		// generation de la map
				// decalage de l'affichage de la map pour venir sur la carte ou spawn le
				// joueur
				g.translate(-LARGEUR_MAP, -2 * HAUTEUR_MAP);

				this.joueur.doitTranslate(); 
				g.translate(this.joueur.getNombreTranslateHorizontal()*LARGEUR_MAP, this.joueur.getNombreTranslateVertical()*HAUTEUR_MAP);


				this.map.render(0, 0, 0);
				this.map.render(0, 0, 1);
				this.map.render(0, 0, 2);

				// creation d'une ombre sous les pieds du personnage
				g.setColor(new Color(0, 0, 0, .5f));
				g.fillOval(this.joueur.x - 10, this.joueur.y - 4, 20, 10);

				g.drawAnimation(
						this.joueur.getAnimationPersonnage(),
						this.joueur.x - 16, this.joueur.y - 32); // on baisse la position pour avoir la position sous les pieds


				for(int i = 0 ; i < this.zombies.length ; i++)
				{
					g.drawAnimation(
							this.zombies[i].getAnimationPersonnage(),
							this.zombies[i].x-16,this.zombies[i].y-32);

				}


				this.map.render(0, 0, 3);

				// debugueur, permet d'afficher des variables

				container.setShowFPS(this.debogueurActive);
				if (this.debogueurActive)
				{
					g.fillRect(this.joueur.getCoordonneeMurOuest()+15, this.joueur.getCoordonneeMurNord()+15, 300, 250);

					Color colorPoliceDebug = new Color((float) 0.8, 0, 0);
					g.setColor(colorPoliceDebug);

					g.drawString("X : " + this.joueur.x + " Y : " + this.joueur.y,  this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+20);
					g.drawString("caseAAtteindreNord : "+this.joueur.caseAAtteindreYNord,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+60);
					g.drawString("caseAAtteindreOuest : "+this.joueur.caseAAtteindreXOuest,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+80);
					g.drawString("caseAAtteindreSud : "+this.joueur.caseAAtteindreYSud,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+100);
					g.drawString("caseAAtteindreEst : "+this.joueur.caseAAtteindreXEst,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+120);
					g.drawString("time : "+this.time,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+140);
					g.drawString("uneSecondeEcoulee : "+this.uneSecondeEcoulee,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+160);
					g.drawString("nombreSecondesEcoulees : "+this.nombreSecondeEcoulee,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+180);
					g.drawString("OrientationPersonnage : "+this.joueur.getOrientationPersonnage(), this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+200); 
					g.drawString("caseBloquee : "+this.joueur.estUneCaseInterdite(this.joueur.x,this.joueur.y), this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+220);

					g.drawString("positionCaseRelativeX : "+this.obtenirPositionCaseRelative(this.joueur.x,this.joueur.y).X, this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+240);
					g.drawString("positionCaseRelativeY : "+this.obtenirPositionCaseRelative(this.joueur.x,this.joueur.y).Y, this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+260);
					
					g.drawString("positionCaseParCaseX : "+this.obtenirPositionCaseParCase(
							this.obtenirPositionCaseRelative(this.joueur.x,this.joueur.y).X,
							this.obtenirPositionCaseRelative(this.joueur.x,this.joueur.y).Y).X, this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+280);
					

					g.drawString("positionCaseParCaseY : "+this.obtenirPositionCaseParCase(
							this.obtenirPositionCaseRelative(this.joueur.x,this.joueur.y).X,
							this.obtenirPositionCaseRelative(this.joueur.x,this.joueur.y).Y).Y, this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+300);
					

				}
				
				if(this.cercleCombat)
				{
//					g.fillOval(this.joueur.x, this.joueur.y, 128, 128, 20);
					g.setColor((new Color(0, 0, 0, 0)));
					g.fillRect(this.joueur.x-800, this.joueur.y-500, 1600, 1200);
					g.setColor((new Color(0, 0, 0)));
					g.fillRoundRect(this.joueur.x, this.joueur.y, 128, 128, 5);
				}

		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException //game pour entrer dans différent states
	{
		//delta = 1 milisecondes
				this.time += delta ;
				//TODO Creer une methode qui permet de creer un decalage du nombre de seconde souhaite (decoupe delta en un modulo)
				
				if(this.time % 2000 == 0)
				{
					this.uneSecondeEcoulee = true ;
					this.nombreSecondeEcoulee++ ;
				}
				else
					this.uneSecondeEcoulee = false ;



				if(this.uneSecondeEcoulee)
				{
					for(int i = 0 ; i < this.zombies.length ; i++)
					{
						this.zombies[i].deplacementAleatoire() ;

					}	
				}

				for(int i = 0 ; i < this.zombies.length ; i++)
				{
					if (this.zombies[i].estEnMouvement)
					{
						this.zombies[i].deplacementDUneCase(delta);
					}
				}
				
				//Le deplacement est fluide, case par case pour le combat
				
				if (this.joueur.estEnMouvement)
				{	
					this.joueur.deplacementFluide(delta);
				}
		
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	
	 public Position obtenirPositionCaseParCase(float positionX, float positionY)
	 {
		 Position positionARenvoyer = new Position() ;
		 positionARenvoyer.X = positionX*32 ; 
		 positionARenvoyer.Y = positionY*32 ;
		 return positionARenvoyer ;
	 }
	 
	 public Position obtenirPositionCaseRelative(float positionX, float positionY)
	 {
		 Position positionARenvoyer = new Position() ;
		 positionARenvoyer.X = positionX/32 ; 
		 positionARenvoyer.Y = positionY/32 ;
		 positionARenvoyer.X = (int)positionARenvoyer.X ;
		 positionARenvoyer.Y = (int)positionARenvoyer.Y ;
		 return positionARenvoyer ;
	 }

}
