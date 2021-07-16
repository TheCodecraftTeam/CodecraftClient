package codecraft.world;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.opengl.GL11;

import codecraft.player.Player;
import codecraft.renderEngine.Texture;
import codecraft.renderEngine.WindowVariables;
import codecraft.renderEngine.drawList;
import codecraft.world.blocks.BlockGrass;

public class World {
private static Chunk chunks[][] = null;
private static int displayListIndex;
private static int oldDisplayListIndex;
private static Texture textures = new Texture();

static {
textures.init("Textures/Textures.bmp");

}
public static void loadChunks(Chunk chunks[][]) {
	World.chunks =  chunks;
	oldDisplayListIndex= displayListIndex;
	displayListIndex = GL11.glGenLists(1);

	
	GL11.glNewList(displayListIndex, GL11.GL_COMPILE);
	textures.bind();
    
    		
    		
    			
    			 int playerChunkXpos = (int) -Player.getPosX();
    			 int playerChunkX;
    			 while(true) {
    				 if(playerChunkXpos % 16 == 0) {
    					 break;
    				 }
    				playerChunkXpos--;
    			 }
    			 playerChunkX = playerChunkXpos/16;
    			 
    			 
    			 int playerChunkZpos = (int) -Player.getPosZ();
    			 int playerChunkZ;
    			 while(true) {
    				 if(playerChunkZpos % 16 == 0) {
    					 break;
    				 }
    				playerChunkZpos--;
    			 }
    			 playerChunkZ = playerChunkZpos/16;
    			 
    			 int x = -3;
    			 int z = -3;
    			 while(true) {
    				 try { chunks[playerChunkX +x][playerChunkZ +z].DrawChunk();} catch(Exception e) {}
    				 x++;
    				 if(x > 3) {
    					 x = -3;
    					 z++;
    				 }
    				 if(z > 3) {
    					 break;
    				 }
    			 }
	
	GL11.glEndList();
	GL11.glDeleteLists(oldDisplayListIndex, 1);
}
public static void drawWorld() {
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	GL11.glCallList(displayListIndex);
	GL11.glDisable(GL11.GL_TEXTURE_2D);
}
public static Block getBlockAtPos(int x, int y, int z) {
	 int chunkXpos = x;
	 int chunkX;
	 while(true) {
		 if(chunkXpos % 16 == 0) {
			 break;
		 }
		chunkXpos--;
	 }
	 chunkX = chunkXpos/16;
	 
	 
	 int chunkZpos = z;
	 int chunkZ;
	 while(true) {
		 if(chunkZpos % 16 == 0) {
			 break;
		 }
		chunkZpos--;
	 }
	 chunkZ = chunkZpos/16;
	 
	 
	 return chunks[chunkX][chunkZ].getBlocks()[x - (chunkX * 16)][y][z - (chunkZ * 16)];
	 
}
public static void SetBlockAtPosition(int x, int y, int z,Class blockType) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException {
	int chunkXpos = x;
	 int chunkX;
	 while(true) {
		 if(chunkXpos % 16 == 0) {
			 break;
		 }
		chunkXpos--;
	 }
	 chunkX = chunkXpos/16;
	 
	 
	 int chunkZpos = z;
	 int chunkZ;
	 while(true) {
		 if(chunkZpos % 16 == 0) {
			 break;
		 }
		chunkZpos--;
	 }
	 chunkZ = chunkZpos/16;
	 
	 try {
	 chunks[chunkX][chunkZ].blocks[x - (chunkX * 16)][y][z - (chunkZ * 16)] = (Block) blockType.getConstructors()[0].newInstance(x -(chunkX * 16),y, z - (chunkZ * 16), chunkX, chunkZ);
	 }catch(Exception e) {
		 
	 }
}
}
