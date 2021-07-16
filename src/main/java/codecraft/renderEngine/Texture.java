package codecraft.renderEngine;

import org.lwjgl.BufferUtils;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {
    private String filepath;
    private int texID;
    private int width,height;


    public Texture(){

    }

    public void init(String filepath) {
        this.filepath = filepath;


        //Generate Texture on GPU
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        //Set texture Parameters
     
        //when shrinking, pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = stbi_load(filepath, width, height, channels, 0);

        if(image!=null){
            this.width = width.get(0);
            this.height = height.get(0);
            if(channels.get(0) == 3) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            } else if(channels.get(0) == 4) {
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            }
        } else {
            assert false : "Error: Texture couldn't be loaded from file '" + filepath + "'. Probably because of an unknown amount of color channels (must be RGB or RGBA)";
        }

        stbi_image_free(image);

    }
    public void bind(){
        glBindTexture(GL_TEXTURE_2D,texID);
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D,0);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getId(){
        return texID;
    }
}