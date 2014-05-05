package fr.iutvalence.projetS2.java.groupe7;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class TestSlick extends BasicGame
{

	public TestSlick()
	{
		super("TestSlick");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		// TODO Auto-generated method stub
		g.drawString("Slick marche, c'est un miracle ! Maintenant il reste plus qu'a bosser", 0, 100);
		
		
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		// TODO Auto-generated method stub
		
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
