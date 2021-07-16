package codecraft.renderEngine;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class drawList {
public static ArrayList<float[]> vertexs = new ArrayList<>();
public static ArrayList<float[]> textureCords = new ArrayList<>();
public static ArrayList<Texture> textures = new ArrayList<>();
public static void reset() {
	vertexs = new ArrayList<>();
	textureCords = new ArrayList<>();
	textures = new ArrayList<>();
}
public static void storeTextureCordToDrawList(float x, float y) {
	textureCords.add(new float[] {x,y});
}
public static void storeVertexToDrawList(float x, float y,float z) {
	vertexs.add(new float[] {x,y,z});
}
public static void storeTextureToDrawList(Texture texture) {
	textures.add(texture);
}
public static void draw() {
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	int count = 0;
	for(int i = 0; i < vertexs.size(); i++) {
		Texture texture = textures.get(i);
		float[] vertex = vertexs.get(i);
		float[] textureCord = textureCords.get(i);
		
		if(texture != null && count == 0) {
			texture.bind();
		}
		if(count == 0) {
		GL11.glBegin(GL11.GL_QUADS);
		}
		GL11.glTexCoord2f(textureCord[0], textureCord[1]);
		GL11.glVertex3f(vertex[0], vertex[1], vertex[2]);
		System.out.println(vertex[0]);
		System.out.println(vertex[1]);
		System.out.println(vertex[2]);
		if(count == 3) {
		GL11.glEnd();
		count = 0;
		}
		count++;
	}
	GL11.glDisable(GL11.GL_TEXTURE_2D);
}
}
