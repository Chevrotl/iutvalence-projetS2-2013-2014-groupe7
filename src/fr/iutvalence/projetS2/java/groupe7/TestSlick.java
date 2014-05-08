package fr.iutvalence.projetS2.java.groupe7;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;


public class TestSlick extends BasicGame
{


	TiledMap map ;

	public TestSlick()
	{
		super("TestSlick");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		// TODO Auto-generated method stub
		this.map.render(0, 0);
		
		
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		// TODO Auto-generated method stub
		
		this.map  = new TiledMap("graphismes/maps/MapCentrale.tmx");
		

		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		try
		{
			AppGameContainer app = new AppGameContainer(new TestSlick()) ;
			app.start();
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	

}
