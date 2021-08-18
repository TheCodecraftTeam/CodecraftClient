package codecraft.renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class VBO {
	public static int vboID;
	public static int iboID;
	public int vSize = 0;
	public int nSize = 0;
	public int cSize = 0;
	public int tSize =0;
	private int iLength = 0;
public VBO() {
	vboID = GL15.glGenBuffers();
	iboID = GL15.glGenBuffers();
}

public void setVBOData(float[] vertices, int[] indices, float[]  texCords, float[] colors, float[] normals) {
    vSize = vertices.length;
	nSize = indices.length;
	cSize = texCords.length;
	tSize = normals.length;
	iLength = indices.length;

	// copy vertex attribs data to VBO
	
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
	GL15.nglBufferData(GL15.GL_ARRAY_BUFFER, vSize+nSize+cSize+tSize, 0, GL15.GL_STATIC_DRAW);
	// reserve space
	GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0 ,vertices);                  // copy verts at offset 0
	GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, vSize, normals);               // copy norms after verts
	GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, vSize+nSize, colors);          // copy cols after norms
	GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, vSize+nSize+cSize, texCords); // copy texs after cols
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

	// copy index data to VBO
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, iboID);
	
	GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);

}

public void Draw() {
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, iboID);

	// enable vertex arrays
	GL15.glEnableClientState(GL15.GL_VERTEX_ARRAY);
	GL15.glEnableClientState(GL15.GL_NORMAL_ARRAY);
	GL15.glEnableClientState(GL15.GL_COLOR_ARRAY);
	GL15.glEnableClientState(GL15.GL_TEXTURE_COORD_ARRAY);

	int nOffset = vSize;
	int cOffset = nOffset + nSize;
	int tOffset = cOffset + cSize;

	// specify vertex arrays with their offsets
	GL15.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);
	GL15.glNormalPointer(GL11.GL_FLOAT, 0, nOffset);
	GL15.glColorPointer(3, GL11.GL_FLOAT, 0, cOffset);
	GL15.glTexCoordPointer(2, GL11.GL_FLOAT, 0, tOffset);

	// finally draw a cube with glDrawElements()
	GL15.glDrawElements(GL11.GL_TRIANGLES,            // primitive type
	               iLength,                      // # of indices
	               GL11.GL_UNSIGNED_INT,         // data type
	               0);               // offset to indices

	// disable vertex arrays
	GL15.glDisableClientState(GL15.GL_VERTEX_ARRAY);
	GL15.glDisableClientState(GL15.GL_NORMAL_ARRAY);
	GL15.glDisableClientState(GL15.GL_COLOR_ARRAY);
	GL15.glDisableClientState(GL15.GL_TEXTURE_COORD_ARRAY);

	// unbind VBOs
	GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
}
}
