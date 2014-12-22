package data;

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
		
		TileGrid grid = new TileGrid(map);
		grid.setTile(3, 3, grid.getTile(4,3).getType());
		Character e = new Character(QuickLoad("character"), grid.getTile(10, 10), 64, 64, 2);
		Character d = new Character(QuickLoad("character"), grid.getTile(1, 1), 64, 64, 2);
		Player player = new Player(grid);
		while(!Display.isCloseRequested())
		{
			Clock.update();
			
			grid.Draw();
			e.Draw();
			e.Update();
			d.Draw();
			d.Update();
			if (player.getCurrentSelection() != null)
			{
				Character current = player.getCurrentSelection();
				//player.getCurrentSelection().Update();
				//player.getCurrentSelection().Draw();
				
				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
				{
					current.move(grid, "left");
				}
				else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
				{
					current.move(grid, "right");
				}
				else if (Keyboard.isKeyDown(Keyboard.KEY_UP))
				{
					current.move(grid, "up");
				}
				else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
				{
					current.move(grid, "down");
				}
				
			}
			player.Update();
			
			Display.update();
			Display.sync(60);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		Display.destroy();
		
	}
	
	public static void main(String[] args)
	{
		new Boot();
	}
}
