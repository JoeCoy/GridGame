package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import static helpers.Artist.*;

public class Character {
	private int width, height, hp, attack, defense, damage;
	private String direction = null;
	float x, y, destX, destY;
	Texture texture;
	
	public Character(Texture texture, Tile startTile, int width, int height, float speed)
	{
		startTile.setOccupied(true);
		startTile.setCharacterAtTile(this);
		this.texture = texture;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.destX = x;
		this.destY = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void Update()
	{
			if (direction == null)
			{
				
			}
			//else if (Math.floor(x) == Math.floor(destX) && Math.floor(y) == Math.floor(destY))
			else if ((x < destX+5 && x > destX-5) && (y < destY+5 && y > destY-5))
			{
				x = destX;
				y = destY;
				direction = null;
			}
			else
			{
				String dir = direction;
				if (dir.equals("up"))
				{
					y -= Delta();
				}
				else if (dir.equals("down"))
				{
					y += Delta();
				}
				else if (dir.equals("left"))
				{
					x -= Delta();
				}
				else if (dir.equals("right"))
				{
					x += Delta();
				}
			}
	}
	
	public void Draw() 
	{
		DrawQuadTex(texture, x, y, width, height);
	}
	
	public void move(TileGrid t, String dir)
	{
		if (direction != null)
		{
			//do nothing
		}
		else if (dir.equals("up"))
		{
			if (t.isValidMove(x, y-64))
			{
				this.move(t.getTile(x, y-64), t);
				this.direction = dir;
			}
		}
		else if (dir.equals("down"))
		{
			if (t.isValidMove(x, y+64))
			{
				this.move(t.getTile(x, y+64), t);
				this.direction = dir;
			}
		}
		else if (dir.equals("left"))
		{
			if (t.isValidMove(x-64, y))
			{
				this.move(t.getTile(x-64, y), t);
				this.direction = dir;
			}
		}
		else if (dir.equals("right"))
		{
			if (t.isValidMove(x+64, y))
			{
				this.move(t.getTile(x+64, y), t);
				this.direction = dir;
			}
		}
	}
	
	//assumes that move is valid
	public void move(Tile tile, TileGrid t)
	{
		this.getTile(t).setOccupied(false);
		this.getTile(t).setCharacterAtTile(null);
		this.destX = tile.getX();
		this.destY = tile.getY();
		tile.setOccupied(true);
		tile.setCharacterAtTile(this);
	}
	
	public Tile getTile(TileGrid t)
	{
		return t.getTile(x, y);
	}
	
}
