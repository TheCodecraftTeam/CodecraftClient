package codecraft.ui.items;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;

import java.lang.reflect.InvocationTargetException;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import codecraft.math.MathUtills;
import codecraft.player.Player;
import codecraft.ui.Item;
import codecraft.world.Block;
import codecraft.world.World;
import codecraft.world.blocks.BlockGrass;
import codecraft.world.blocks.BlockPlank;

public class ItemGrassBlock extends Item {

	@Override
	public void action() {
	Vector3f blockPosition = MathUtills.PlayerPositionToBlockPosition(-Player.posX, -Player.posY, -Player.posZ);
	try {
		World.SetBlockAtPosition((int)blockPosition.x,(int)blockPosition.y + (int)Player.placeBlockOffsetY, (int)blockPosition.z, BlockGrass.class);
	} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
			| SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Player.posY-=2;
	int chunkXpos = (int)blockPosition.x;
	 int chunkX;
	 while(true) {
		 if(chunkXpos % 16 == 0) {
			 break;
		 }
		chunkXpos--;
	 }
	 chunkX = chunkXpos/16;
	 
	 
	 int chunkZpos = (int)blockPosition.z;
	 int chunkZ;
	 while(true) {
		 if(chunkZpos % 16 == 0) {
			 break;
		 }
		chunkZpos--;
	 }
	 chunkZ = chunkZpos/16;
	 int i;
	 try {
	 i = World.ChunkPositonToChunkNumber(chunkX, chunkZ);
	 GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
	 World.chunks[chunkX][chunkZ].DrawChunk();
	 GL11.glEndList();
	 }catch(Exception e) {
		 
	 }
	}

	@Override
	public void draw(float x, float y, float z, float size) {
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +  +z);	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Left Of The Texture and Quad
		// Back Face
		
		
		
		
		
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x,   (0.5f*size) + y , -(0.5f*size) +z);	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Left Of The Texture and Quad
		// Top Face
		GL11.glEnd();
		
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x,(0.5f*size) + y ,  (0.5f*size)  +z);	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z) ;	// Top Right Of The Texture and Quad
		// Bottom Face
		
		GL11.glEnd();
		
		glBegin(GL_QUADS);
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size)+z);	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		// Right face
	
		
		
		
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Left Of The Texture and Quad
		// Left Face
		
		

		
		
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y  , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glEnd();
		
		
	}

}
