package com.example.androidstudio.turalbaj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

public class Ontouch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontouch);

        final ImageView image = (ImageView) findViewById(R.id.imageView);

        image.setOnTouchListener(new View.OnTouchListener() {
            float iX, iY, fX, fY,dx,dy;
            EditText x1 = (EditText)findViewById(R.id.x1);
            //EditText x2 = (EditText)findViewById(R.id.x2);
            EditText y1 = (EditText)findViewById(R.id.y1);
            //EditText y2 = (EditText)findViewById(R.id.y2);
            EditText cd = (EditText)findViewById(R.id.d);
            EditText cm = (EditText)findViewById(R.id.m);
            EditText cq = (EditText)findViewById(R.id.q);
            String motion,motionL,quadrant,sdx,sdy;
            //editText.setText("Google is your friend.", TextView.BufferType.EDITABLE);

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        iX = event.getX();
                        iY = event.getY();
                        //float viewX = screenX - v.getLeft();
                        //float viewY = screenY - v.getTop();
                        //Toast.makeText(getApplicationContext(),"X="+iX+" Y="+iY,Toast.LENGTH_SHORT).show();





                    case MotionEvent.ACTION_UP:
                        fX = event.getX();
                        fY = event.getY();

                        if (iX < fX) {
                            //Toast.makeText(getApplicationContext(), "Left to Right swipe", Toast.LENGTH_SHORT).show();
                            motionL = "Left to Right swipe";
                            if (iY > fY) {
                                quadrant = "FIRST";
                            }
                            if (iY < fY) {
                                quadrant = "FOURTH";
                            }
                            //  Toast.makeText(getApplicationContext(), "Inital X=" + iX + " Final X=" + fX + " Left to Right swipe", Toast.LENGTH_SHORT).show();
                        }
                        if (iX > fX) {
                            //Toast.makeText(getApplicationContext(), "Right to Left swipe", Toast.LENGTH_SHORT).show();
                            // Toast.makeText(getApplicationContext(), "Initial X=" + iX + " Final X=" + fX + " Right to Left swipe", Toast.LENGTH_SHORT).show();
                            motionL = "Right to Left swipe";
                            if (iY > fY) {
                                quadrant = "SECOND";
                            }
                            if (iY < fY) {
                                quadrant = "THIRD";
                            }
                        }
                        if (iY < fY) {
                            // Toast.makeText(getApplicationContext(), "Upward swipe", Toast.LENGTH_SHORT).show();
                            // Toast.makeText(getApplicationContext(), "Initial Y=" + iY + " Final Y=" + fY + " Downward swipe", Toast.LENGTH_SHORT).show();
                            motion = "Downward swipe";
                        }
                        if (iY > fY) {
                            //    Toast.makeText(getApplicationContext(), "Downward swipe", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(), "Initial Y=" + iY + " Final Y=" + fY + " Upward swipe", Toast.LENGTH_SHORT).show();
                            motion = "Upward swipe";
                        }
                        //String tempx,tempy;

                        dx=iX-fX;
                        dy=iY-fY;

                        x1.setText("X1: "+String.valueOf(iX)+" X2: "+String.valueOf(fX));
                        //x2.setText("X2"+String.valueOf(fX));
                        y1.setText("Y1: "+String.valueOf(iY)+" Y1: "+String.valueOf(fY));
                        //y2.setText("Y1"+String.valueOf(fY));
                        cq.setText("quadrant: "+quadrant, TextView.BufferType.EDITABLE);
                        if(dx==0 && dy ==0)
                        {
                            cm.setText("motion: NO MOTION", TextView.BufferType.EDITABLE);
                            cq.setText("quadrant: NO QUADRANT", TextView.BufferType.EDITABLE);

                        }
                        else
                        {
                            cm.setText("motion: "+motion+" and "+motionL, TextView.BufferType.EDITABLE);
                        }
                        cd.setText("x:"+String.valueOf(dx) + " y:"+String.valueOf(dy),TextView.BufferType.EDITABLE);
                        return true;

                }
                return false;

            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}