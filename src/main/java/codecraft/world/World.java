package codecraft.world;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import org.lwjgl.opengl.GL11;

import codecraft.player.Player;
import codecraft.renderEngine.Texture;
import codecraft.renderEngine.WindowUtils;
import codecraft.renderEngine.WindowVariables;
import codecraft.renderEngine.drawList;
import codecraft.world.blocks.BlockGrass;

public class World {
public static Chunk chunks[][] = null;
public static int displayListIndex = 0;
private static int oldDisplayListIndex;
public static int rd = 2;
public static int nrd = -rd;
private static int chunkNumbers[][] = new int[32][32];
public static Texture textures = new Texture();
public static int i = 0;
public static int count = 0;

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
    			 
    			 int x = nrd;
    			 int z = nrd;
    			 i = 0;
    			 
    			 while(true) {
    				 int count =0;
    				 GL11.glNewList(displayListIndex +i, GL11.GL_COMPILE);
    				
    				 try { 
    					 
    					 chunks[playerChunkX +x][playerChunkZ +z].DrawChunk();
    					 chunkNumbers[playerChunkX +x][playerChunkZ +z] = i;
    				 } catch(Exception e) {}
    				 count++;
    				 
    				 i++;
    				 GL11.glEndList();
    				 x++;
    				 if(x > rd) {
    					 x = nrd;
    					 z++;
    				 }
    				 if(z > rd){
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
public static void saveWorldtoFile() throws IOException {
	File cp = new File("/saves/world/chunks/");
	File worldFile = new File("/saves/world/world.codecraftWorld");
	int n = 0;
	worldFile.createNewFile();
	cp.mkdirs();
	String worldSave = "";
	FileWriter cfr = new FileWriter(worldFile);
for(Chunk[] Chunks : chunks) {

	for(Chunk chunk : Chunks) {
		System.out.println(++n);
		float x =0;
		float y = 0;
		float z =0;
		
		for(int i = 0; i < 16 *256 *16; i++) {
		Block block = chunk.blocks[(int) x][(int) y][(int) z];
		
	
					if(block != null) {
					cfr.append((int)chunk.getChunkX() + "." + (int)chunk.getChunkZ() + "." + block.getClass().getSimpleName() + "." + (int)block.x+ "." + (int)block.y +  "." + (int)block.z + "\n");
					}
					y++;
					if(y > 255) {
						y =0;
						x++;
					}
					if(x > 15) {
						x = 0;
						z++;
					}
					
}
}
}


cfr.close();
}

}
