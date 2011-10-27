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
 

public class Game4 extends SimpleApplication {

	 
    protected Geometry player;
    public boolean animate=true;

     @Override
     public void simpleInitApp() {

         Box b = new Box(Vector3f.ZERO, 1, 1, 1);
         player = new Geometry("blue cube", b);
         Material mat = new Material(assetManager,
           "Common/MatDefs/Misc/Unshaded.j3md");
         mat.setColor("Color", ColorRGBA.Blue);
         player.setMaterial(mat);
         rootNode.attachChild(player);
     }

     /* This is the update loop */
     @Override
     public void simpleUpdate(float tpf) {
         // make the player rotate
         if (animate){
         player.rotate(0, 2*tpf, 0);
         }
     }
}