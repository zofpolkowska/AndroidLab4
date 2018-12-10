package com.example.zofiapolkowska.asynctaskexampleproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void decimalToBinary(View view){
        TextView textView = findViewById(R.id.editText);
        ExampleAsyncTask exampleAsyncTask = new ExampleAsyncTask();
        exampleAsyncTask.execute(textView.getText ().toString ());

    }

    private class ExampleAsyncTask extends AsyncTask<String, Void, String> {
        protected void onPreExecute(){
            TextView textView = findViewById(R.id.textView);
            textView.setText("Calculations in background...");
        }
        protected String doInBackground(String ... strings) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }

            return Integer.toString (Integer.parseInt (strings[0]), 2);
        }

        protected void onPostExecute(String result) {
            TextView textView = findViewById(R.id.textView);
            textView.setText(result);
        }
    }

}
