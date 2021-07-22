package codecraft.world.lighting;

import org.joml.Vector3f;

public class Light {
private Vector3f blocksAffected[];
private Vector3f position;

public Light(Vector3f[] blocksAffected, Vector3f position) {

	this.blocksAffected = blocksAffected;
	this.position = position;
}


}
