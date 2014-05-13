package fr.iutvalence.projetS2.java.groupe7;

import java.util.Random;

import org.newdawn.slick.Animation;

/**
 * classe représentant un personnage (zombie comme humain)
 * @author Lucas
 *
 */
public class Personnage 
{

	/**
	 * vie par defaut
	 */
	private static final int VIE_PAR_DEFAUT = 100 ;
	
	/**
	 * Nombre d'attaque speciale par defaut en stock
	 */
	private static final int ATCK_SPECIALE_EN_STOCK_PAR_DEFAUT = 1 ;
	
	/**
	 * nombre de soin par defaut en stock
	 */
	private static final int SOIN_EN_STOCK_PAR_DEFAUT = 1 ;
	
	/**
	 * Vie du personnage
	 */
	private int vie ;
	
	/**
	 * nombre d'attaque speciale disponible
	 */
	private int atckSpecialeEnStock ;
	
	/**
	 * nombre de soin disponible
	 */
	private int soinsEnStock ;
	
	/**
	 * orientation du personage
	 */
	private Orientation orientationPersonnage ;
	

	
	/**
	 * Booleen pour savoir si le personnage est en mouvement ou non / partie graphique
	 */
	public boolean estEnMouvement = false  ;
	
	/**
	 * l'animation d'un sprited'un personnage
	 */
	protected Animation[] animationSprite = new Animation[8];
	

	/** position x du personnage */
	protected float x ;
	
	/** position y du personnage */
	protected float y ;
	
	//utile pour le deplacement case par case, = aux coordonnée de pop du joueur
		protected float caseAAtteindreXEst  ;
		protected float caseAAtteindreXOuest  ;
		protected  float caseAAtteindreYNord  ;
		protected float caseAAtteindreYSud  ; 

		//lors du premier deplacement sur la carte, les coordonnée du deplacement ne se modifient pas de la meme maniere
		//on est oblige de separer le premier deplacement des autres
		protected boolean aDejaBougeX = false;
		protected boolean aDejaBougeY = false;
		
		
		/**
	 * constructeur general, ou l'on attribut seulement une orientaion et une position au personnage crée
	 * @param x position X
	 * @param y position Y
	 * @param orientation orientation du personnage lors de sa creation
	 */
	public Personnage(float x, float y, Orientation orientation)
	{
		super();
		this.orientationPersonnage = orientation;
		this.x = x ;
		this.y = y;
		this.caseAAtteindreXEst = x;
		this.caseAAtteindreXOuest = x;
		this.caseAAtteindreYNord = y;
		this.caseAAtteindreYSud = y ;
		this.vie = VIE_PAR_DEFAUT ;
		this.soinsEnStock = SOIN_EN_STOCK_PAR_DEFAUT ;
		this.atckSpecialeEnStock = ATCK_SPECIALE_EN_STOCK_PAR_DEFAUT ; 
		this.estEnMouvement = false ;
	}

	/**
	 * accesseur en lecture vie
	 * @return vie
	 */
	public int getVie()
	{
		return this.vie;
	}

	/**
	 * accesseur en ecriture de l'attribut  vie 
	 * @param vie vie future du personnage
	 */
	public void setVie(int vie)
	{
		this.vie = vie;
	}

	/**
	 * accesseur en lecture du nombre d'attaque speciale disponible
	 * @return atckSpecialeEnStock
	 */
	public int getAtckSpecialeEnStock()
	{
		return this.atckSpecialeEnStock;
	}

	/**
	 * accesseur en ecriture de l'attribut nombre d'attaque speciale en stock
	 * @param atckSpecialeEnStock nb futur d'attaque speciale du personnage
	 */
	public void setAtckSpecialeEnStock(int atckSpecialeEnStock)
	{
		this.atckSpecialeEnStock = atckSpecialeEnStock;
	}

	/**
	 * accesseur en lecture du nombre de soin disponible 
	 * @return soinEnStock nb de soin du personnage
	 */
	public int getSoinsEnStock()
	{
		return this.soinsEnStock;
	}

	/**
	 * accesseur en ecriture de l'attribut du nombre de soin disponible
	 * @param soinsEnStock nb futur de soin du personnage
	 */
	public void setSoinsEnStock(int soinsEnStock)
	{
		this.soinsEnStock = soinsEnStock;
	}

	/**
	 * accesseur en lecture de l'orientation du personnage
	 * @return orientationPersonnage orientation actuelle du personnage
	 */
	public Orientation getOrientationPersonnage()
	{
		return this.orientationPersonnage;
	}

