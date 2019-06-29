package com.example.wagochallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendFeedback(View view) {
        new AddFeedback().execute();
        hideAll();
    }

    public void hideAll() {
        TextView txtView = (TextView)findViewById(R.id.textView);

        txtView.setText( "Thank you for Participation!");

        RatingBar ratingView = (RatingBar)findViewById(R.id.ratingBar);

        ratingView.setVisibility(RatingBar.INVISIBLE);

        Button buttonView = (Button)findViewById(R.id.button_id);

        buttonView.setVisibility(Button.INVISIBLE);

        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);;
    }

    private class AddFeedback extends AsyncTask<String, Void, String> {
        private ProgressDialog pd;

        // onPreExecute called before the doInBackgroud start for display
        // progress dialog.
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(MainActivity.this, "", "Loading", true,
                    false); // Create and show Progress dialog
        }

        @Override
        protected String doInBackground(String... urls) {

         //   public void sendFeedback(View view) {
                URL url;
                HttpURLConnection urlConnection = null;
                try {

                    RatingBar rate = (RatingBar)findViewById(R.id.ratingBar);

                    //use ratings within event listner code block

                    float rating = rate.getRating();


                    String urlString = "http://192.168.3.134:3000/addfeedback?feedback=\"from app\"" + Float.toString(rating);

                    url = new URL( urlString );

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        char current = (char) data;
                        data = isw.read();
                        System.out.print(current);
                    }


                    return "no problem";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "error occured";
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    return "error occured";
                }
           // }
        }

        // onPostExecute displays the results of the doInBackgroud and also we
        // can hide progress dialog.
        @Override
        protected void onPostExecute(String result) {
            pd.dismiss();
        }
    }
}





