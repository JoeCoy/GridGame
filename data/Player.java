package data;

import org.lwjgl.input.Mouse;

import static helpers.Artist.*;

public class Player {
	private TileGrid grid;
	private Character currentSelection = null;
	
	public Player(TileGrid grid)
	{
		this.grid = grid;
	}
	
	public void SetTile()
	{
		int x = (int)Math.floor(Mouse.getX()/64);
		int y = (int)Math.floor((HEIGHT - Mouse.getY() - 1)/64);
		if (grid.getTile(x, y).isOccupied())
		{
			currentSelection = grid.getTile(x, y).getCharacterAtTile();
			//grid.setTile(x, y, TileType.Wall);
		}
	}
	
	public void Update()
	{
		if (Mouse.isButtonDown(0)) //left button
		{
			SetTile();
		}
	}
	
	public Character getCurrentSelection()
	{
		return currentSelection;
	}
	
}
