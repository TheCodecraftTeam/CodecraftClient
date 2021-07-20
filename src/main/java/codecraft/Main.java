package codecraft;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.stb.STBEasyFont;

import codecraft.intro.Intro1;
import codecraft.player.Player;
import codecraft.renderEngine.WindowUtils;
import codecraft.renderEngine.WindowVariables;
import codecraft.ui.Text;
import codecraft.world.Block;
import codecraft.world.Chunk;
import codecraft.world.World;
import codecraft.world.blocks.BlockGrass;
import codecraft.world.generator.FlatWorldGenerator;
import codecraft.world.generator.NormalWorldGenerator;


public class Main {
	private static Chunk[][] chunks; 
	public static void main(String[] args) throws FileNotFoundException, Exception {
		runCodecraft("", "");
	}
	public static void runCodecraft(String Username, String versionLabel) throws FileNotFoundException, Exception {
		
	WindowUtils.createWindowAndOpenglContext("Codecraft", 1920,1080);
	WindowUtils.configureOpenGL();
	WindowUtils.setupKeyBindingsAndMouse();
	Intro1.playIntro();
	new Thread() {
		public void run() {
			chunks = NormalWorldGenerator.generateWorld();
		}
	}.start();
	while(NormalWorldGenerator.getProgress() < 1.0) {
		long startTime = System.currentTimeMillis();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(0.529f, 0.808f, 0.922f, 0.5f);
		Text.DrawText(0, 0, -0.2f, 0.5f, String.valueOf((int)(NormalWorldGenerator.getProgress()*100)));
		GL11.glLoadIdentity();
		GLFW.glfwSwapBuffers(WindowVariables.window);
		WindowUtils.FPS(60, System.currentTimeMillis() - startTime);
	}
	long startTime = System.currentTimeMillis();
	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	GL11.glClearColor(0.529f, 0.808f, 0.922f, 0.5f);
	Text.DrawText(0, 0, -0.2f, 0.5f, String.valueOf((int)(NormalWorldGenerator.getProgress()*100)));
	GL11.glLoadIdentity();
	GLFW.glfwSwapBuffers(WindowVariables.window);
	WindowUtils.FPS(60, System.currentTimeMillis() - startTime);
	World.loadChunks(chunks);
	long st = System.currentTimeMillis();
	
	
	//World.SetBlockAtPosition(0, 52, 0);
	while (!GLFW.glfwWindowShouldClose(WindowVariables.window) && !WindowVariables.Done())
    {
		startTime = System.currentTimeMillis();
		Player.OldposX = Player.getPosX();
		Player.OldposY = Player.getPosY();
		Player.OldposZ = Player.getPosZ();
		WindowUtils.CheckKeys();
		if(Player.isinTheAir) {
		WindowUtils.updateYPosition();
		
	
		}
		WindowUtils.getBlockPlayerIsLookingAt();
		WindowUtils.updateXY();
		Block DownBlock1 = null;
		
		int x =(int) Math.roundHalfDown(-Player.posX);
		int y = (int) Math.roundHalfDown(-Player.posY);
		int z = (int) Math.roundHalfDown(-Player.posZ);
		//System.out.println(-Player.posY);
		
		while(true) {
			try {
				DownBlock1 = World.getBlockAtPos(x,y,z);
				if(DownBlock1 == null) {
					y--;
					if(y <= -1) {
						Player.isinTheAir = true;
						break;
					}
					continue;
				}
				break;
			}catch(Exception e){
				y--;
				if(y <= -1) {
					Player.isinTheAir = true;
					break;
				}
			}
		}
		
		if(-Player.posY - y > 1) {
			Player.isinTheAir = true;
		}
		
		GLFW.glfwPollEvents();
		
		
        /* Render here */
		
		
		WindowUtils.setupNextFrame();
		
		
       // Vector3f v = WindowUtils.getBlockPosPlayerIsLookingAt();
		//System.out.println("x : " + v.x);
		//System.out.println("y : " + v.y);
		//System.out.println("z : " + v.z);
		
		World.drawWorld();
		
		GLFW.glfwSwapBuffers(WindowVariables.window);

        /* Poll for and process events */
		if(System.currentTimeMillis() - st >= 15000) {
			World.loadChunks(World.chunks);
			st = System.currentTimeMillis();
		}
		WindowUtils.FPS(60, System.currentTimeMillis() - startTime);
    }
	//World.saveWorldtoFile();
	GLFW.glfwTerminate();
	}

}
