package codecraft.entity;


import codecraft.player.Player;
import codecraft.world.Block;
public class StaticHitBox {
	
	private float x1;
	private float y1;
	private float z1;

	private float x2;
	private float y2;
	private float z2;

	private float x3;
	private float y3;
	private float z3;

	private float x4;
	private float y4;
	private float z4;

	private float x5;
	private float y5;
	private float z5;

	private float x6;
	private float y6;
	private float z6;

	private float x7;
	private float y7;
	private float z7;

	private float x8;
	private float y8;
	private float z8;



	public StaticHitBox(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4,
			float y4, float z4, float x5, float y5, float z5, float x6, float y6, float z6, float x7, float y7, float z7,
			float x8, float y8, float z8) {
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.x3 = x3;
		this.y3 = y3;
		this.z3 = z3;
		this.x4 = x4;
		this.y4 = y4;
		this.z4 = z4;
		this.x5 = x5;
		this.y5 = y5;
		this.z5 = z5;
		this.x6 = x6;
		this.y6 = y6;
		this.z6 = z6;
		this.x7 = x7;
		this.y7 = y7;
		this.z7 = z7;
		this.x8 = x8;
		this.y8 = y8;
		this.z8 = z8;
	}

	public static boolean checkPointIntersectionWithBlock(float x, float y, float z, Block block){
		return ((x >=-block.getGlobalX()  -1  && x <= block.getGlobalX() + 1 ) &&
		         (y >=block.getGlobalY() - 5 && y <= block.getGlobalY()+2 ) &&
		         (z >= block.getGlobalZ() - 0.5  && z <= block.getGlobalZ() + 0.5 ));
	}

	public boolean checkCollsionWithBlock(Block block,float x, float y, float z) {
		return (checkPointIntersectionWithBlock(x1 - x,y1- y - 2  - 0.5f,z1- z,block)||
				checkPointIntersectionWithBlock(x2- x,y2- y - 2- 0.5f,z2- z,block)||
				checkPointIntersectionWithBlock(x3- x,y3- y - 2- 0.5f,z3- z,block)||
				checkPointIntersectionWithBlock(x4- x,y4- y - 2- 0.5f,z4- z,block)||
				checkPointIntersectionWithBlock(x5- x,y5- y - 2- 0.5f,z5- z,block)||
				checkPointIntersectionWithBlock(x6- x,y6- y - 2- 0.5f,z6- z,block)||
				checkPointIntersectionWithBlock(x7- x,y7- y - 2- 0.5f,z7- z,block)||
				checkPointIntersectionWithBlock(x8- x,y8- y - 2- 0.5f,z8- z,block));
		 
	}
	

}
