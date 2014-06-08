package fr.iutvalence.projetS2.java.groupe7;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author Lucas
 * 
 */
public class ViewController extends StateBasedGame {

	public static final int DEPLACEMENTSTATE = 0;
	public static final int COMBATSTATE = 1;


	public ViewController() {
		super("Jeux");
	}

	@Override
	public void initStatesList(GameContainer gameContainer)
			throws SlickException 
			{

		this.addState((GameState) new DeplacementState());
		//this.addState(new GamePlayState(COMBATSTATE));
			}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new ViewController());
			app.setDisplayMode(DeplacementState.LARGEUR_MAP, DeplacementState.HAUTEUR_MAP, false);
			app.setShowFPS(false);
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
}