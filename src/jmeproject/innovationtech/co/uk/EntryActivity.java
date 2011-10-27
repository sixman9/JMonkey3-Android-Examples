package jmeproject.innovationtech.co.uk;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class EntryActivity extends ListActivity {

	 static final String[] ITEMS = new String[] {
		   "Game","Game1"};
	 static final ArrayList<HashMap<String,String>> list = 

		 new ArrayList<HashMap<String,String>>();
	
		/** Called when the activity is first created. */

			public void onCreate(Bundle icicle) {
				super.onCreate(icicle);
				
				SimpleAdapter adapter = new SimpleAdapter(
				this,
				list,
				R.layout.custom_row_view,
				new String[] {"item","description"},
				new int[] {R.id.text1,R.id.text2}
				);
				populateList();
				setListAdapter(adapter);
			}
			
			private void populateList() {
				HashMap<String,String> temp = new HashMap<String,String>();
				temp.put("item","Game");
				temp.put("description", "A basic blue cube");
				list.add(temp);
				HashMap<String,String> temp1 = new HashMap<String,String>();
				temp1.put("item","Game1");
				temp1.put("description", "Basic HUD, launch ball, small wall");
				list.add(temp1);
				HashMap<String,String> temp2 = new HashMap<String,String>();
				temp2.put("item","Game2");
				temp2.put("description", "Moving Particle Emitter");
				list.add(temp2);
				HashMap<String,String> temp3 = new HashMap<String,String>();
				temp3.put("item","Game3");
				temp3.put("description", "Basic Rag Doll");
				list.add(temp3);
				
				HashMap<String,String> temp4 = new HashMap<String,String>();
				temp4.put("item","Game4");
				temp4.put("description", "Voice Controlled Cube");
				list.add(temp4);
				
				HashMap<String,String> temp5 = new HashMap<String,String>();
				temp5.put("item","Game5");
				temp5.put("description", "Camera Waypoints");
				list.add(temp5);
				
				HashMap<String,String> temp6 = new HashMap<String,String>();
				temp6.put("item","Game6");
				temp6.put("description", "Load Scene");
				list.add(temp6);
				
				HashMap<String,String> temp7 = new HashMap<String,String>();
				temp7.put("item","Game7");
				temp7.put("description", "Finger Camera Control");
				list.add(temp7);
				
				HashMap<String,String> temp8 = new HashMap<String,String>();
				temp8.put("item","Game8");
				temp8.put("description", "Accelerometer");
				list.add(temp8);
				
				HashMap<String,String> temp9 = new HashMap<String,String>();
				temp9.put("item","Game9");
				temp9.put("description", "On Screen Trackpad");
				list.add(temp9);
				
				HashMap<String,String> temp10 = new HashMap<String,String>();
				temp10.put("item","Game10");
				temp10.put("description", "NEW");
				list.add(temp10);
				}

			@Override
			protected void onListItemClick(ListView l, View v, int position, long id) {
				super.onListItemClick(l, v, position, id);

				HashMap<String, String> o = (HashMap<String, String>) l.getItemAtPosition(position);
			    String item=o.get("item");
			    
			    Toast.makeText(this, "You have chosen: " + " " + item, Toast.LENGTH_LONG).show();
			    Bundle bundle = new Bundle();
			    Intent myIntent = new Intent(EntryActivity.this, JMEProject1.class);
			    
			    if (item.equals("Game4")){
			    	myIntent = new Intent(EntryActivity.this, JMEProject2.class);				
			    }
			    
			    if (item.equals("Game8")){
			    	myIntent = new Intent(EntryActivity.this, JMEProject3.class);				
			    }
			
				 //Add the parameters to bundle as
			    bundle.putString("DISABLEMOUSE", "NO");
                bundle.putString("APPCLASSNAME", ""+item);
                
                if (item.equals("Game10")||item.equals("Game9")){
                	bundle.putString("DISABLEMOUSE", "YES");				
			    }
                

                //Add this bundle to the intent
                myIntent.putExtras(bundle);
                EntryActivity.this.startActivity(myIntent);

			}
}