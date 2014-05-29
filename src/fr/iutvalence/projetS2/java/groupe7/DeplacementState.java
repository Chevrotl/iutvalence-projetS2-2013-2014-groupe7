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
 * 
 * @author Lucas
 * 
 */
public class DeplacementState extends BasicGame
{

	public static final int HAUTEUR_MAP = 416; // 13 cases 450
	public static final int LARGEUR_MAP = 576; // 17 cases 550
	public static final int NOMBRES_CASES_LARGEUR = LARGEUR_MAP / 32;
	public static final int NOMBRES_CASES_HAUTEUR = HAUTEUR_MAP / 32;
	private static final int POSITION_SPAWN_JOUEUR_X = 864;
	private static final int POSITION_SPAWN_JOUEUR_Y = 1120;

	private Joueur joueur;

	private Zombie zombie;

	private int numeroDirection = 0;

	private static TiledMap map;
	private int coordonneeMurNord = 832;
	private int coordoneeMurEst = 1152;
	private int coordoneeMurSud = 1248;
	private int coordonneeMurOuest = 576;

	private int nombreTranslateHorizontal = 0;
	private int nombreTranslateVertical = 0;

	private boolean debogueurActive;

	private int time = 0 ;

	private boolean uneSecondeEcoulee = false ;
	private int nombreSecondeEcoulee = 0 ;

	public DeplacementState()
	{
		super("Jeux");

	}

	public static TiledMap getMap()
	{
		return map;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
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

		g.drawAnimation(this.zombie.getAnimationPersonnage(),
				this.zombie.x-16,this.zombie.y-32);


		this.map.render(0, 0, 3);

		// debugueur, permet d'afficher des variables

		container.setShowFPS(this.debogueurActive);
		if (this.debogueurActive)
		{
			Color colorPoliceDebug = new Color((float) 0.8, 0, 0);
			g.setColor(colorPoliceDebug);


			g.drawString("X : " + this.joueur.x + " Y : " + this.joueur.y,  this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+20);
			//g.drawString("estUneCaseInterdite : " + this.estUneCaseInterdite(this.joueur.x, this.joueur.y), this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+60);
			//			g.drawString("coordonneeMurNord : "+this.joueur.getCoordonneeMurNord(),this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+80);
			//			g.drawString("coordonneeMurEst : "+this.joueur.getCoordoneeMurEst(),this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+100);
			//			g.drawString("coordonneeMurSud : "+this.joueur.getCoordoneeMurSud(),this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+120);
			//			g.drawString("coordonneeMurOuest : "+this.joueur.getCoordonneeMurOuest(),this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+140);
			g.drawString("caseAAtteindreNord : "+this.joueur.caseAAtteindreYNord,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+80);
			g.drawString("caseAAtteindreOuest : "+this.joueur.caseAAtteindreXOuest,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+100);
			g.drawString("caseAAtteindreSud : "+this.joueur.caseAAtteindreYSud,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+120);
			g.drawString("caseAAtteindreEst : "+this.joueur.caseAAtteindreXEst,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+140);


			g.drawString("time : "+this.time,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+160);
			g.drawString("uneSecondeEcoulee : "+this.uneSecondeEcoulee,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+180);
			g.drawString("nombreSecondesEcoulees : "+this.nombreSecondeEcoulee,this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+200);
			g.drawString("OrientationPersonnage : "+this.joueur.getOrientationPersonnage(), this.joueur.getCoordonneeMurOuest()+20, this.joueur.getCoordonneeMurNord()+220); 


		}

	}


	@Override
	public void init(GameContainer container) throws SlickException
	{

		this.map = new TiledMap("graphismes/maps/new map/MapCentrale.tmx");

		this.zombie = new Zombie(1056, 1024, Orientation.EST);

		this.joueur = new Joueur(POSITION_SPAWN_JOUEUR_X, POSITION_SPAWN_JOUEUR_Y, Orientation.NORD, "Link");

	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		//delta = 1 milisecondes
		this.time += delta ;
		//TODO Creer une methode qui permet de creer un decalage du nombre de seconde souhaite (decoupe delta en un modulo)
		if(this.time % 3000 == 0)
		{
			this.uneSecondeEcoulee = true ;
			this.nombreSecondeEcoulee++ ;
		}
		else
			this.uneSecondeEcoulee = false ;


		//	
		//		if(this.uneSecondeEcoulee)
		//		{
		//			this.zombie.deplacementAleatoire() ;
		//		}	
		//		
		
		if (this.joueur.estEnMouvement)
		{	
			this.joueur.deplacementDUneCase(delta);
		}
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
		case Input.KEY_ESCAPE:
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new DeplacementState());
			app.setDisplayMode(LARGEUR_MAP, HAUTEUR_MAP, false);
			app.setShowFPS(false);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
