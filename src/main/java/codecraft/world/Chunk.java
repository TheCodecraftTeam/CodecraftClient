package codecraft.world;

import codecraft.player.Player;

public class Chunk {
public Block blocks[][][];
private float chunkX;
private float chunkZ;
public Chunk(float chunkX, float chunkZ, Block blocks[][][]) {
	this.chunkX = chunkX;
	this.chunkZ = chunkZ;
	this.blocks = blocks;
}
public void DrawChunk() {
	for(Block[][] B : blocks) {
		for(Block[] b : B) {
			for(Block block : b) {
				if(block != null) {
				
				Block sideBlock1 = null;
				Block sideBlock2 = null;
				Block forwardBlock = null;
				Block backBlock = null;
				Block upBlock = null;
				Block downBlock = null;
					
				try {	
				 sideBlock1 = World.getBlockAtPos((int)block.getGlobalX()+1, (int)block.getGlobalY(), (int)block.getGlobalZ());
				 sideBlock2 = World.getBlockAtPos((int)block.getGlobalX()-1, (int)block.getGlobalY(), (int)block.getGlobalZ());
				 forwardBlock = World.getBlockAtPos((int)block.getGlobalX(), (int)block.getGlobalY(), (int)block.getGlobalZ()+1);
				 backBlock = World.getBlockAtPos((int)block.getGlobalX(), (int)block.getGlobalY(), (int)block.getGlobalZ()-1);
				 upBlock = World.getBlockAtPos((int)block.getGlobalX(), (int)block.getGlobalY()+1, (int)block.getGlobalZ());
				 downBlock = World.getBlockAtPos((int)block.getGlobalX(), (int)block.getGlobalY()-1, (int)block.getGlobalZ());
				}catch(Exception e) {
					
				}
				 if(sideBlock1 == null || sideBlock2 == null || forwardBlock == null || backBlock == null || upBlock == null || downBlock == null) {
					 block.Draw();
				 }
				}
				}
			}
		}
	
}
public Block[][][] getBlocks() {
	return blocks;
}
public float getChunkX() {
	return chunkX;
}
public float getChunkZ() {
	return chunkZ;
}

}
