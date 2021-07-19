package codecraft.ui;

import java.util.HashMap;

import codecraft.ui.charModels.C0;
import codecraft.ui.charModels.C1;
import codecraft.ui.charModels.C2;
import codecraft.ui.charModels.C3;
import codecraft.ui.charModels.C4;
import codecraft.ui.charModels.C5;
import codecraft.ui.charModels.C6;
import codecraft.ui.charModels.C7;
import codecraft.ui.charModels.C8;
import codecraft.ui.charModels.C9;
import codecraft.ui.charModels.CPeriod;

public class Text {
private static HashMap<Character, CharModel> characters = new HashMap<>();
static {
	characters.put('0', new C0());
	characters.put('1', new C1());
	characters.put('2', new C2());
	characters.put('3', new C3());
	characters.put('4', new C4());
	characters.put('5', new C5());
	characters.put('6', new C6());
	characters.put('7', new C7());
	characters.put('8', new C8());
	characters.put('9', new C9());
	characters.put('.', new CPeriod());
}
public static void DrawText(float x, float y ,float z,float size,String text) {
	for(int i = 0; i < text.length(); i++) {
		try {
		characters.get(text.charAt(i)).Draw(x + (i * (size*0.0125f)), y,z, size);
		}catch(Exception e){
			
		}
	}
}
}
