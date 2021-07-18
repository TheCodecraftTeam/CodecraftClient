package codecraft.ui;

import org.lwjgl.opengl.GL11;

public abstract class CharModel {

public void DrawQuad(float x, float y, float z, float f) {
	GL11.glColor3f(0, 0, 0);
	GL11.glBegin(GL11.GL_QUADS);
	
	GL11.glVertex3f(x +(0.001f * f), y+(0.001f * f), z);
	GL11.glVertex3f(x -(0.001f * f), y+(0.001f * f), z);
	GL11.glVertex3f(x -(0.001f * f), y-(0.001f * f), z);
	GL11.glVertex3f(x +(0.001f * f), y-(0.001f * f), z);
	GL11.glEnd();
	GL11.glColor3f(1, 1, 1);
}
public abstract void Draw(float x, float y, float z, float size);
}
