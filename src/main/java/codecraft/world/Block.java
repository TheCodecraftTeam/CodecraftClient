package codecraft.world;

public abstract class Block {
	float x; 
	float y; 
	float z; 
	float chunkX;
	float chunkZ;
	public Block(float x, float y, float z, float chunkX, float chunkZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public float getGlobalX() {
		return x + (chunkX * 16);
	}
	public float getGlobalY() {
		return y;
	}
	public float getGlobalZ() {
		return z + (chunkZ * 16);
	}
	public float getChunkX() {
		return chunkX;
	}
	public float getChunkZ() {
		return chunkZ;
	}
	public abstract void Draw();

}
