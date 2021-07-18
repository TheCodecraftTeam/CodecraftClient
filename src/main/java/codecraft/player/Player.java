package codecraft.player;

import codecraft.entity.PlayerHitBox;

public class Player {
	public static float posX = -1;
	public static float posY = -1000;
	public static float posZ = -5;
	public static float OldposX = -1;
	public static float OldposY = -1000;
	public static float OldposZ = -5;
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
	


}
