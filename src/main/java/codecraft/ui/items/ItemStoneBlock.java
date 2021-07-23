package codecraft.ui.items;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;

import java.lang.reflect.InvocationTargetException;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import codecraft.math.MathUtills;
import codecraft.player.Player;
import codecraft.ui.Item;
import codecraft.ui.ItemType;
import codecraft.world.Block;
import codecraft.world.World;
import codecraft.world.blocks.BlockDirt;
import codecraft.world.blocks.BlockGrass;
import codecraft.world.blocks.BlockPlank;
import codecraft.world.blocks.BlockStone;
import codecraft.world.lighting.LightingController;

public class ItemStoneBlock extends Item {
	public ItemStoneBlock() {
		itemType = ItemType.Placeable;
	}
	@Override
	public void action() {
	Vector3f blockPosition = Player.blockSelected;
	if(blockPosition == null) {
		return;
	}
	try {
		World.SetBlockAtPosition((int)blockPosition.x, (int)blockPosition.y,(int)blockPosition.z, BlockStone.class);
	} catch (InstantiationException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalAccessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvocationTargetException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SecurityException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
	 GL11.glColor4f(1*LightingController.worldLighting+0.2f,1*LightingController.worldLighting+0.3f,1*LightingController.worldLighting+0.3f,0.5f);
	 World.chunks[chunkX][chunkZ].DrawChunk();
	 GL11.glEndList();
	 }catch(Exception e) {
		 
	 }
	}

	@Override
	public void draw(float x, float y, float z, float size) {
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +  +z);	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Left Of The Texture and Quad
		// Back Face
		
		
		
		
		
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x,   (0.5f*size) + y , -(0.5f*size) +z);	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Left Of The Texture and Quad
		// Top Face
		GL11.glEnd();
		
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x,(0.5f*size) + y ,  (0.5f*size)  +z);	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z) ;	// Top Right Of The Texture and Quad
		// Bottom Face
		
		GL11.glEnd();
		
		glBegin(GL_QUADS);
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size)+z);	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
		// Right face
	
		
		
		
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y , -(0.5f*size) +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( (0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f( (0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Left Of The Texture and Quad
		// Left Face
		
		

		
		
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y , -(0.5f*size) +z);	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f(-(0.5f*size) + x, -(0.5f*size) +y ,  (0.5f*size) +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y ,  (0.5f*size) +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-(0.5f*size) + x, (0.5f*size) +y  , -(0.5f*size) +z);	// Top Left Of The Texture and Quad
		GL11.glEnd();
		
		
	}

}