	/**
	 * accesseur en ecriture de l'attribut de l'orienation du personnage
	 * @param orientationPersonnage orientation vers laquelle le personnage se deplace
	 */
	public void setOrientationPersonnage(Orientation orientationPersonnage)
	{
		this.orientationPersonnage = orientationPersonnage;
	}

	/**
	 * accesseur en lecture de la position du personnage
	 * @return positionPersonnage position actuelle du personnage
	 */
	public Position getPositionPersonnage()
	{
		return new Position(this.x,this.y);
	}

	/**
	 * accesseur en lecture position x du personnage
	 * @return x (position x du personnage)
	 */
	public float getPositionPersonnageX()
	{
		return this.x ;
	}
	
	/**
	 * accesseur en lecture position y du personnage
	 * @return y (position y du personnage)
	 */
	public float getPositionPersonnageY()
	{
		return this.y ;
	}
	
	
	/**
	 * accesseur en ecriture de l'attribut de la position du personnage
	 * @param positionPersonnage position future du personnage
	 */
	public void setPositionPersonnage(Position positionPersonnage)
	{
		
		this.x = positionPersonnage.X ;
		this.y = positionPersonnage.Y ;
	}
	
	/**
	 * accesseur en ecriture de la position X du personnage
	 * @param x position.X
	 */
	public void setPositionPersonnageX(float x)
	{
		this.x = x;
	}
	
	/**
	 * accesseur en ecriture de la position X du personnage
	 * @param y position.Y
	 */
	public void setPositionPersonnageY(float y)
	{
		this.y = y ;
	}
	
/**
 * accesseur en lecture du tableau d'animation du sprite
 * @return animationSprite
 */
	public Animation[] getAnimationSprite()
	{
		return this.animationSprite;
	}

	/**
	 * deplace aleatoirement soit Y, soit X
	 * @return une position avec juste des coordonnée d'une case différente
	 */
	public Position deplacementAleatoire()
	{ 
		Position positionARenvoyer = new Position();
		
		boolean nombreAleatoire1 = new Random().nextBoolean() ;
		boolean nombreAleatoire2 = new Random().nextBoolean() ;
		
		if(nombreAleatoire1)
		{
			if(nombreAleatoire2)
			{
				positionARenvoyer.X += 32 ;
				
			}
			else
			{
				positionARenvoyer.X -= 32 ;
			}
		}
		else
		{
			if(nombreAleatoire2)
			{
				positionARenvoyer.Y += 32 ;
			}
			else
			{
				positionARenvoyer.Y -= 32 ;
			}
		}
		return positionARenvoyer ;
		
		
	}

	
	public void  deplacementDUneCase(float futurePositionX, float futurePositionY)
	{
		switch(this.getOrientationPersonnage())
		{
		case NORD: {
			if((int)futurePositionY == (int)this.caseAAtteindreYNord)
			{
				if(this.aDejaBougeY)
				{
					this.estEnMouvement = false ;
					this.caseAAtteindreYNord -= 32;
					this.caseAAtteindreYSud -= 32 ;
					//comme la position est de type float, la valeur n'est pas ronde, cela crée des problemes pour pouvoir se deplacer correctement.
					//on remet la position du personnage a jour pour eviter tout probleme
					this.setPositionPersonnageY(this.caseAAtteindreYSud);


				}
				else
				{
					this.estEnMouvement = false ;
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
					this.estEnMouvement = false ;
					this.caseAAtteindreXEst -= 32;
					this.caseAAtteindreXOuest -= 32;
					this.setPositionPersonnageX(this.caseAAtteindreXEst);
				}
				else
				{
					this.estEnMouvement = false ;
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
					this.estEnMouvement = false ;
					this.caseAAtteindreYNord += 32;
					this.caseAAtteindreYSud += 32 ;
					this.setPositionPersonnageY(this.caseAAtteindreYNord);

				}
				else
				{
					this.estEnMouvement = false ;
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
					this.estEnMouvement = false ;
					this.caseAAtteindreXEst += 32;
					this.caseAAtteindreXOuest += 32;
					this.setPositionPersonnageX(this.caseAAtteindreXOuest);
				}
				else
				{
					this.estEnMouvement = false ;
					this.caseAAtteindreXEst += 32;
					this.aDejaBougeX = true ;
				}

			}
		}break;
		}

	}
		
	
	
	
}
