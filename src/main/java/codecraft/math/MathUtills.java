package codecraft.math;

import org.joml.Vector3f;

public class MathUtills {

	public static Vector3f PlayerPositionToBlockPosition(float x, float y, float z) {
		String x_str = String.valueOf(x);
		String xd = "";
		boolean d = false;
		for(int i = 0; i < x_str.length(); i++) {
			if(d == false && x_str.charAt(i) != '-' && x_str.charAt(i) != '.') {
				xd += "0";
				
			}else {
				xd += x_str.charAt(i);
				if(x_str.charAt(i) != '-') {
					d = true;
				}
			}
		}
		
		String y_str = String.valueOf(y);
		String yd = "";
		boolean d2 = false;
		for(int i = 0; i < y_str.length(); i++) {
			if(d2 == false && y_str.charAt(i) != '-' &&  y_str.charAt(i) != '.') {
				yd += "0";
				
			}else {
				yd += y_str.charAt(i);	
				if(y_str.charAt(i) != '-') {
					d2 = true;
				}
			}
		}
		
		
		String z_str = String.valueOf(z);
		String zd = "";
		boolean d3 = false;
		for(int i = 0; i < z_str.length(); i++) {
			if(d3 == false && z_str.charAt(i) != '-' && z_str.charAt(i) != '.') {
				zd += "0";
				
			}
			else {
				zd += z_str.charAt(i);
				if(z_str.charAt(i) != '-') {
				d3 = true;
				}
			}
		}
		
	return new Vector3f(x - Float.valueOf(xd),y - Float.valueOf(yd),z - Float.valueOf(zd));
	}
	 public static int floor(float value) {
	        int i =(int)value;
	        if(i - value <= -0.5) {
	        	i++;
	        }
	        	return i;
	        
	    }
}
