package com.app.asynctaskdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //if you are loading large amount of data using asynctask add these 2 lines
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public  void getImage(View view) {
       MyTask task=new MyTask();
       task.execute();

    }
    class MyTask extends AsyncTask{
        InputStream instr;

        @Override
        protected Object doInBackground(Object[] objects) {

            try{
                //hit the server
                URL url=new URL("https://miro.medium.com/max/702/1*1cC0wSIGnsylrYEo7C9B3g.png");

                //to get the response openStream have return type InputStream means in form of byte code
                 instr= url.openStream();
            }  catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //convert byte code data of input stream in image
            Bitmap bitmap= BitmapFactory.decodeStream(instr);
            //set the response on the imageview
            ImageView img=(ImageView)findViewById(R.id.img);
            img.setImageBitmap(bitmap);
        }
    }
}
