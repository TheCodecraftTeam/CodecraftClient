package codecraft.player;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import codecraft.entity.PlayerHitBox;
import codecraft.ui.Item;
import codecraft.ui.items.ItemGrassBlock;

public class Player {
	public static float posX = -1;
	public static float posY = -1000;
	public static float posZ = -5;
	public static float OldposX = -1;
	public static float OldposY = -1000;
	public static float OldposZ = -5;
	public static Item itemInHand = null;
	public static int hotBarIndex = 0;
	public static float placeBlockOffsetX = 0;
	public static float placeBlockOffsetY = 0;
	public static float placeBlockOffsetZ = 0;
	public static Boolean pu =false;
	public static Boolean pd =false;
	public static float rotX = 0;
	public static float posDY = 0;
	public static boolean isinTheAir = true;
	public static float rotY = 0;
	private static PlayerHitBox entityHitBox = new PlayerHitBox(0.2f,0.2f, 0.2f, -0.2f, 2, 0.2f, -0.2f, 2, -0.2f, 0.2f, 2, -0.2f,0.2f, -2, 0.2f, -0.2f, -2, 0.2f, -0.2f, -2, -0.2f, 0.2f, -2, -0.2f);
	public static float getPosX() {
		return posX;
	}
	public static void setPosX(float posX) {
		Player.posX = posX;
	}
	public static float getPosY() {
		return posY;
	}
	public static void setPosY(float posY) {
		Player.posY = posY;
	}
	public static float getPosZ() {
		return posZ;
	}
	public static void setPosZ(float posZ) {
		Player.posZ = posZ;
	}
	public static float getRotX() {
		return rotX;
	}
	public static void setRotX(float rotX) {
		Player.rotX = rotX;
	}
	public static float getRotY() {
		return rotY;
	}
	public static void setRotY(float rotY) {
		Player.rotY = rotY;
	}
	public static PlayerHitBox getEntityHitBox() {
		return entityHitBox;
	}
	public static void drawHotBars() {
		
		for(int i = 0; i < 9; i++) {
			Item item = new ItemGrassBlock();
		Vector3f color = null;
		if(hotBarIndex == i) {
			color=new Vector3f(1,1,1 );
			itemInHand = item;
		}else {
			color = new Vector3f(0,0,0 );
		}
	
		
		Player.drawHotBar(-0.1f + +((i * 0.0125f)+ (i*0.01f)), -0.075f, -0.2f, color, item );
		
		
		}
		
		}
	public static void drawHotBar(float x, float y, float z, Vector3f color, Item item) {
		GL11.glColor3f(color.x, color.y, color.z);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.01f, y + 0.001f-+ 0.01f, z);
		GL11.glVertex3f(x - 0.01f, y + 0.001f-+ 0.01f, z);
		GL11.glVertex3f(x - 0.01f, y - 0.001f-+ 0.01f, z);
		GL11.glVertex3f(x + 0.01f, y - 0.001f-+ 0.01f, z);
		GL11.glEnd();
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.01f, y + 0.001f+ 0.01f, z);
		GL11.glVertex3f(x - 0.01f, y + 0.001f+ 0.01f, z);
		GL11.glVertex3f(x - 0.01f, y - 0.001f+ 0.01f, z);
		GL11.glVertex3f(x + 0.01f, y - 0.001f+ 0.01f, z);
		GL11.glEnd();
		
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.001f-+ 0.01f, y + + 0.01f, z);
		GL11.glVertex3f(x + 0.001f-+ 0.01f, y - 0.01f, z);
		GL11.glVertex3f(x - 0.001f-+ 0.01f, y - 0.01f, z);
		GL11.glVertex3f(x - 0.001f-+ 0.01f, y + 0.01f, z);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.001f+ 0.01f, y + + 0.01f, z);
		GL11.glVertex3f(x + 0.001f+ 0.01f, y - 0.01f, z);
		GL11.glVertex3f(x - 0.001f+ 0.01f, y - 0.01f, z);
		GL11.glVertex3f(x - 0.001f+ 0.01f, y + 0.01f, z);
		GL11.glEnd();
		
		
		GL11.glColor3f(1, 1, 1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		item.draw(x, y, z, 0.01f);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}


}
