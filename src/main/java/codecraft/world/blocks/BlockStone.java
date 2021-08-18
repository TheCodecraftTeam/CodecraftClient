package codecraft.world.blocks;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

import org.lwjgl.opengl.GL11;

import codecraft.renderEngine.Texture;
import codecraft.renderEngine.drawList;
import codecraft.world.Block;

public class BlockStone extends Block {
	
	public BlockStone(float x, float y, float z, float chunkX, float chunkZ) {
		super(x, y, z, chunkX, chunkZ);
		this.blockName = "stone";
	}

	@Override
	public void Draw() {
		
		
		
	glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f+ 0.2f,0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +  +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f,0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		// Back Face
		
		
		
		
		
		GL11.glTexCoord2f(0.1f+ 0.2f,0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(),   0.5f + this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f,0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		// Top Face
		GL11.glEnd();
		
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f,0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(),0.5f + this.getGlobalY() ,  0.5f  +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f,0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ()) ;	// Top Right Of The Texture and Quad
		// Bottom Face
		
		GL11.glEnd();
		
		glBegin(GL_QUADS);
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.2f,0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f+this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.2f,0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		// Right face
	
		
		
		
	GL11.glTexCoord2f(0.1f+ 0.2f,0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f,0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		// Left Face
		
		

		
		
	GL11.glTexCoord2f(0.0f+ 0.2f,0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f,0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY()  , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glEnd();
		
		
	}
}

	
