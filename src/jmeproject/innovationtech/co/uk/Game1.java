package jmeproject.innovationtech.co.uk;

import android.util.Log;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.SphereCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.effect.ParticleEmitter;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.TouchEvent;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Renderer;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.scene.shape.Sphere;
import com.jme3.scene.shape.Sphere.TextureMode;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.ui.Picture;

import com.jme3.effect.ParticleMesh;
 
// basic navigation with buttons
// shoot ball by long press
// loads physics

public class Game1 extends SimpleApplication implements TouchListener,AnimEventListener,PhysicsCollisionListener {
 
      Material mat;
        Material mat2;
        Material mat3;
        private static Sphere bullet;
        private static SphereCollisionShape bulletCollisionShape;
        static Node enemy;
        private static AnimChannel channel;
        private static AnimControl control;
 
     private CharacterControl player;
 
    private boolean left = false, right = false, up = false, down = false, hudbutton=false;
    private Vector3f walkDirection = new Vector3f();
 
    private BulletAppState bulletAppState = new BulletAppState();
 
  @Override
  public void simpleInitApp() {
 
      bulletAppState = new BulletAppState();
      stateManager.attach(bulletAppState);
 
      bullet = new Sphere(32, 32, 0.4f, true, false);
      bullet.setTextureMode(TextureMode.Projected);
      bulletCollisionShape = new SphereCollisionShape(0.4f);
 
      initMaterial();
 
    initKeys();       // load custom key mappings
 
    settings.setHeight(480);
    settings.setWidth(800);
 
    // create my picture buttons
    Picture pic = new Picture("HUD Picture"); // up
    pic.setImage(assetManager, "assets/button1.png", true);
    pic.setWidth(80f);
    pic.setHeight(80f);
    pic.setPosition(80f, 80f);
    guiNode.attachChild(pic);
 
    Picture pic2 = new Picture("HUD Picture"); // down
    pic2.setImage(assetManager, "assets/button2.png", true);
    pic2.setWidth(80f);
    pic2.setHeight(80f);
    pic2.setPosition(80f, 0f);
    guiNode.attachChild(pic2);
 
    Picture pic3 = new Picture("HUD Picture");// left
    pic3.setImage(assetManager, "assets/button3.png", true);
    pic3.setWidth(80f);
    pic3.setHeight(80f);
    pic3.setPosition(0f, 40f);
    guiNode.attachChild(pic3);
 
    Picture pic4 = new Picture("HUD Picture");// right
    pic4.setImage(assetManager, "assets/button4.png", true);
    pic4.setWidth(80f);
    pic4.setHeight(80f);
    pic4.setPosition(160f, 40f);
    guiNode.attachChild(pic4);
    
    Picture pic5 = new Picture("HUD Picture");// action
    pic5.setImage(assetManager, "assets/button5.png", true);
    pic5.setWidth(80f);
    pic5.setHeight(80f);
    pic5.setPosition(360f, 40f);
    guiNode.attachChild(pic5);
 
    DirectionalLight dl = new DirectionalLight();
    dl.setDirection(new Vector3f(-0.1f, -1f, -1).normalizeLocal());
    rootNode.addLight(dl);
 
    createPhysicsTestWorld(rootNode, assetManager, bulletAppState.getPhysicsSpace());
 
    CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f, 6f, 1);
    player = new CharacterControl(capsuleShape, 0.05f);
    player.setJumpSpeed(20);
    player.setFallSpeed(30);
    player.setGravity(30);
    player.setPhysicsLocation(new Vector3f(0, 10, 0));
    
 
    bulletAppState.getPhysicsSpace().add(player);
    
