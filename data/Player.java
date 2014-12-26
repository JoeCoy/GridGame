package data;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import static helpers.Artist.*;

public class Player {
	private TileGrid grid;
	private Character currentSelection = null;
	private ArrayList<Character> characters;
	private boolean isActive;
	public int numActivations = 0;
	private boolean isReadyToSelect = false;
	
	public Player(TileGrid grid)
	{
		isActive = false;
		this.grid = grid;
		characters = new ArrayList<Character>();
		numActivations = 0;
	}
	
	public TileGrid getTileGrid()
	{
		return grid;
	}
	
	public void setReadyToSelect(boolean ready)
	{
		isReadyToSelect = ready;
	}
	
	public boolean isReadyToSelect()
	{
		return isReadyToSelect;
	}
	
	public void takeTurn()
	{
		int count = 0;
		for (Character c: characters)
		{
			if (!c.isActivated())
			{
				count++;
			}
		}
		
		numActivations = Math.min(count, 2);
		if (numActivations != 0)
			isActive = true;
	}
	
	public boolean activateCharacter(Character c)
	{
		if (characters.contains(c))
		{
			c.activate();
			currentSelection = c;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isActivePlayer()
	{
		return isActive;
	}
	
	public void addCharacter(Character c)
	{
		characters.add(c);
		c.player = this;
	}
	
	public void SetCurrent()
	{
		if (!isTurnOver())
		{
			if(isReadyToSelect())
			{
				int x = (int)Math.floor(Mouse.getX()/64);
				int y = (int)Math.floor((HEIGHT - Mouse.getY() - 1)/64);
				if (grid.getTile(x, y).isOccupied())
				{
					Character c = grid.getTile(x, y).getCharacterAtTile();
					if (!c.isActivated())
					{
						if (currentSelection != null)
							currentSelection.deactivate();
						
						currentSelection = c;
						c.activate();
						this.setReadyToSelect(false);
					}
					//grid.setTile(x, y, TileType.Wall);
				}
			}
		}
	}
	
	public void deselect()
	{
		this.currentSelection = null;
	}
	
	public void Update()
	{
		if (Mouse.isButtonDown(0)) //left button
		{
			SetCurrent();
		}
	}
	
	public Character getCurrentSelection()
	{
		return currentSelection;
	}
	
	public boolean isTurnOver()
	{
		return (numActivations == 0);
	}
	
	public void endTurn()
	{
		isActive = false;
		currentSelection = null;
	}
	
}
