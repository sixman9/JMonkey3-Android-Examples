package jmeproject.innovationtech.co.uk;

import com.jme3.app.SimpleApplication;
import com.jme3.effect.ParticleEmitter;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.effect.ParticleMesh.Type; 

public class Game2 extends SimpleApplication {
	 
    private ParticleEmitter fire;
    private ParticleEmitter debris;
    private float angle = 0;
   
    
    @Override
    public void simpleInitApp() {

    	fire = new ParticleEmitter("Emitter", Type.Triangle, 30);
        Material mat_red = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/flame.png"));
        fire.setMaterial(mat_red);
        fire.setImagesX(2); fire.setImagesY(2); // 2x2 texture animation
        fire.setEndColor(  new ColorRGBA(1f, 0f, 0f, 1f));   // red
        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow

        fire.setStartSize(1.5f);
        fire.setEndSize(0.1f);

        fire.setLowLife(0.5f);
        fire.setHighLife(3f);

        rootNode.attachChild(fire);

        debris = new ParticleEmitter("Debris", Type.Triangle, 10);
        Material debris_mat = new Material(assetManager, "Common/MatDefs/Misc/Particle.j3md");
        debris_mat.setTexture("Texture", assetManager.loadTexture("Effects/Explosion/Debris.png"));
        debris.setMaterial(debris_mat);
        debris.setImagesX(3); debris.setImagesY(3); // 3x3 texture animation
        debris.setRotateSpeed(4);
        debris.setSelectRandomImage(true);

        debris.setStartColor(new ColorRGBA(1f, 1f, 1f, 1f));
 
        rootNode.attachChild(debris);
        debris.emitAllParticles();
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        angle += tpf;
        angle %= FastMath.TWO_PI;
        float x = FastMath.cos(angle) * 2;
        float y = FastMath.sin(angle) * 2;
        fire.setLocalTranslation(x, 0, y);
    }
}