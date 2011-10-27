package jmeproject.innovationtech.co.uk;

import java.util.ArrayList;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jme3.app.AndroidHarness;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;

	public class JMEProject3 extends AndroidHarness implements SensorEventListener{
		 
	    private SensorManager sensorManager = null;
	 
	    Game8 game;
	 
	    @Override
	    protected void onResume() {
	     super.onResume();
	     // Register this class as a listener for the accelerometer sensor
	     sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	     // ...and the orientation sensor
	     sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
	    }
	 
	    @Override
	    protected void onStop() {
	     // Unregister the listener
	     sensorManager.unregisterListener(this);
	     super.onStop();
	    } 
	 
	    @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	    }
	 
	    public JMEProject3() {
	    	appClass = "jmeproject.innovationtech.co.uk.Game8";
	        eglConfigType = ConfigType.BEST;
	 
	        mouseEventsEnabled=false;
	        exitDialogTitle = "Exit?";
	        exitDialogMessage = "Press Yes";
	        eglConfigVerboseLogging = false;
	    }
	 
	    static {
	        System.loadLibrary("bulletjme");
	    }
	 
	    @Override
	    public void onAccuracyChanged(Sensor arg0, int arg1) {
	        // TODO Auto-generated method stub
	    }
	 
	    @Override
	    public void onSensorChanged(SensorEvent sensorEvent) {
	        // wait for app to be up before firing in sensors
	        if (game==null){
	        game=(Game8) getJmeApplication();
	        return;
	        }
	         if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
	              Log.e("","Sensor 1="+Float.toString(sensorEvent.values[1]));     
	 
	        if (sensorEvent.values[0]>-0.4&&sensorEvent.values[0]<0.4)
	        {
	            game.movePlayer(98,sensorEvent.values[0]);

	        }
	        if (sensorEvent.values[1]<8&&sensorEvent.values[1]>6)
	        {
	            game.movePlayer(99,sensorEvent.values[1]);

	        }
	        if (sensorEvent.values[0]>0.4){
	            game.movePlayer(1,sensorEvent.values[0]);

	        }
	 
	        if (sensorEvent.values[0]<-0.4){
	            game.movePlayer(2,sensorEvent.values[0]);
	        }
	        if (sensorEvent.values[1]<6){
 
	            game.movePlayer(3,sensorEvent.values[1]-6);
	        }
	        if (sensorEvent.values[1]>8){
	 
	            game.movePlayer(4,sensorEvent.values[1]-8);

	        }
	         }
	    }
	}