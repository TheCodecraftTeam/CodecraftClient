package codecraft.ui.charModels;

import codecraft.ui.CharModel;

public class CPeriod extends CharModel {

	@Override
	public void Draw(float x, float y, float z, float size) {
    DrawQuad(x+-2 * (0.002f * size), y + -2 * (0.002f * size), z, size);
    DrawQuad(x+-2 * (0.002f * size), y + -3 * (0.002f * size), z, size);
	}

}
