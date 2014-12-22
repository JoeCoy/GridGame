package data;

public enum TileType {
	
	Floor("floor", true), Wall("wall", false);
	
	String textureName;
	boolean walkable;
	
	TileType(String textureName, boolean buildable)
	{
		this.textureName = textureName;
		this.walkable = buildable;
	}
}
