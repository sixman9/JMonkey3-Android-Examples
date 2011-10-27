package jmeproject.innovationtech.co.uk;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
 

public class Game extends SimpleApplication {

	    @Override
	    public void simpleInitApp() {
	        Box b = new Box(Vector3f.ZERO, 1, 1, 1); // create cube shape at the origin
	        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
	        Material mat = new Material(assetManager,
	          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
	        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
	        geom.setMaterial(mat);                   // set the cube's material
	        rootNode.attachChild(geom); 
}
}