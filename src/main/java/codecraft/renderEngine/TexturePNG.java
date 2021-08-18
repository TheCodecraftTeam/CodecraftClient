package codecraft.renderEngine;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import de.matthiasmann.twl.utils.PNGDecoder;
import javassist.bytecode.stackmap.TypeData.ClassName;

public class TexturePNG {
private int texID;
public TexturePNG() {
	
}

public void loadTexture(String fileName) throws IOException{

    //load png file
    PNGDecoder decoder = new PNGDecoder(ClassName.class.getResourceAsStream(fileName));

    //create a byte buffer big enough to store RGBA values
    ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());

    //decode
    decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);

    //flip the buffer so its ready to read
    buffer.flip();

    //create a texture
    int id = GL11.glGenTextures();

    //bind the texture
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

    //tell opengl how to unpack bytes
    GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);

    //set the texture parameters, can be GL_LINEAR or GL_NEAREST
    GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

    //upload texture
    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

    // Generate Mip Map
    GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);

    texID = id;
}
public void bind(){
    glBindTexture(GL_TEXTURE_2D,texID);
}

public void unbind(){
    glBindTexture(GL_TEXTURE_2D,0);
}

}
