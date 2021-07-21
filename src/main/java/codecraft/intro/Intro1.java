package codecraft.intro;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import codecraft.renderEngine.WindowUtils;
import codecraft.renderEngine.WindowVariables;
import codecraft.world.World;

public class Intro1 {

	public static void playIntro() {
		World.textures.bind();
		int fn = 0;
		while(fn < 300) {
		long startTime = System.currentTimeMillis();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.529f, 0.808f, 0.922f, 0.5f);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		DrawStoneCube(-2, 0, -30);
		DrawStoneCube(-2, 1, -30);
		DrawStoneCube(-2, 2, -30);
		DrawStoneCube(-2, -1, -30);
		DrawStoneCube(-2, -2, -30);
		DrawStoneCube(-1, 3, -30);
		DrawStoneCube(0, 3, -30);
		DrawStoneCube(1, 3, -30);
		DrawStoneCube(2, 2, -30);
		DrawStoneCube(2, -2, -30);
		DrawStoneCube(-1, -3, -30);
		DrawStoneCube(0, -3, -30);
		DrawStoneCube(1, -3, -30);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GLFW.glfwSwapBuffers(WindowVariables.window);
		try {
			WindowUtils.FPS(60, System.currentTimeMillis() - startTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fn++;
	}
		
	}
public static void DrawStoneCube(float x, float y, float z) {
	glBegin(GL_QUADS);
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f(-0.5f +x, -0.5f +y ,  0.5f +  +z);	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f( 0.5f +x, -0.5f +y ,  0.5f +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f +x, 0.5f +y ,  0.5f +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f +x, 0.5f +y ,  0.5f +z);	// Top Left Of The Texture and Quad
	// Back Face
	
	
	
	
	
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f(-0.5f +x, -0.5f +y , -0.5f +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f +x,   0.5f +y , -0.5f +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f +x, 0.5f +y , -0.5f +z);	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f( 0.5f +x, -0.5f +y , -0.5f +z);	// Bottom Left Of The Texture and Quad
	// Top Face
	GL11.glEnd();
	
	glBegin(GL_QUADS);
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f +x, 0.5f +y , -0.5f +z);	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f(-0.5f +x,0.5f +y ,  0.5f  +z);	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f( 0.5f +x, 0.5f +y ,  0.5f +z);	// Bottom Right Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f +x, 0.5f +y , -0.5f +z) ;	// Top Right Of The Texture and Quad
	// Bottom Face
	
	GL11.glEnd();
	
	glBegin(GL_QUADS);
GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f +x, -0.5f +y , -0.5f +z);	// Top Right Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f +x, -0.5f +y , -0.5f +z);	// Top Left Of The Texture and Quad
	GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f( 0.5f +x, -0.5f +y ,  0.5f+z);	// Bottom Left Of The Texture and Quad
	GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f(-0.5f +x, -0.5f +y ,  0.5f +z);	// Bottom Right Of The Texture and Quad
	// Right face

	
	
	
GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f( 0.5f +x, -0.5f +y , -0.5f +z);	// Bottom Right Of The Texture and Quad
GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f +x, 0.5f +y , -0.5f +z);	// Top Right Of The Texture and Quad
GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f( 0.5f +x, 0.5f +y ,  0.5f +z);	// Top Left Of The Texture and Quad
GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f( 0.5f +x, -0.5f +y ,  0.5f +z);	// Bottom Left Of The Texture and Quad
	// Left Face
	
	

	
	
GL11.glTexCoord2f(0.0f+ 0.2f, 0.0f); GL11.glVertex3f(-0.5f +x, -0.5f +y , -0.5f +z);	// Bottom Left Of The Texture and Quad
GL11.glTexCoord2f(0.1f+ 0.2f, 0.0f); GL11.glVertex3f(-0.5f +x, -0.5f +y ,  0.5f +z);	// Bottom Right Of The Texture and Quad
GL11.glTexCoord2f(0.1f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f +x, 0.5f +y ,  0.5f +z);	// Top Right Of The Texture and Quad
GL11.glTexCoord2f(0.0f+ 0.2f, 0.1f); GL11.glVertex3f(-0.5f +x, 0.5f +y  , -0.5f +z);	// Top Left Of The Texture and Quad
	GL11.glEnd();
	
}
}