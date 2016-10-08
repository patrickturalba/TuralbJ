package com.example.androidstudio.turalbaj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class blank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        final Button btnOnTouch = (Button) findViewById(R.id.btnOnTouch);
        btnOnTouch.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v){
                Intent i = new Intent(blank.this,Ontouch.class);
                startActivity(i);
            }

        });}

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(blank.this, MainActivity.class);
        startActivity(intent);
    }
    public void onPause(){
        super.onPause();
        finish();
    }


}

