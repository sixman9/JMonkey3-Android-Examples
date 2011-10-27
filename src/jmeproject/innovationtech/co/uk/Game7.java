package jmeproject.innovationtech.co.uk;

import android.util.Log;
import com.jme3.app.SimpleApplication;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.ChaseCamera;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.TouchEvent;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.util.TangentBinormalGenerator;

public class Game7 extends SimpleApplication implements TouchListener {
 
        Material mat;

        private ChaseCamera chaser;
        
        Geometry geom;
        
        float x;
        float y;
        
        float xp0;
        float yp0;
        float xp1;
        float yp1;
        float midx;
        float midy;

        float movedistance;
        float olddistance;

        boolean dragging=false;
 
  @Override
  public void simpleInitApp() {
 
    initKeys();       // load custom key mappings
 
    settings.setHeight(480);
    settings.setWidth(800);
 
    DirectionalLight sun = new DirectionalLight();
    sun.setColor(ColorRGBA.White);
    rootNode.addLight(sun);
    
    
    Sphere rock = new Sphere(32,32, 2f);
    Geometry shiny_rock = new Geometry("Shiny rock", rock);
    rock.setTextureMode(Sphere.TextureMode.Projected); // better quality on spheres
    TangentBinormalGenerator.generate(rock);           // for lighting effect
    Material mat_lit = new Material(
        assetManager, "Common/MatDefs/Light/Lighting.j3md");
    mat_lit.setTexture("m_DiffuseMap",
        assetManager.loadTexture("Textures/Terrain/Pond/Pond.png"));
    mat_lit.setTexture("m_NormalMap",
        assetManager.loadTexture("Textures/Terrain/Pond/Pond_normal.png"));
    mat_lit.setFloat("m_Shininess", 5f); // [0,128]
    shiny_rock.setMaterial(mat_lit);
    rootNode.attachChild(shiny_rock);
    
  //movable boxes
    for (int i = 0; i < 5; i++) {
        Box box = new Box(1f, 1f, 1f);
        Geometry boxGeometry = new Geometry("Box", box);
        boxGeometry.setMaterial(mat_lit);
        for (int i2 = 0; i2 < 5; i2++) {
        boxGeometry.setLocalTranslation(i*2, i2, -3);
        //Log.e("","Box created");
        }
        
        boxGeometry.addControl(new RigidBodyControl(2));
        rootNode.attachChild(boxGeometry);
    }
    
    //flyCam.setEnabled(false);
    chaser = new ChaseCamera(cam, shiny_rock);
    chaser.registerWithInput(inputManager);
    chaser.setSmoothMotion(true);
    chaser.setMaxDistance(50);
    chaser.setMinDistance(5);
    chaser.setDefaultDistance(50);
    chaser.setRotationSensitivity(10f);
    chaser.setInvertHorizontalAxis(true);
  }
 
  /** Declaring the "Shoot" action and mapping to its triggers. */
  private void initKeys() {
 
    inputManager.addMapping("Touch", new TouchTrigger(0));
    inputManager.addListener(this, new String[]{"Touch"});
  }
 

 @Override
    public void onTouch(String binding, TouchEvent evt, float tpf) {

        
        x = evt.getX();
        y = evt.getY();

        Log.e("","ALL "+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());
 
        switch(evt.getType())
        {
            case MOVE:

            	if (evt.getPointerId()==0){
            		//Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());          	    	
            	xp0=x;
            	yp0=y;
            	}
            	if (evt.getPointerId()==1){
            		//Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());
            	xp1=x;
            	yp1=y;
            	}
            	
                break;
 
            case LONGPRESSED:
            	
                break;
 
            case TAP:
 
                break;
 
            case DOWN:

           	//Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());

                break;
            case SCALE_START:
            	 //Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());
             	olddistance = evt.getScaleFactor();
 	 
                break;
            case SCALE_MOVE:
            	
            	dragging=true;

            	float deltadistance= evt.getScaleFactor();
            	movedistance = deltadistance-olddistance;

            	//Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());
            	//Log.e("","Movedistance="+movedistance);
            	chaser.onAnalog("ChaseCamZoomIn", movedistance, tpf);
            	
                break;
                
            case SCALE_END:
            	
            	//Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());
           	 
            	dragging=false;
            	
                break;
                
 
            case UP:
                    Log.e("",""+evt.getType()+" x="+x+"y="+y+" scalefactor="+evt.getScaleFactor()+" ScaleSpan="+evt.getScaleSpan()+" Pointid="+evt.getPointerId());

                break;
 
            case FLING:

            	float valuex=evt.getDeltaX()*0.005f;
            	float valuey=evt.getDeltaY()*0.005f;

            	//Log.e("",""+evt.getType()+" x="+valuex+"y="+valuey);

            	chaser.onAnalog("ChaseCamMoveRight", valuex, tpf);
            	chaser.onAnalog("ChaseCamUp", valuey, tpf);
            	
                break;

 
           default:
 
               break;
        }

 
    }
 
 public void simpleUpdate(float tpf) {
 
 }
 
}