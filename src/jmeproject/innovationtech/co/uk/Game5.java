package jmeproject.innovationtech.co.uk;

import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.cinematic.MotionPath;
import com.jme3.cinematic.events.MotionTrack;
import com.jme3.input.ChaseCamera;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl.ControlDirection;
import com.jme3.scene.shape.Box;
 

public class Game5 extends SimpleApplication {

    private MotionPath path;
    private MotionTrack cameraMotionControl;
    private ChaseCamera chaser;
    private CameraNode camNode;
    private boolean playing = false;
    
	    @Override
	    public void simpleInitApp() {
	        Box b = new Box(Vector3f.ZERO, 1, 1, 1); // create cube shape at the origin
	        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
	        Material mat = new Material(assetManager,
	          "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
	        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
	       
	        geom.setMaterial(mat);                   // set the cube's material
	        rootNode.attachChild(geom); 
 
	        Material matSoil = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
	        matSoil.setBoolean("UseMaterialColors", true);
	        matSoil.setColor("Ambient", ColorRGBA.Gray);
	        matSoil.setColor("Diffuse", ColorRGBA.Gray);
	        matSoil.setColor("Specular", ColorRGBA.Black);



	        Geometry soil = new Geometry("soil", new Box(new Vector3f(0, -1.0f, 0), 50, 1, 50));
	        soil.setMaterial(matSoil);
	        rootNode.attachChild(soil);
	        
	        cam.setLocation(new Vector3f(8.4399185f, 11.189463f, 14.267577f));
	        camNode = new CameraNode("Motion cam", cam);
	        camNode.setControlDir(ControlDirection.SpatialToCamera);
	        camNode.getControl(0).setEnabled(false);
	        path = new MotionPath();
	        path.setCycle(true);
	        path.addWayPoint(new Vector3f(20, 3, 0));
	        path.addWayPoint(new Vector3f(0, 3, 20));
	        path.addWayPoint(new Vector3f(-20, 3, 0));
	        path.addWayPoint(new Vector3f(0, 3, -20));
	        path.setCurveTension(0.83f);
	        path.enableDebugShape(assetManager, rootNode);

	        cameraMotionControl = new MotionTrack(camNode, path);
	        cameraMotionControl.setLoopMode(LoopMode.Loop);
	        //cameraMotionControl.setDuration(15f);
	        cameraMotionControl.setLookAt(geom.getWorldTranslation(), Vector3f.UNIT_Y);
	        cameraMotionControl.setDirectionType(MotionTrack.Direction.LookAt);

	        rootNode.attachChild(camNode);
	        
	        flyCam.setEnabled(false);
	        chaser = new ChaseCamera(cam, geom);
	        chaser.registerWithInput(inputManager);
	        chaser.setSmoothMotion(true);
	        chaser.setMaxDistance(50);
	        chaser.setDefaultDistance(50);
	        
	        playing = true;
            chaser.setEnabled(false);
            camNode.getControl(0).setEnabled(true);
            cameraMotionControl.play();
            
            DirectionalLight light = new DirectionalLight();
            light.setDirection(new Vector3f(0, -1, 0).normalizeLocal());
            light.setColor(ColorRGBA.White.mult(1.5f));
            rootNode.addLight(light);
}
}