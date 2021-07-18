package codecraft.world;

import java.lang.reflect.InvocationTargetException;

import org.lwjgl.opengl.GL11;

import codecraft.player.Player;
import codecraft.renderEngine.Texture;
import codecraft.renderEngine.WindowUtils;
import codecraft.renderEngine.WindowVariables;
import codecraft.renderEngine.drawList;
import codecraft.world.blocks.BlockGrass;

public class World {
public static Chunk chunks[][] = null;
public static int displayListIndex = GL11.glGenLists(1);
private static int oldDisplayListIndex;
private static int chunkNumbers[][] = new int[32][32];
private static Texture textures = new Texture();
public static int i = 0;
static {
textures.init("Textures/Textures.bmp");

}
public static void loadChunks(Chunk chunks[][]) {
	World.chunks =  chunks;
	
	

	
	
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
    			 
    			 int x = 16;
    			 int z = -15;
    			 i = 0;
    			 while(true) {
    				 GL11.glNewList(displayListIndex +i, GL11.GL_COMPILE);
    				 
    				 try { 
    					 chunks[playerChunkX +x][playerChunkZ +z].DrawChunk();
    					 chunkNumbers[playerChunkX +x][playerChunkZ +z] = i;
    				 } catch(Exception e) {}
    				 i++;
    				 GL11.glEndList();
    				 x++;
    				 if(x > 16) {
    					 x = -16;
    					 z++;
    				 }
    				 if(z > 16){
    					 break;
    				 }
    			 }
    			 System.out.println(i);
	
	
}
public static void drawWorld() {
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	//WindowUtils.showBlockWherePlayerIsLookin();
	for(int i = 0; i <=World.i; i++) {
	GL11.glCallList(displayListIndex + i);
	}
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
	 if(blockType == null) {
		 try {
		 chunks[chunkX][chunkZ].blocks[x - (chunkX * 16)][y][z - (chunkZ * 16)] = null;
		 }catch(Exception e) {
			 
		 }
		 }else {
	 try {
	 chunks[chunkX][chunkZ].blocks[x - (chunkX * 16)][y][z - (chunkZ * 16)] = (Block) blockType.getConstructors()[0].newInstance(x -(chunkX * 16),y, z - (chunkZ * 16), chunkX, chunkZ);
	 }catch(Exception e) {
		 
	 }
	 }
}
public static int ChunkPositonToChunkNumber(int x,int z) {
	return chunkNumbers[x][z];
}
}
