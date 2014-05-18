package fr.iutvalence.projetS2.java.groupe7;

/**
 * @author Lucas
 * 
 */



public enum Carte
{	
	
	CARTE_PRINCIPALE("graphismes/maps/new map/MapCentrale.tmx",832,1152,1248,576);


	private String cheminFichierMap ;
	public int coordonneeMurNord ;
	public int coordoneeMurEst ;
	public int coordoneeMurSud ;
	public int coordonneeMurOuest ;

	Carte(String cheminFichierMap, int coordonneeMurNord, int coordoneeMurEst, int coordoneeMurSud, int coordonneeMurOuest) 
	{
		this.cheminFichierMap = cheminFichierMap ;
		this.coordonneeMurNord = coordonneeMurNord ;
		this.coordoneeMurEst = coordoneeMurEst ;
		this.coordoneeMurSud = coordoneeMurSud ;
		this.coordonneeMurOuest = coordonneeMurOuest ;
	}

	public String getCheminFichierMap()
	{
		return cheminFichierMap;
	}
	

}

