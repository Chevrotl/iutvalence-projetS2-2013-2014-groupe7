package fr.iutvalence.projetS2.java.groupe7;

/**
 * Classe codant les interupteurs
 * @author chevrotl
 *
 */
public class Interrupteur
{
	/**
	 * Position l'interupteur sur la carte
	 */
	private Position position ;
	
	/**
	 * Etat de l'interupteur, par defaut non active
	 */
	private boolean estActive = false ;
	
	/**
	 * Orientation de l'interupteur, pour l'utiliser que en face
	 */
	private Orientation orientation ;

	
	/**
	 * Creer un interupteur d'une position et d'une orientation donnee
	 * 
	 * @param position
	 * @param orientation
	 */
	public Interrupteur(Position position, Orientation orientation)
	{	
		
		// Erreur si la position n'est pas dans la carte 
		
		this.position = position;
		this.orientation = orientation;
	}


	/**
	 * Accesseur en lecture de position
	 * @return position
	 */
	public Position obtenirPosition()
	{
		return this.position;
	}


	/**
	 * Accesseur en lecture du booleen estActive
	 * @return estActive
	 */
	public boolean isEstActive()
	{
		return this.estActive;
	}


	/**
	 * Accesseur en lecture d'orientation
	 * @return orientation
	 */
	public Orientation obtenirOrientation()
	{
		return this.orientation;
	}

}
