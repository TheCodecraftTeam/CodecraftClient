package codecraft.renderEngine;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LEQUAL;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClearDepth;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadMatrixf;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.nio.FloatBuffer;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.FontSizeAction;

import org.joml.Math;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import codecraft.entity.PlayerHitBox;
import codecraft.entity.StaticHitBox;
import codecraft.math.MathUtills;
import codecraft.player.Player;
import codecraft.ui.Text;
import codecraft.ui.charModels.C0;
import codecraft.ui.charModels.C1;
import codecraft.ui.charModels.C2;
import codecraft.world.Block;
import codecraft.world.World;
import codecraft.world.blocks.BlockGrass;
import codecraft.world.blocks.BlockStone;
import codecraft.world.blocks.BlockWood;
import codecraft.world.lighting.LightingController;

public class WindowUtils {

	public static String readShader(String filePath) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(filePath));
		String shaderCode = "";
		while (reader.hasNextLine()) {
			shaderCode += reader.nextLine() + "\n";
		}
		reader.close();
		return shaderCode;
	}

	public static void createWindowAndOpenglContext(String title, int width, int height) {
		WindowVariables.height = height;
		WindowVariables.width = width;
		if (!GLFW.glfwInit())
			return;

		/* Create a windowed mode window and its OpenGL context */
		WindowVariables.window = glfwCreateWindow(width, height, title, GLFW.glfwGetPrimaryMonitor(), NULL);
		if (WindowVariables.window == 0) {
			GLFW.glfwTerminate();
			return;
		}
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 0);
		/* Make the window's context current */

		GLFW.glfwMakeContextCurrent(WindowVariables.window);
		GL.createCapabilities();

	}

	public static void configureOpenGL() throws Exception {

		WindowVariables.fb = BufferUtils.createFloatBuffer(16);
		WindowVariables.m = new Matrix4f();
		WindowVariables.m.setPerspective((float) Math.toRadians(50.0f),
				(float) WindowVariables.width / (float) WindowVariables.height, 0.01f, 1000000.0f);
		glMatrixMode(GL_PROJECTION);
		glLoadMatrixf(WindowVariables.m.get(WindowVariables.fb));
		WindowVariables.m.setLookAt(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadMatrixf(WindowVariables.m.get(WindowVariables.fb));
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f); // Black Background
		glClearDepth(1.0f); // Depth Buffer Setup
		// glDepthFunc(GL_LEQUAL); // The Type Of Depth Testing (Less Or Equal)
		glEnable(GL_DEPTH_TEST);// Enable Depth Testing

		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// glEnable(GL11.GL_CULL_FACE);
		 GL11.glShadeModel (GL11.GL_SMOOTH);
		
		  //GL11.glMaterialfv(GL11.GL_FRONT, GL11.GL_SPECULAR, mat_specular);
		
		 
		float density = 0.005f;

		float fogColor[] = { 0.529f * LightingController.worldLighting, 0.808f * LightingController.worldLighting,
				0.922f * LightingController.worldLighting, 0.5f };

		

		WindowVariables.shaderProgram = new ShaderProgram();
		System.out.println(readShader("shader/VertexShader"));
		WindowVariables.shaderProgram.createVertexShader(readShader("shader/VertexShader"));

		// WindowVariables.shaderProgram.createFragmentShader(readShader("shader/FragShader"));
		WindowVariables.shaderProgram.createFragmentShader(readShader("shader/FragShader"));
		World.textures.bind();
		WindowVariables.shaderProgram.link();

	//WindowVariables.shaderProgram.bind();

	}

	public static void setupKeyBindingsAndMouse() {
		GLFW.glfwSetInputMode(WindowVariables.window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_HIDDEN);
		GLFW.glfwSetKeyCallback(WindowVariables.window, new GLFWKeyCallback() {

			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == GLFW.GLFW_KEY_ESCAPE) {

					WindowVariables.done = true;
				}

			}
		});
		GLFW.glfwSetScrollCallback(WindowVariables.window, new GLFWScrollCallback() {

			@Override
			public void invoke(long window, double xoffset, double yoffset) {
				if (yoffset > 0) {
					Player.hotBarIndex--;
					if (Player.hotBarIndex < 0) {
						Player.hotBarIndex = 8;
					}
				}
				if (yoffset < 0) {
					Player.hotBarIndex++;
					if (Player.hotBarIndex > 8) {
						Player.hotBarIndex = 0;
					}
				}
			}
		});
		GLFW.glfwSetMouseButtonCallback(WindowVariables.window, new GLFWMouseButtonCallback() {

			@Override
			public void invoke(long window, int button, int action, int mods) {
				if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT && action == GLFW.GLFW_PRESS) {
					WindowVariables.pressedRightClick = true;
				}

				if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && action == GLFW.GLFW_PRESS) {
					WindowVariables.pressedLeftClick = true;
				}

			}
		});
		GLFW.glfwSetCursorPosCallback(WindowVariables.window, new GLFWCursorPosCallback() {

			public void invoke(long window, double x, double y) {
				float lastrotX = Player.rotX;
				float DX = (float) (x - (WindowVariables.width + 1f) / 2f);
				float DY = (float) (y - ((WindowVariables.height + 1f) / 2f));
				if (Player.rotX > 360 || Player.rotX < -360) {

					Player.rotX = 0;
				}
				if (Player.rotY > 90) {
					Player.rotY = 90;
				} else if (Player.rotY < -90) {
					Player.rotY = -90;
				}

				Player.rotX = (float) (Player.rotX + (DX / 10f));

				Player.rotY = (float) (Player.rotY + (DY / 10f));

			}
		});

	}

	public static void showBlockWherePlayerIsLookin() {

	}

	public static void CheckKeys() {
		if (WindowVariables.pressedRightClick) {
			if (Player.hotBarItems[Player.hotBarIndex] != null) {
				Player.hotBarItems[Player.hotBarIndex].action();
			}
			WindowVariables.pressedRightClick = false;
		}
		if (WindowVariables.pressedLeftClick) {
			if (Player.hotBarItems[Player.hotBarIndex] == null) {
				Vector3f blockPosition = Player.blockSelected;
				if (blockPosition == null) {
					return;
				}
				try {
					World.SetBlockAtPosition((int) blockPosition.x, (int) blockPosition.y, (int) blockPosition.z, null);
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int chunkXpos = (int) blockPosition.x;
				int chunkX;
				while (true) {
					if (chunkXpos % 16 == 0) {
						break;
					}
					chunkXpos--;
				}
				chunkX = chunkXpos / 16;

				int chunkZpos = (int) blockPosition.z;
				int chunkZ;
				while (true) {
					if (chunkZpos % 16 == 0) {
						break;
					}
					chunkZpos--;
				}
				chunkZ = chunkZpos / 16;
				int i;
				try {
					i = World.ChunkPositonToChunkNumber(chunkX, chunkZ);
					GL11.glNewList(World.displayListIndex + i, GL11.GL_COMPILE);
					GL11.glColor4f(1 * LightingController.worldLighting + 0.2f,
							1 * LightingController.worldLighting + 0.3f, 1 * LightingController.worldLighting + 0.3f,
							0.5f);
					World.chunks[chunkX][chunkZ].DrawChunk();
					GL11.glEndList();
				} catch (Exception e) {

				}
			}

			WindowVariables.pressedLeftClick = false;
		}
		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_UP) == GLFW.GLFW_PRESS) {
			if (!Player.pu) {
				Player.placeBlockOffsetY++;
				Player.pu = true;
			}
		} else {
			Player.pu = false;
		}

		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_DOWN) == GLFW.GLFW_PRESS) {
			if (!Player.pd) {
				Player.placeBlockOffsetY--;
				Player.pd = true;
			}
		} else {
			Player.pd = false;
		}
		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {

			float yawRadian = (float) ((Player.rotX) * (Math.PI / 180f));

			if (Player.isinTheAir) {
				Player.posX += WindowVariables.delta * (0.2f * Math.sin(yawRadian) * Math.cos(90));
				Player.posZ -= WindowVariables.delta * (0.2f * Math.cos(yawRadian) * Math.cos(90));
			} else {
				Player.posX += WindowVariables.delta * (0.5f * Math.sin(yawRadian) * Math.cos(90));
				Player.posZ -= WindowVariables.delta * (0.5f * Math.cos(yawRadian) * Math.cos(90));
			}

			float offset = 0.1f;
			float pitchRadian = (float) (Player.rotY * (Math.PI / 180)); // X rotation

			// float yawRadian = (float) (-Player.rotX * (Math.PI / 180)); // Y rotation
			/*
			 * Player.posX -= offset * Math.sin( yawRadian ) * Math.cos( pitchRadian );
			 * Player.posY -= offset * -Math.sin( pitchRadian ); Player.posZ += (offset *
			 * Math.cos( yawRadian ) * Math.cos( pitchRadian ));
			 */
		}
		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
			if (!Player.isinTheAir) {
				Player.posDY = 0.15f;
				Player.isinTheAir = true;
			}
		}
		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {

		}
		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
			float yawRadian = (float) ((Player.rotX) * (Math.PI / 180f));
			if (Player.isinTheAir) {
				Player.posX -= WindowVariables.delta * (0.2f * Math.cos(yawRadian) * Math.cos(90));
				Player.posZ -= WindowVariables.delta * (0.2f * Math.sin(yawRadian) * Math.cos(90));

			} else {
				Player.posX -= WindowVariables.delta * (0.5f * Math.cos(yawRadian) * Math.cos(90));
				Player.posZ -= WindowVariables.delta * (0.5f * Math.sin(yawRadian) * Math.cos(90));
			}
		}

		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
			float yawRadian = (float) ((Player.rotX) * (Math.PI / 180f));
			if (Player.isinTheAir) {
				Player.posX += WindowVariables.delta * (0.2f * Math.cos(yawRadian) * Math.cos(90));
				Player.posZ += WindowVariables.delta * (0.2f * Math.sin(yawRadian) * Math.cos(90));

			} else {
				Player.posX += WindowVariables.delta * (0.5f * Math.cos(yawRadian) * Math.cos(90));
				Player.posZ += WindowVariables.delta * (0.5f * Math.sin(yawRadian) * Math.cos(90));
			}
		}
		if (GLFW.glfwGetKey(WindowVariables.window, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
			float yawRadian = (float) ((Player.rotX) * (Math.PI / 180f));
			if (Player.isinTheAir) {
				Player.posX -= WindowVariables.delta * (0.2f * Math.sin(yawRadian) * Math.cos(90));
				Player.posZ += WindowVariables.delta * (0.2f * Math.cos(yawRadian) * Math.cos(90));
			} else {
				Player.posX -= WindowVariables.delta * (0.5f * Math.sin(yawRadian) * Math.cos(90));
				Player.posZ += WindowVariables.delta * (0.5f * Math.cos(yawRadian) * Math.cos(90));
			}
		}

	}

	public static void updateYPosition() {

		if (-Player.posDY < 1.6f) {
			Player.posDY -= WindowVariables.delta * 0.008f;
		}
		for (int i = 0; i < 100; i++) {
			Player.posY -= (WindowVariables.delta * Player.posDY) / 100;
			Block DownBlock1 = null;
			/*
			 * Block DownBlock2 = null; Block DownBlock3 = null; Block DownBlock4 = null;
			 * Block DownBlock5 = null; Block DownBlock6 = null; Block DownBlock7 = null;
			 * Block DownBlock8 = null; Block DownBlock9 = null;
			 */
			int x = (int) Math.roundHalfDown(-Player.posX);
			int y = (int) Math.roundHalfDown(-Player.posY);
			int z = (int) Math.roundHalfDown(-Player.posZ);
			while (true) {
				try {
					DownBlock1 = World.getBlockAtPos(x, y, z);
					break;
				} catch (Exception e) {
					y--;
					if (y <= -1) {
						break;
					}
				}
			}

			/*
			 * try { if(DownBlock1 != null) { DownBlock2 = World.getBlockAtPos(x +1,y,z);
			 * DownBlock3 = World.getBlockAtPos(x -1,y,z); DownBlock4 =
			 * World.getBlockAtPos(x,y,z+1); DownBlock5 = World.getBlockAtPos(x,y,-1);
			 * DownBlock6 = World.getBlockAtPos(x+1,y,z+1); DownBlock7 =
			 * World.getBlockAtPos(x+1,y,z-1); DownBlock8 = World.getBlockAtPos(x-1,y,z-1);
			 * DownBlock9 = World.getBlockAtPos(x-1,y,z+1); } }catch(Exception e) {
			 * 
			 * }
			 */

			if (DownBlock1 != null) {
				if (Player.getEntityHitBox().checkCollsionWithBlock(DownBlock1, 0)) {
					Player.posY = Math.floor(Player.posY);

					Player.posDY = 0;
					Player.isinTheAir = false;
					break;
				}
			}
		}
		/*
		 * if( WindowVariables.pressedRightClick == true) {
		 * 
		 * 
		 * try { World.SetBlockAtPosition((int)DownBlock.getGlobalX(),(int)DownBlock.
		 * getGlobalY()+1 + (int)Player.placeBlockOffsetY, (int)DownBlock.getGlobalZ(),
		 * BlockWood.class); } catch (InstantiationException | IllegalAccessException |
		 * IllegalArgumentException | InvocationTargetException | SecurityException e1)
		 * { // TODO Auto-generated catch block e1.printStackTrace(); } Player.posY-=2;
		 * int chunkXpos = (int)DownBlock.getGlobalX(); int chunkX; while(true) {
		 * if(chunkXpos % 16 == 0) { break; } chunkXpos--; } chunkX = chunkXpos/16;
		 * 
		 * 
		 * int chunkZpos = (int)DownBlock.getGlobalZ(); int chunkZ; while(true) {
		 * if(chunkZpos % 16 == 0) { break; } chunkZpos--; } chunkZ = chunkZpos/16; int
		 * i; try { i = World.ChunkPositonToChunkNumber(chunkX, chunkZ);
		 * GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
		 * World.chunks[chunkX][chunkZ].DrawChunk(); GL11.glEndList(); }catch(Exception
		 * e) {
		 * 
		 * } WindowVariables.pressedRightClick = false;
		 * 
		 * }
		 * 
		 * 
		 * 
		 * if( WindowVariables.pressedLeftClick == true) {
		 * 
		 * 
		 * try { World.SetBlockAtPosition((int)DownBlock.getGlobalX(),(int)DownBlock.
		 * getGlobalY() , (int)DownBlock.getGlobalZ(), null); } catch
		 * (InstantiationException | IllegalAccessException | IllegalArgumentException |
		 * InvocationTargetException | SecurityException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); }
		 * 
		 * int chunkXpos = (int)DownBlock.getGlobalX(); int chunkX; while(true) {
		 * if(chunkXpos % 16 == 0) { break; } chunkXpos--; } chunkX = chunkXpos/16;
		 * 
		 * 
		 * int chunkZpos = (int)DownBlock.getGlobalZ(); int chunkZ; while(true) {
		 * if(chunkZpos % 16 == 0) { break; } chunkZpos--; } chunkZ = chunkZpos/16; int
		 * i; try { i = World.ChunkPositonToChunkNumber(chunkX, chunkZ);
		 * GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
		 * World.chunks[chunkX][chunkZ].DrawChunk(); GL11.glEndList(); }catch(Exception
		 * e) {
		 * 
		 * } try { i = World.ChunkPositonToChunkNumber(chunkX+1, chunkZ);
		 * GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
		 * World.chunks[chunkX+1][chunkZ].DrawChunk(); GL11.glEndList();
		 * }catch(Exception e) {
		 * 
		 * } try { i = World.ChunkPositonToChunkNumber(chunkX, chunkZ+1);
		 * GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
		 * World.chunks[chunkX][chunkZ+1].DrawChunk(); GL11.glEndList();
		 * }catch(Exception e) {
		 * 
		 * } try { i = World.ChunkPositonToChunkNumber(chunkX-1, chunkZ);
		 * GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
		 * World.chunks[chunkX-1][chunkZ].DrawChunk(); GL11.glEndList();
		 * }catch(Exception e) {
		 * 
		 * } try { i = World.ChunkPositonToChunkNumber(chunkX, chunkZ-1);
		 * GL11.glNewList(World.displayListIndex +i, GL11.GL_COMPILE);
		 * World.chunks[chunkX][chunkZ-1].DrawChunk(); GL11.glEndList();
		 * }catch(Exception e) {
		 * 
		 * } WindowVariables.pressedLeftClick = false;
		 * 
		 * }
		 */

	}

	public static void updateXY() {
		Block SideBlock1 = null;

		Block SideBlock2 = null;
		/*
		 * Block DownBlock3 = null; Block DownBlock4 = null; Block DownBlock5 = null;
		 * Block DownBlock6 = null; Block DownBlock7 = null; Block DownBlock8 = null;
		 * Block DownBlock9 = null;
		 */

		int x = (int) Math.roundHalfDown(-Player.posX);
		int y = (int) Math.roundHalfDown(-Player.posY);
		int z = (int) Math.roundHalfDown(-Player.posZ);

		int i = 0;
		while (true) {
			try {
				SideBlock1 = World.getBlockAtPos(x, y, z);

				break;
			} catch (Exception e) {
				x--;
				if (x <= -Player.posX - 10) {
					break;
				}
			}
		}
		int x2 = (int) Math.roundHalfDown(-Player.posX);
		int y2 = (int) Math.roundHalfDown(-Player.posY + 1);
		int z2 = (int) Math.roundHalfDown(-Player.posZ);

		while (true) {
			try {
				SideBlock2 = World.getBlockAtPos(x2, y2, z2);
				if (SideBlock2 == null) {
					x2--;
					if (x2 <= -Player.posX - 10) {
						break;
					}
					continue;
				}
				break;
			} catch (Exception e) {
				x2--;
				if (x2 <= -Player.posX - 10) {
					break;
				}
			}
		}

		Block[] DownBlocks = { SideBlock1, SideBlock2 };
		for (Block DownBlock : DownBlocks) {
			if (DownBlock != null) {
				if (Player.getEntityHitBox().checkCollsionWithBlock(DownBlock, i)) {
					Player.posX = Player.OldposX;
				}
			}
			i++;
		}
		i = 0;
		Block SideBlock3 = null;

		Block SideBlock4 = null;
		/*
		 * Block DownBlock3 = null; Block DownBlock4 = null; Block DownBlock5 = null;
		 * Block DownBlock6 = null; Block DownBlock7 = null; Block DownBlock8 = null;
		 * Block DownBlock9 = null;
		 */
		int x3 = (int) Math.roundHalfDown(-Player.posX);
		int y3 = (int) Math.roundHalfDown(-Player.posY);
		int z3 = (int) Math.roundHalfDown(-Player.posZ);
		while (true) {
			try {
				SideBlock3 = World.getBlockAtPos(x3, y3, z3);

				break;
			} catch (Exception e) {
				x3++;
				if (x3 <= -Player.posX + 10) {
					break;
				}
			}
		}
		int x4 = (int) Math.roundHalfDown(-Player.posX);
		int y4 = (int) Math.roundHalfDown(-Player.posY + 1);
		int z4 = (int) Math.roundHalfDown(-Player.posZ);
		while (true) {
			try {
				SideBlock4 = World.getBlockAtPos(x4, y4, z4);
				if (SideBlock4 == null) {
					x4++;
					if (x4 <= -Player.posX + 10) {
						break;
					}
					continue;
				}
				break;
			} catch (Exception e) {
				x4++;
				if (x4 <= -Player.posX + 10) {
					break;
				}
			}
		}

		Block[] Blocks2 = { SideBlock3, SideBlock4 };
		for (Block DownBlock : Blocks2) {
			if (DownBlock != null) {
				if (Player.getEntityHitBox().checkCollsionWithBlock(DownBlock, i)) {
					Player.posX = Player.OldposX;

				}
			}
			i++;
		}
		i = 0;

		Block SideBlock5 = null;

		Block SideBlock6 = null;
		/*
		 * Block DownBlock3 = null; Block DownBlock4 = null; Block DownBlock5 = null;
		 * Block DownBlock6 = null; Block DownBlock7 = null; Block DownBlock8 = null;
		 * Block DownBlock9 = null;
		 */
		int x5 = (int) Math.roundHalfDown(-Player.posX);
		int y5 = (int) Math.roundHalfDown(-Player.posY);
		int z5 = (int) Math.roundHalfDown(-Player.posZ);
		while (true) {
			try {
				SideBlock5 = World.getBlockAtPos(x5, y5, z5);

				break;
			} catch (Exception e) {
				z5--;
				if (z5 <= -Player.posZ - 10) {
					break;
				}
			}
		}
		int x6 = (int) Math.roundHalfDown(-Player.posX);
		int y6 = (int) Math.roundHalfDown(-Player.posY + 1);
		int z6 = (int) Math.roundHalfDown(-Player.posZ);
		while (true) {
			try {
				SideBlock6 = World.getBlockAtPos(x6, y6, z6);
				if (SideBlock6 == null) {
					z6--;
					if (z6 <= -Player.posZ - 10) {
						break;
					}
					continue;
				}
				break;
			} catch (Exception e) {
				z6--;
				if (z6 <= -Player.posZ - 10) {
					break;
				}
			}
		}

		Block[] Blocks3 = { SideBlock5, SideBlock6 };
		for (Block DownBlock : Blocks3) {
			if (DownBlock != null) {
				if (Player.getEntityHitBox().checkCollsionWithBlock(DownBlock, i)) {
					Player.posZ = Player.OldposZ;

				}
			}
			i++;
		}
		i = 0;
		Block SideBlock7 = null;

		Block SideBlock8 = null;
		/*
		 * Block DownBlock3 = null; Block DownBlock4 = null; Block DownBlock5 = null;
		 * Block DownBlock6 = null; Block DownBlock7 = null; Block DownBlock8 = null;
		 * Block DownBlock9 = null;
		 */
		int x7 = (int) Math.roundHalfDown(-Player.posX);
		int y7 = (int) Math.roundHalfDown(-Player.posY);
		int z7 = (int) Math.roundHalfDown(-Player.posZ);
		while (true) {
			try {
				SideBlock7 = World.getBlockAtPos(x7, y7, z7);

				break;
			} catch (Exception e) {
				z7++;
				if (z7 <= -Player.posZ + 10) {
					break;
				}
			}
		}
		int x8 = (int) Math.roundHalfDown(-Player.posX);
		int y8 = (int) Math.roundHalfDown(-Player.posY + 1);
		int z8 = (int) Math.roundHalfDown(-Player.posZ);
		while (true) {
			try {
				SideBlock8 = World.getBlockAtPos(x8, y8, z8);
				if (SideBlock8 == null) {
					z8++;
					if (z8 <= -Player.posZ + 10) {
						break;
					}
					continue;
				}
				break;
			} catch (Exception e) {
				z8++;
				if (z8 <= -Player.posZ + 10) {
					break;
				}
			}
		}

		Block[] Blocks4 = { SideBlock7, SideBlock8 };
		for (Block DownBlock : Blocks4) {
			if (DownBlock != null) {
				if (Player.getEntityHitBox().checkCollsionWithBlock(DownBlock, i)) {
					Player.posZ = Player.OldposZ;

				}
			}
			i++;
		}
		i = 0;
	}

	public static void setupNextFrame() {
		// GL11.glCullFace(GL11.GL_BACK);
		++WindowVariables.test;
		if (WindowVariables.test > 10) {
			WindowVariables.test = 1;
		}

		float yawRadian = (float) ((Player.rotX) * (Math.PI / 180));
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glClearColor(0.529f * LightingController.worldLighting, 0.808f * LightingController.worldLighting,
				0.922f * LightingController.worldLighting, 0.5f);

		// GL11.glClearColor(0f, 0f, 0f, 0f);
		WindowVariables.m.setLookAt(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
		GL11.glLoadIdentity();
		drawCrossHair();
		Player.drawHotBars();
		GL11.glRotated(10, 1, 0, 0);
		drawHand(+0.2f, -0.18f, 0f);
		GL11.glRotated(-10, 1, 0, 0);

		Text.DrawText(-0.15f, 0.08f, -0.2f, 0.5f, String.valueOf(-Player.posX));
		Text.DrawText(-0.15f, 0.07f, -0.2f, 0.5f, String.valueOf(-Player.posY));
		Text.DrawText(-0.15f, 0.06f, -0.2f, 0.5f, String.valueOf(-Player.posZ));
		Text.DrawText(-0.15f, 0.05f, -0.2f, 0.5f, String.valueOf(-Player.rotX));
		Text.DrawText(-0.15f, 0.04f, -0.2f, 0.5f, String.valueOf(-Player.rotY));
		Text.DrawText(-0.15f, 0.03f, -0.2f, 0.5f, String.valueOf(WindowVariables.fps));

		GL11.glRotatef(Player.rotX, 0.0f, 1f, 0.0f);
		GL11.glRotatef((float) (-Player.rotY), (1 * Math.cos(yawRadian) * Math.cos(90)) / 360, 0.0f,
				(1 * Math.sin(yawRadian) * Math.cos(90)) / 360);
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GL11.glTranslatef(Player.posX, Player.posY - 1f, Player.posZ);

		Vector3f v = new Vector3f(0, 1, 0);

		float offset = 1f;

		float xd = MathUtills.floor(-Player.posX) + Player.posX;
		float yd = MathUtills.floor(-Player.posY) + Player.posY;
		float zd = MathUtills.floor(-Player.posZ) + Player.posZ;
		System.out.println("xd:" + xd);
		System.out.println("yd:" + yd);
		System.out.println("zd:" + zd);
		// System.out.println("pos X: " + Player.posX);
		// System.out.println("pos Y: " + Player.posY);
		// System.out.println("pos Z: " + Player.posZ);
		GL11.glRotatef(0, 0.0f, 1.0f, 0.0f);
		GL11.glRotatef(0, 1.0f, 0.0f, 0.0f);
		GL11.glRotatef(0, 0.0f, 0.0f, 1.0f);
		GLFW.glfwSetCursorPos(WindowVariables.window, (WindowVariables.width + 1f) / 2f,
				(WindowVariables.height + 1f) / 2f);
	}

	private static void drawCrossHair() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(0.005f, 0.0003f, -0.2f);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(-0.005f, 0.0003f, -0.2f);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-0.005f, -0.0003f, -0.2f);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(0.005f, -0.0003f, -0.2f);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(0.0003f, 0.005f, -0.2f);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(0.0003f, -0.005f, -0.2f);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-0.0003f, -0.005f, -0.2f);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-0.0003f, 0.005f, -0.2f);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnd();

	}

	public static void FPS(int fps, long time) throws InterruptedException {
		if (time < 1000 / fps) {
			Thread.sleep(1000 / fps - time);
		}
	}

	public static Vector3f getBlockPlayerIsLookingAt() {

		float offset = 0.1f;
		float pitchRadian = (float) (Player.rotY * (Math.PI / 180)); // X rotation
		float yawRadian = (float) (Player.rotX * (Math.PI / 180)); // Y rotation
		float x2 = -Player.posX;
		float y2 = -Player.posY + 1;
		float z2 = -Player.posZ;
		int tn = 0;

		while (true) {
			Vector3f pbp = MathUtills.PlayerPositionToBlockPosition(x2, y2, z2);
			try {
				Block b = World.getBlockAtPos((int) pbp.x, (int) pbp.y, (int) pbp.z);
				if (b == null) {
					x2 += offset * Math.sin(yawRadian) * Math.cos(pitchRadian);
					y2 += offset * -Math.sin(pitchRadian);
					z2 -= (offset * Math.cos(yawRadian) * Math.cos(pitchRadian));
					tn++;
					if (tn > 2000) {
						x2 = 0;
						y2 = 0;
						z2 = 0;
						break;
					}
					continue;
				}
				x2 = pbp.x;
				y2 = pbp.y;
				z2 = pbp.z;
				break;
			} catch (Exception e) {

				x2 += offset * Math.sin(yawRadian) * Math.cos(pitchRadian);
				y2 += offset * -Math.sin(pitchRadian);
				z2 -= (offset * Math.cos(yawRadian) * Math.cos(pitchRadian));
				tn++;
				if (tn > 2000) {
					x2 = 0;
					y2 = 0;
					z2 = 0;
					break;
				}
			}
		}

		return new Vector3f((int) x2, (int) y2, (int) z2);

	}

	private static void drawHand(float x, float y, float z) {
		GL11.glColor3f((210.0f / 255.0f) * LightingController.worldLighting + (0.3f * ((210.0f / 255.0f))),
				(180.0f / 255.0f) * LightingController.worldLighting + (0.3f * (180.0f / 255.0f)),
				(140.0f / 255.0f) * LightingController.worldLighting + (0.3f * (140.0f / 255.0f)));

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.125f / 4.0f, y + 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y + 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y - 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y - 0.125f / 4.0f, z - 0.5f);
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.125f / 4.0f, y + 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y + 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y - 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y - 0.125f / 4.0f, z + 0.5f);
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x - 0.125f / 4.0f, y + 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y - 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y - 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y + 0.125f / 4.0f, z - 0.5f);
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.125f / 4.0f, y + 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y - 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y - 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y + 0.125f / 4.0f, z - 0.5f);
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.125f / 4.0f, y - 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y - 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y - 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y - 0.125f / 4.0f, z - 0.5f);
		GL11.glEnd();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(x + 0.125f / 4.0f, y + 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y + 0.125f / 4.0f, z + 0.5f);
		GL11.glVertex3f(x - 0.125f / 4.0f, y + 0.125f / 4.0f, z - 0.5f);
		GL11.glVertex3f(x + 0.125f / 4.0f, y + 0.125f / 4.0f, z - 0.5f);
		GL11.glEnd();
	}

}