    bulletAppState.getPhysicsSpace().addCollisionListener(this);
    
    
 
  }
 
  /** Declaring the "Shoot" action and mapping to its triggers. */
  private void initKeys() {
 
    inputManager.addMapping("Touch", new TouchTrigger(0));
    inputManager.addListener(this, new String[]{"Touch"});
  }
  public void movePlayer(int direction){
 
	  return;
	  /*
      switch (direction){
 
          case 1: // go left
              left=true;
              right=false;
              break;
          case 2: // go right
              right=true;
              left=false;
              break;
          case 3: // go up
              up=true;
              down=false;
              break;
          case 4: // go down
              down=true;
              up=false;
              break;
 
          case 98: // stop left or right
              right=false;
              left=false;
              break;
          case 99: // stop up or down
              up=false;
              down=false;
              break;
 
          default:
              break;
      }
      */
  }
 
  public static void createPhysicsTestWorld(Node rootNode, AssetManager assetManager, PhysicsSpace space) {
      AmbientLight light = new AmbientLight();
      light.setColor(ColorRGBA.LightGray);
      rootNode.addLight(light);
 
      Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      material.setTexture("ColorMap", assetManager.loadTexture("Textures/Terrain/Pond/Pond.png"));
 
      Box floorBox = new Box(140, 0.25f, 140);
      Geometry floorGeometry = new Geometry("Floor", floorBox);
      floorGeometry.setMaterial(material);
      floorGeometry.setLocalTranslation(0, -5, 0);
//      Plane plane = new Plane();
//      plane.setOriginNormal(new Vector3f(0, 0.25f, 0), Vector3f.UNIT_Y);
//      floorGeometry.addControl(new RigidBodyControl(new PlaneCollisionShape(plane), 0));
      floorGeometry.addControl(new RigidBodyControl(0));
      rootNode.attachChild(floorGeometry);
      space.add(floorGeometry);
 
   
      
      //movable boxes
      for (int i = 0; i < 5; i++) {
          Box box = new Box(1f, 1f, 1f);
          Geometry boxGeometry = new Geometry("Box", box);
          boxGeometry.setMaterial(material);
          for (int i2 = 0; i2 < 5; i2++) {
          boxGeometry.setLocalTranslation(i*2, i2, -3);
          //Log.e("","Box created");
          }
          
          boxGeometry.addControl(new RigidBodyControl(2));
          rootNode.attachChild(boxGeometry);
          space.add(boxGeometry);
      }
 
  }
 
  public void initMaterial() {
      mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      TextureKey key = new TextureKey("Textures/Terrain/BrickWall/BrickWall.jpg");
      key.setGenerateMips(true);
      Texture tex = assetManager.loadTexture(key);
      tex.setWrap(WrapMode.Repeat);
      mat.setTexture("ColorMap", tex);
 
      mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      TextureKey key2 = new TextureKey("Textures/Terrain/Rock/Rock.PNG");
      key2.setGenerateMips(true);
      Texture tex2 = assetManager.loadTexture(key2);
      mat2.setTexture("ColorMap", tex2);
 
      mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
      TextureKey key3 = new TextureKey("Textures/Terrain/Pond/Pond.jpg");
      key3.setGenerateMips(true);
      Texture tex3 = assetManager.loadTexture(key3);
      tex3.setWrap(WrapMode.Repeat);
      mat3.setTexture("ColorMap", tex3);
  }
 
  public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (animName.equals("Walk")) {
          channel.setAnim("stand", 0.50f);
          channel.setLoopMode(LoopMode.DontLoop);
          channel.setSpeed(1f);
        }
        if (animName.equals("pull")) {
              channel.setAnim("stand", 0.50f);
              channel.setLoopMode(LoopMode.DontLoop);
              channel.setSpeed(1f);
            }
        if (animName.equals("Dodge")) {
              channel.setAnim("stand", 0.50f);
              channel.setLoopMode(LoopMode.DontLoop);
              channel.setSpeed(1f);
            }
      }
 
 @Override
    public void onTouch(String binding, TouchEvent evt, float tpf) {
        float x;
        float y;
        x = evt.getX();
        y = evt.getY();
        switch(evt.getType())
        {
            case MOVE:
            	
                
                if (x>80&&x<160&&y>80&&y<160){
                  //Log.e("","You touched " + x + " " + y + " which is button 1!"); //up
 
                  up=true;
                }
                if (x>80&&x<160&&y>0&&y<80){
                    //  Log.e("","You touched " + x + " " + y + " which is button 2!"); //down
                     down=true;
  
                }
                if (x>0&&x<80&&y>40&&y<120){
                      //Log.e("","You touched " + x + " " + y + " which is button 3!"); //left
                     left=true;
 
                }
 
                if (x>160&&x<240&&y>40&&y<120){
                     // Log.e("","You touched " + x + " " + y + " which is button 4!");//right
 
                right=true;
 
                }
                
                if (x>360&&x<440&&y>40&&y<120){
                	// Log.e("","You touched " + x + " " + y + " which is button 5!");//action
                	Geometry bulletg = new Geometry("bullet", bullet);
                    bulletg.setMaterial(mat2);
                    bulletg.setShadowMode(ShadowMode.CastAndReceive);
                    bulletg.setLocalTranslation(cam.getLocation());
                    bulletg.setLocalTranslation(bulletg.getLocalTranslation().x, bulletg.getLocalTranslation().y, bulletg.getLocalTranslation().z-20f);
     
                    SphereCollisionShape bulletCollisionShape = new SphereCollisionShape(0.4f);
                    RigidBodyControl bulletNode = new RigidBodyControl(bulletCollisionShape, 1);
                    bulletNode.setLinearVelocity(cam.getDirection().mult(25));
                    bulletg.addControl(bulletNode);
                    rootNode.attachChild(bulletg);
                    bulletAppState.getPhysicsSpace().add(bulletNode);
                    
                	}
 
                break;
 
            case LONGPRESSED:
            	
  
                break;
 
            case TAP:
 
                break;
 
            case DOWN:
 
            	break;

 
            case UP:
 
                up=false;
                down=false;
                right=false;
                left=false;
 
                break;
 
            case FLING:
 
                break;
 
           default:
 
               break;
        }
//        Log.e("","Event Type " + evt.getType() + " x="+x+" y="+y);
        evt.setConsumed();
 
    }
 
 public void simpleUpdate(float tpf) {
 
        Vector3f camDir = cam.getDirection().clone().multLocal(0.6f);
        Vector3f camLeft = cam.getLeft().clone().multLocal(0.4f);
 
        walkDirection.set(0, 0, 0);
        if (left)  { walkDirection.addLocal(camLeft); }
        if (right) { walkDirection.addLocal(camLeft.negate()); }
        if (up)    { walkDirection.addLocal(camDir); }
        if (down)  { walkDirection.addLocal(camDir.negate()); }
        player.setWalkDirection(walkDirection);
        cam.setLocation(player.getPhysicsLocation());
 }
 
@Override
public void onAnimChange(AnimControl arg0, AnimChannel arg1, String arg2) {
    // TODO Auto-generated method stub
 
}

@Override
public void collision(PhysicsCollisionEvent event) {
	//Log.e("",""+event.getNodeA().getName());
	
}
}