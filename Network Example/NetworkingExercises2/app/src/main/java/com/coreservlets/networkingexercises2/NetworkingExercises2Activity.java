package com.coreservlets.networkingexercises2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;

import org.apache.http.client.ClientProtocolException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkingExercises2Activity extends Activity {
    private EditText mUrlToCount;
    private TextView mResultArea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mUrlToCount = (EditText)findViewById(R.id.url_to_count);
        mResultArea = (TextView)findViewById(R.id.result_area);
    }

    public void countLines(View clickedButton) {
        String urlString = mUrlToCount.getText().toString();
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            BufferedReader in = 
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            int numLines = 0;
            while ((in.readLine()) != null) {
                numLines++;
            }
            String result = String.format("%s contains %s lines", urlString, numLines);
            showResult(result);
        } catch (MalformedURLException mue) {
            showError("Bad URL: " + urlString);
            mue.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            showError("Error in connection: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
    
    public void countCharacters(View clickedButton) {
        String urlString = mUrlToCount.getText().toString();
        try {
            String urlBody = HttpUtils.urlContent(urlString);
            String result = String.format("%s contains %s characters", urlString, urlBody.length());
            showResult(result);
        } catch (ClientProtocolException cpe) {
            showError("Bad URL: " + urlString);
            cpe.printStackTrace(); // View this in DDMS window
        } catch (IOException ioe) {
            showError("Error in connection: " + ioe);
            ioe.printStackTrace(); // View this in DDMS window
        } 
    }
    
    private void showError(String text) {
        mResultArea.setTextSize(15);
        mResultArea.setText("\n\n" + text);
    }
    
    private void showResult(String text) {
        mResultArea.setTextSize(20);
        mResultArea.setText("\n\n" + text);
    }
}
