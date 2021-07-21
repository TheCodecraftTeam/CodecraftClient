package codecraft.renderEngine;

import org.joml.Matrix4f;

public class WindowVariables {
public static double fps = 0;
static int width = 0;
static int height = 0;
static boolean pressedRightClick = false;
static boolean pressedLeftClick = false;
static boolean done = false;
static Matrix4f m;
public static float delta = 0;
public static long window = 0;

public static int getWidth() {
	return width;
}
public static int getHeight() {
	return height;
}
public static long getWindow() {
	return window;
}
public static boolean Done() {
	return done;
}



}
