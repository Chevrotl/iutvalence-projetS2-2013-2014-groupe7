package fr.iutvalence.projetS2.java.groupe7;

import java.util.Random;

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
	 * position du personnage
	 */
	private Position positionPersonnage ;

	/**
	 * constructeur general, ou l'on attribut seulement une orientaion et une position au personnage crée
	 * @param orientation
	 * @param position
	 */
	public Personnage(Position position, Orientation orientation)
	{
		super();
		this.orientationPersonnage = orientation;
		this.positionPersonnage = position;
		this.vie = VIE_PAR_DEFAUT ;
		this.soinsEnStock = SOIN_EN_STOCK_PAR_DEFAUT ;
		this.atckSpecialeEnStock = ATCK_SPECIALE_EN_STOCK_PAR_DEFAUT ; 
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
		return this.positionPersonnage;
	}

	/**
	 * accesseur en ecriture de l'attribut de la position du personnage
	 * @param positionPersonnage position future du personnage
	 */
	public void setPositionPersonnage(Position positionPersonnage)
	{
		this.positionPersonnage = positionPersonnage;
	}
	
	
	public void attaquer(Personnage personnageAttaque, int degatsMaxi)
	{
		Random nombreAleatoire = null ;
		
		int degatsDeLattaque = nombreAleatoire.nextInt(degatsMaxi) ;
		 personnageAttaque.setVie((getVie()-degatsDeLattaque)) ;
		
	}
	

	
	
	
	
}
