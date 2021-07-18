package codecraft.ui.charModels;

import codecraft.ui.CharModel;

public class C1 extends CharModel {

	@Override
	public void Draw(float x, float y, float z, float size) {
		Draw(x+0 * (0.002f * size), y + 0 * (0.002f * size), z, size);
		Draw(x+0 * (0.002f * size), y + 1 * (0.002f * size), z, size);
		Draw(x+0 * (0.002f * size), y + 2 * (0.002f * size), z, size);
		Draw(x+0 * (0.002f * size), y + 3 * (0.002f * size), z, size);
		Draw(x+0 * (0.002f * size), y + -1 * (0.002f * size), z, size);
		Draw(x+0 * (0.002f * size), y + -2 * (0.002f * size), z, size);
		Draw(x+0 * (0.002f * size), y + -3 * (0.002f * size), z, size);
		Draw(x+-1 * (0.002f * size), y + 2 * (0.002f * size), z, size);
		Draw(x+-1 * (0.002f * size), y + -4 * (0.002f * size), z, size);
		Draw(x+-2 * (0.002f * size), y + -4 * (0.002f * size), z, size);
		Draw(x+1 * (0.002f * size), y + -4 * (0.002f * size), z, size);
		Draw(x+2 * (0.002f * size), y + -4 * (0.002f * size), z, size);
	}

}
