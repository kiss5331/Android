package com.androidexample.parsejson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ParseJsonAndroidExample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parse_json_android_example);

		final TextView output 		= (TextView) findViewById(R.id.output);
		final Button bparsejson      = (Button) findViewById(R.id.bparsejson);
		
		/************  Static JSON data ***********/
		final String strJson = "{ \"Android\" :[{\"song_name\":\"Gimme Dat\",\"song_id\":\"1932\",\"artist_name\":\"Sidney Samson (Feat. Pitbull & Akon)\"},{\"song_name\":\"F-k The Money (Remix)\",\"song_id\":\"73\",\"artist_name\":\"B.o.B. (Feat. Wiz Khalifa)\"}] }";
		
		String dataToBeParsed = "Click on button to parse JSON.\n\n JSON DATA : \n\n"+strJson;
		output.setText(dataToBeParsed);
		
		/******** Listener for button click ********/
		bparsejson.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
		
			   String OutputData = "";
			   JSONObject jsonResponse;
					
		       try {
					
					/****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
					jsonResponse = new JSONObject(strJson);
					
					/***** Returns the value mapped by name if it exists and is a JSONArray. Returns null otherwise.*******/
					JSONArray jsonMainNode=jsonResponse.optJSONArray("Android");
					
					/*********** Process each JSON Node ************/
					int lengthJsonArr = jsonMainNode.length();
					for(int i=0;i<lengthJsonArr;i++)
					{
						/****** Get Object for each JSON node.***********/
						JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
						
						/******* Fetch node values **********/
						int song_id 		= Integer.parseInt(jsonChildNode.optString("song_id").toString());
			            String song_name 	= jsonChildNode.optString("song_name").toString();
			            String artist_name 	= jsonChildNode.optString("artist_name").toString();
			            
			            
			            OutputData += "Node : \n\n     "+ song_id +" | "
								                        + song_name +" | " 
								                        + artist_name +" \n\n ";
			            //Log.i("JSON parse", song_name);
			       }
					
					/************ Show Output on screen/activity **********/
					output.setText(OutputData);
					
				} catch (JSONException e) {
		
					e.printStackTrace();
				}

			} 	
		});	 
	}
}
