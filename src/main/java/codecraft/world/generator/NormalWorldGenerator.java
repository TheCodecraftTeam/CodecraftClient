package codecraft.world.generator;

import codecraft.world.Block;
import codecraft.world.Chunk;
import codecraft.world.blocks.BlockDirt;
import codecraft.world.blocks.BlockGrass;
import codecraft.world.blocks.BlockStone;

public class NormalWorldGenerator {
	public static Chunk[][] generateWorld() {
		Gen g = new Gen();
		Chunk[][] generatedChunks = new Chunk[32][32];
		 
		  
		  int cx = 0;
		  int cy = 0;
		  int cz = 0;
		  int ch = 0;
		
		  int i;
		  for (int i2 = 0; i2 < 32*32; i2++) {

			  Block Blocks[][][] = new Block[16][256][16];

			 int x = 0;
			 int y = 0;
			 int z = 0;
			 int height = (int) g.generateHeight(x + (cx*16), z+ (cz*16)) + 50;
			 for(int count = 9; count > 0; count--) {
				 if(height % count == 0) {
					 System.out.println(count);
				 }
			  }
			 for ( i = 0; i < 16 * 256 * 16; i++) {


			
				


				
	if(y < height -5) {
		Blocks[x][y][z] = new BlockStone(x, y, z,cx,cz);
	}
	if(y >= height - 5 && y < height) {
		Blocks[x][y][z] = new BlockDirt(x, y, z,cx,cz);
	}
	if(y==height) {
				Blocks[x][y][z] = new BlockGrass(x, y, z,cx,cz);
	}
				
				y++;


				if (y > height) {
				   y = 0;
				   x++;
				   if (x > 15) {
					   
					   x = 0;
					   z++;
					   
					   
					   
					}
				   if (z > 15) {
					   break;
					}else {
						height = (int) g.generateHeight(x + (cx*16), z+ (cz*16))+50;
						for(int count = 9; count > 0; count--) {
							 if(height % count == 0) {
								// System.out.println(count);
							 }
						  }
					}
				   
				}


				
				


			 }


			 generatedChunks[cx][cz] = new Chunk( cx, cz, Blocks);
			 cy++;

			 if (cy > 0) {
				cy = 0;
				cx++;
				if (cx > 31) {
					cx = 0;
					cz++;
				 }
				
			 }
			
			 //System.out.println(cz);
		  }
		return generatedChunks;
		
	}
}
