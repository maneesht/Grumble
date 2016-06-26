package food.grumble;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context ctx = this;
        final TextView textView = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler();
        Thread thread = new Thread() {
            public void run() {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=32.914194,-117.086382&radius=2000&type=restaurant&key=<API-KEY>");
                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = urlConnection.getInputStream();
                    InputStreamReader isw = new InputStreamReader(in);
                    int data = isw.read();
                    String finalData = "";
                    while(data != -1) {
                        final char current = (char) data;
                        data = isw.read();
                        finalData += current;
                    }
                    final String finalData1 = finalData;
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(finalData1);
                        }
                    });

                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }

}
