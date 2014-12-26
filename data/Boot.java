package data;

import java.awt.BorderLayout;

import java.awt.Button;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;

import helpers.Clock;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
public class Boot {
	
	public static final int WIDTH = 600, HEIGHT = 400;
	TileGrid grid;
	Menu menu;
	
	public Boot()
	{
		BeginSession();
		int[][] map = 
			{
				{0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
				{1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1,},
			};
		
		grid = new TileGrid(map);
		grid.setTile(3, 3, grid.getTile(4,3).getType());
		Character e = new Character(QuickLoad("character"), grid.getTile(10, 10), 64, 64, 6);
		Character d = new Character(QuickLoad("character"), grid.getTile(1, 1), 64, 64, 6);
		Player player1 = new Player(grid);
		Player player2 = new Player(grid);
		
		player1.addCharacter(e);
		player1.addCharacter(d);
		
		grid.characters.add(d);
		grid.characters.add(e);
		
		menu = new Menu("Menu", player1);
		menu.setSize(250, 100);
		menu.setVisible(true);
		
		while(!Display.isCloseRequested())
		{
			
			this.Update();
			
		}
		
		Display.destroy();
		
	}
	
	public void Update()
	{
		Clock.update();
		grid.Draw();
		if (menu.getActivePlayer() != null)
		{
			if (menu.getActivePlayer().getCurrentSelection() != null)
			{
				Character current = menu.getActivePlayer().getCurrentSelection();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
					current.move(grid, "left");
				else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
					current.move(grid, "right");
				else if (Keyboard.isKeyDown(Keyboard.KEY_UP))
					current.move(grid, "up");
				else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
					current.move(grid, "down");
			}
			menu.getActivePlayer().Update();
		}
		
		Display.update();
		Display.sync(60);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		new Boot();
	}
}
