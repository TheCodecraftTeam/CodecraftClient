package codecraft.ui.charModels;

import codecraft.ui.CharModel;

public class C1 extends CharModel {

	@Override
	public void Draw(float x, float y, float z, float size) {
		DrawQuad(x+0 * (0.002f * size), y + 0 * (0.002f * size) + 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + 1 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + 2 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + 3 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + -1 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + -2 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + -3 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+-1 * (0.002f * size), y + 2 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+-1 * (0.002f * size), y + -4 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+-2 * (0.002f * size), y + -4 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+1 * (0.002f * size), y + -4 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+2 * (0.002f * size), y + -4 * (0.002f * size)+ 0.002f/2f, z, size);
		DrawQuad(x+0 * (0.002f * size), y + -4 * (0.002f * size)+ 0.002f/2f, z, size);
	}

}
