package codecraft;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.stb.STBEasyFont;



import codecraft.intro.Intro1;
import codecraft.math.MathUtills;
import codecraft.player.Player;
import codecraft.renderEngine.PlotCell3f;
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
		try {
			World.rd = Integer.valueOf(args[0]);
			World.nrd = -Integer.valueOf(args[0]);
		}catch(Exception e) {
			
		}
		World.rd = 16;
		World.nrd = -16;
		runCodecraft("", "");
	}
	public static void runCodecraft(String Username, String versionLabel) throws FileNotFoundException, Exception {
		
	WindowUtils.createWindowAndOpenglContext("Codecraft", 1920,1080);
	WindowUtils.configureOpenGL();
	WindowUtils.setupKeyBindingsAndMouse();
	World.textures.init("Textures/Textures.bmp");
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
	int fs = 0;
	
	//World.SetBlockAtPosition(0, 52, 0);
	while (!GLFW.glfwWindowShouldClose(WindowVariables.window) && !WindowVariables.Done())
    {
		
		int startTimeNano = (int) System.nanoTime();
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
		double lastTime = System.nanoTime();
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
		float pitchRadian = (float) (Player.rotY * (Math.PI / 180)); // X rotation
		float yawRadian = (float) ((Player.rotX) * (Math.PI/180));
		//float yawRadian   = (float) (-Player.rotX * (Math.PI / 180)); // Y rotation
		float offset = 1f;
		float x1 = 0* offset *  Math.sin( yawRadian ) * Math.cos( pitchRadian );
		float y1 = 0 * offset * -Math.sin( pitchRadian );
		float z1 = 0* -(offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian ));
		
		int tn = 0;
		while(true) {
			float x2 = x1+offset *  Math.sin( yawRadian ) * Math.cos( pitchRadian );
			float z2 = z1 + -(offset *  Math.cos( yawRadian ) * Math.cos( pitchRadian ));
			try {
				Block b = World.getBlockAtPos(MathUtills.floor(x2 -Player.posX), MathUtills.floor(y1-Player.posY+1),+MathUtills.floor(z2-Player.posZ));
				if(b != null) {
					x1 = x2;
					z1 = z2;
					break;
				}

			
			
			}catch(Exception e){
				break;
			}
			x1 = x2;
			z1 = z2;
			float y2 = y1 + offset * -Math.sin( pitchRadian );
			try {
				Block b = World.getBlockAtPos(MathUtills.floor(x1 -Player.posX), MathUtills.floor(y2-Player.posY+1),+MathUtills.floor(z1-Player.posZ));
				if(b != null) {
					y1 = y2;
					break;
				}

			
			
			}catch(Exception e){
				break;
			}
			y1 = y2;
			if(tn > 5) {
				
				break;
			}
		tn++;
		}
		
		
		Block b = null;
		try {
			b = World.getBlockAtPos(MathUtills.floor(x1 -Player.posX), MathUtills.floor(y1-Player.posY+1),+MathUtills.floor(z1-Player.posZ));
		}catch(Exception e){
			
		}
		if(b != null) {
			GL11.glColor4f(1,1,1,0.5f);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			GL11.glAlphaFunc(GL11.GL_GREATER, 0.0f);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		new BlockGrass(MathUtills.floor(x1 -Player.posX), MathUtills.floor(y1-Player.posY+1),+MathUtills.floor(z1-Player.posZ),0,0).Draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glColor3f(1,1,1);
		}
		Vector3f d = new Vector3f();
		/*
		d.x = Math.cos(Player.rotX)*Math.cos(Player.rotY);
				d.y = Math.sin(Player.rotX)*Math.cos(Player.rotY);
				d.z = Math.sin(Player.rotY);
				//System.out.println("x:"+d.x);
				 // System.out.println("y:"+d.y);
				  //System.out.println("z:"+d.z);
				PlotCell3f plotter = new PlotCell3f(0, 0, 0, 1, 1, 1);
				// From the center of the camera and its direction...
				plotter.plot( new Vector3f(Player.posX,Player.posY,Player.posZ), d, 1000);
				// Find the first non-air block
				while ( plotter.next() ) {
				   Vector3i v = plotter.get();
				   Block b = null;
				   try {
				   b = World.getBlockAtPos(-v.x, -v.y, -v.z);
				   }catch(Exception e){
					   
				   }
				   System.out.println("x:"+v.x);
					  System.out.println("y:"+v.y);
					  System.out.println("z:"+v.z);
				   if (b != null) {
					   System.out.println("x:"+v.x);
						  System.out.println("y:"+v.y);
						  System.out.println("z:"+v.z);
				      plotter.end();
				      // set selected block to v
				   }
				}
				*?
				*/
		GLFW.glfwSwapBuffers(WindowVariables.window);

        
		
		if(System.currentTimeMillis() - st >= (World.rd * 2)*1000) {
			World.loadChunks(World.chunks);
			st = System.currentTimeMillis();
		}
		
		//WindowUtils.FPS(30, System.currentTimeMillis() - startTime);
		//System.out.println(((System.currentTimeMillis()) - startTime)/ 16f );
		 WindowVariables.delta = (System.currentTimeMillis() - startTime) / 16f;
		 fs++;
		 WindowVariables.fps = -(1000000000.0 / (lastTime - (lastTime = System.nanoTime())));
    }
	//World.saveWorldtoFile();
	GLFW.glfwTerminate();
	}

}
