package codecraft.ui.charModels;

import org.lwjgl.opengl.GL11;

import codecraft.ui.CharModel;

public class C0 extends CharModel {

	
	@Override
	public void Draw(float x, float y, float z, float f) {
		DrawQuad(x, y, z, f);
		DrawQuad(x+(0.002f * f), y +( 0.002f * f), z, f);
		DrawQuad(x-(0.002f * f), y -( 0.002f * f), z, f);
		DrawQuad(x+(0.002f * f), y +( 0.002f * f), z, f);
		DrawQuad(x + 2*(0.002f * f), y + -2 *( 0.002f * f), z, f);
		DrawQuad(x + 2*(0.002f * f), y + -1 *( 0.002f * f), z, f);
		DrawQuad(x + 2*(0.002f * f), y + 0 *( 0.002f * f), z, f);
		DrawQuad(x + 2*(0.002f * f), y + 1 *( 0.002f * f), z, f);
		DrawQuad(x + 2*(0.002f * f), y + 2 *( 0.002f * f), z, f);
		DrawQuad(x + -2*(0.002f * f), y + -2 *( 0.002f * f), z, f);
		DrawQuad(x + -2*(0.002f * f), y + -1 *( 0.002f * f), z, f);
		DrawQuad(x + -2*(0.002f * f), y + 0 *( 0.002f * f), z, f);
		DrawQuad(x + -2*(0.002f * f), y + 1 *( 0.002f * f), z, f);
		DrawQuad(x + -2*(0.002f * f), y + 2 *( 0.002f * f), z, f);
		DrawQuad(x + 0*(0.002f * f), y + 3 *( 0.002f * f), z, f);
		DrawQuad(x + 1*(0.002f * f), y + 3 *( 0.002f * f), z, f);
		DrawQuad(x + -1*(0.002f * f), y + 3 *( 0.002f * f), z, f);
		DrawQuad(x + 0*(0.002f * f), y + -3 *( 0.002f * f), z, f);
		DrawQuad(x + 1*(0.002f * f), y + -3 *( 0.002f * f), z, f);
		DrawQuad(x + -1*(0.002f * f), y + -3 *( 0.002f * f), z, f);
	}

}
