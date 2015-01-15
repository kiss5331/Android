package com.androidexample.httpgetexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpGetAndroidExample extends Activity {

    TextView content;
    EditText fname, email, login, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_get_android_example);

        content = (TextView) findViewById(R.id.content);
        fname = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        login = (EditText) findViewById(R.id.loginname);
        pass = (EditText) findViewById(R.id.password);
        Button saveme = (Button) findViewById(R.id.save);


        saveme.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //ALERT MESSAGE
                Toast.makeText(getBaseContext(),
                        "Please wait, connecting to server.",
                        Toast.LENGTH_LONG).show();

                new Tesk().execute();

            }
        });
    }


    class Tesk extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {


                HttpClient HttpClient = new DefaultHttpClient();
                String URL = "https://apis.sktelecom.com/v1/weather/status?latitude=37.5660649&longitude=126.9826791";
                //Log.i("httpget", URL);
                try {
                    HttpGet httpget = new HttpGet(URL);


                    ResponseHandler<String> responseHandler = new BasicResponseHandler();


                    String SetServerString = "";
                    SetServerString = HttpClient.execute(httpget, responseHandler);


                    Log.e("성공", SetServerString);
                } catch (Exception ex) {

                    Log.e("에러", ex + "");


                }
            } catch (Exception a) {

                Log.e("에러", a + "");
            }


            return null;
        }
    }


}
