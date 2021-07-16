package codecraft.renderEngine;

import org.joml.Matrix4f;

public class WindowVariables {
static int width = 0;
static int height = 0;

static boolean done = false;
static Matrix4f m;
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
