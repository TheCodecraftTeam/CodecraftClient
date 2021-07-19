package codecraft.ui.items;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;

import org.lwjgl.opengl.GL11;

import codecraft.ui.Item;

public class ItemGrassBlock extends Item {

	@Override
	public void action() {
	
		
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
