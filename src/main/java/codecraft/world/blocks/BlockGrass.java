package codecraft.world.blocks;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

import org.lwjgl.opengl.GL11;

import codecraft.renderEngine.Texture;
import codecraft.renderEngine.drawList;
import codecraft.world.Block;
import codecraft.world.lighting.LightingController;

public class BlockGrass extends Block {
	

		// index array for glDrawElements()
		// A cube requires 36 indices = 6 sides * 2 tris * 3 verts
		
		public static float vertices[]  = {
			     .5f, .5f, .5f,  -.5f, .5f, .5f,  -.5f,-.5f, .5f,  .5f,-.5f, .5f, // v0,v1,v2,v3 (front)
			     .5f, .5f, .5f,   .5f,-.5f, .5f,   .5f,-.5f,-.5f,  .5f, .5f,-.5f, // v0,v3,v4,v5 (right)
			     .5f, .5f, .5f,   .5f, .5f,-.5f,  -.5f, .5f,-.5f, -.5f, .5f, .5f, // v0,v5,v6,v1 (top)
			    -.5f, .5f, .5f,  -.5f, .5f,-.5f,  -.5f,-.5f,-.5f, -.5f,-.5f, .5f, // v1,v6,v7,v2 (left)
			    -.5f,-.5f,-.5f,   .5f,-.5f,-.5f,   .5f,-.5f, .5f, -.5f,-.5f, .5f, // v7,v4,v3,v2 (bottom)
			     .5f,-.5f,-.5f,  -.5f,-.5f,-.5f,  -.5f, .5f,-.5f,  .5f, .5f,-.5f  // v4,v7,v6,v5 (back)
			};

			// normal array
			public static float normals[] = {
			     0, 0, 1,   0, 0, 1,   0, 0, 1,   0, 0, 1,  // v0,v1,v2,v3 (front)
			     1, 0, 0,   1, 0, 0,   1, 0, 0,   1, 0, 0,  // v0,v3,v4,v5 (right)
			     0, 1, 0,   0, 1, 0,   0, 1, 0,   0, 1, 0,  // v0,v5,v6,v1 (top)
			    -1, 0, 0,  -1, 0, 0,  -1, 0, 0,  -1, 0, 0,  // v1,v6,v7,v2 (left)
			     0,-1, 0,   0,-1, 0,   0,-1, 0,   0,-1, 0,  // v7,v4,v3,v2 (bottom)
			     0, 0,-1,   0, 0,-1,   0, 0,-1,   0, 0,-1   // v4,v7,v6,v5 (back)
			};

			public static float colors[] = {
				     1, 1, 1,   1, 1, 1,   1, 1, 1,   1, 1, 1,  // v0,v1,v2,v3 (front)
				     1, 1, 1,   1, 1, 1,   1, 1, 1,   1, 1, 1,  // v0,v3,v4,v5 (right)
				     1, 1, 1,   1, 1, 1,   1, 1, 1,   1, 1, 1,  // v0,v5,v6,v1 (top)
				     1, 1, 1,   1, 1, 1,   1, 1, 1,   1, 1, 1,  // v1,v6,v7,v2 (left)
				     1, 1, 1,   1, 1, 1,   1, 1, 1,   1, 1, 1,  // v7,v4,v3,v2 (bottom)
				     1, 1, 1,   1, 1, 1,   1, 1, 1,   1, 1, 1   // v4,v7,v6,v5 (back)
				};

			// texture coord array
			public static float texCoords[] = {
			    0.2f,   0,   0, 0,   0, 0.1f,   0.2f, 0.1f,               // v0,v1,v2,v3 (front)
			    0, 0,   0, 0.1f,   0.2f, 0.1f,   0.2f, 0,               // v0,v3,v4,v5 (right)
			    0.1f, 0.1f,   0.1f, 0,   0, 0,   0, 0.1f,               // v0,v5,v6,v1 (top)
			    0.2f, 0,   0, 0,   0, 0.1f,   0.2f, 0.1f,               // v1,v6,v7,v2 (left)
			    0, 0.1f,   0.2f, 0.1f,   0.1f, 0,   0, 0,               // v7,v4,v3,v2 (bottom)
			    0, 0.1f,   0.2f, 0.1f,   0.2f, 0,   0, 0                // v4,v7,v6,v5 (back)
			};

			// index array for glDrawElements()
			// A cube requires 36 indices = 6 sides * 2 tris * 3 verts
			public static int indices[] = {
			     0, 1, 2,   2, 3, 0,    // v0-v1-v2, v2-v3-v0 (front)
			     4, 5, 6,   6, 7, 4,    // v0-v3-v4, v4-v5-v0 (right)
			     8, 9,10,  10,11, 8,    // v0-v5-v6, v6-v1-v0 (top)
			    12,13,14,  14,15,12,    // v1-v6-v7, v7-v2-v1 (left)
			    16,17,18,  18,19,16,    // v7-v4-v3, v3-v2-v7 (bottom)
			    20,21,22,  22,23,20     // v4-v7-v6, v6-v5-v4 (back)
			};

	public BlockGrass(float x, float y, float z, float chunkX, float chunkZ) {
		super(x, y, z, chunkX, chunkZ);
		this.blockName = "grass";
	}

	@Override
	public void Draw() {
		
		
		
	glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +  +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		
		
		// Back Face
		
		
		
		
		
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(),   0.5f + this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		// Top Face
		GL11.glEnd();
		
		glBegin(GL_QUADS);
		GL11.glTexCoord2f(0.0f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f, 0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(),0.5f + this.getGlobalY() ,  0.5f  +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f, 0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		GL11.glTexCoord2f(0.1f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ()) ;	// Top Right Of The Texture and Quad
		// Bottom Face
		
		GL11.glEnd();
		
		glBegin(GL_QUADS);
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f+this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
		// Right face
	
		
		
		
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f( 0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f( 0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
		// Left Face
		
		

		
		
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() , -0.5f +this.getGlobalZ());	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.0f); GL11.glVertex3f(-0.5f + this.getGlobalX(), -0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.1f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY() ,  0.5f +this.getGlobalZ());	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.1f, 0.1f); GL11.glVertex3f(-0.5f + this.getGlobalX(), 0.5f +this.getGlobalY()  , -0.5f +this.getGlobalZ());	// Top Left Of The Texture and Quad
		GL11.glEnd();
		
		
		
	}
}

	
