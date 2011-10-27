package jmeproject.innovationtech.co.uk;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jme3.app.AndroidHarness;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;

public class JMEProject2 extends AndroidHarness {
 
	Game4 game;
    private static final int REQUEST_CODE = 1234;
 
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FrameLayout frame = (FrameLayout) findViewById(R.id.threeD_view);
        frame.addView(view);
        game=(Game4) getJmeApplication();
 
    }
    public JMEProject2() {

        appClass = "jmeproject.innovationtech.co.uk.Game4";
        eglConfigType = ConfigType.BEST;
 
        mouseEventsEnabled=false;
        exitDialogTitle = "Exit?";
        exitDialogMessage = "Press Yes";
        eglConfigVerboseLogging = false;
    }
 
    /**
     * Handle the action of the button being clicked
     */
    public void speakButtonClicked(View v)
    {
        startVoiceRecognitionActivity();
    }
 
    /**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
        startActivityForResult(intent, REQUEST_CODE);
    }
 
    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
 
            String[] strings = matches.toArray(new String[0]);
            String command=strings[0];
 
            Toast.makeText(this, "You selected: " + command, Toast.LENGTH_LONG).show();
            if (command.equals("move")){
            game.animate=true;
            }
            if (command.equals("stop")){
            game.animate=false;
            }
  }
        super.onActivityResult(requestCode, resultCode, data);
    }
}