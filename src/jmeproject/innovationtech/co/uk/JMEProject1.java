package jmeproject.innovationtech.co.uk;

import android.os.Bundle;
import com.jme3.app.AndroidHarness;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;

public class JMEProject1 extends AndroidHarness {
 
    Game game;
 
    @Override
    protected void onResume() {
     super.onResume();
}
 
    @Override
    protected void onStop() {
     super.onStop();
    } 
 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	Bundle bundle = getIntent().getExtras();
        appClass = bundle.getString("APPCLASSNAME"); 
   	    appClass = "jmeproject.innovationtech.co.uk."+bundle.getString("APPCLASSNAME");
   	    
   	    if (bundle.getString("DISABLEMOUSE").equals("YES")){
   	    	mouseEventsEnabled=false;	
   	    };
   	 
        eglConfigType = ConfigType.BEST;

       //mouseEventsEnabled=false;
       exitDialogTitle = "Exit?";
       exitDialogMessage = "Press Yes";
       eglConfigVerboseLogging = false;
        
        super.onCreate(savedInstanceState);
        //game=(Game) getJmeApplication();
    }
 
    
 static {
        System.loadLibrary("bulletjme");
    }
}