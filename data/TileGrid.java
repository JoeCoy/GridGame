package data;

public class TileGrid {
	public Tile[][] map;
	public static final int WIDTH = 20, HEIGHT = 15;
	
	
	public TileGrid()
	{
		map = new Tile[WIDTH][HEIGHT];
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Floor);
			}
		}
	}
	
	public TileGrid(int[][] newMap)
	{
		map = new Tile[WIDTH][HEIGHT];
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				switch (newMap[j][i])
				{
				case 0:
					map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Floor);
					break;
				case 1:
					map[i][j] = new Tile(i*64, j*64, 64, 64, TileType.Wall);
					break;
				}
			}
		}
	}

	public void setTile(int xCoord, int yCoord, TileType type)
	{
		int x = xCoord * 64;
		int y = yCoord * 64;
		if (x > 0 && y > 0 && xCoord < WIDTH && yCoord < HEIGHT)
			map[xCoord][yCoord] = new Tile(x, y, 64, 64, type);
		else
			System.out.print("fail");
	}
	
	public void Draw()
	{
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				Tile t = map[i][j];
				t.Draw();
			}
		}
	}

	public Tile getTile(int xCoord, int yCoord) {
		
		return map[xCoord][yCoord];
	}
	
	public Tile getTile(float xCoord, float yCoord)
	{
		int x = (int) (xCoord/64);
		int y = (int) (yCoord/64);
		
		if ((0 > x || x > WIDTH) || (0 > y || y > HEIGHT))
		{
			return null;
		}
		else
		{
			return map[x][y];
		}
		
	}
	
	public boolean isValidMove(float xCoord, float yCoord)
	{
		int x = (int) (xCoord/64);
		int y = (int) (yCoord/64);
		//System.out.print("\nx: " + x + "\ny: " + y);
		if (x < 0 || y < 0 || x > WIDTH-1 || y > HEIGHT-1)
		{
			return false;
		}
		
		else if (this.getTile(x, y).isOccupied())
		{
			return false;
		}
		else if (!this.getTile(x, y).getType().walkable)
		{
			return false;
		}
		else
			return true;
		
	}
}