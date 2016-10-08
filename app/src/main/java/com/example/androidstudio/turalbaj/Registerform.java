package com.example.androidstudio.turalbaj;


import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Registerform extends Activity{
    EditText first, sur, user, email,pwd1,pwd2;
    Button reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        first = (EditText)findViewById(R.id.etFirst);
        sur = (EditText)findViewById(R.id.etLast);
        user = (EditText)findViewById(R.id.etUsername);
        email = (EditText)findViewById(R.id.regEmail);
        pwd1 = (EditText)findViewById(R.id.regPass);
        pwd2 = (EditText)findViewById(R.id.regConPass);
        reg = (Button)findViewById(R.id.btnSend);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    DatabaseAdapter sqlDB = new DatabaseAdapter(Registerform.this);
                    if (sqlDB.checkExist(user.getText().toString().trim(), email.getText().toString().trim())){
                        Toast.makeText(getApplicationContext(),"Registration failed! Email/username already in use.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sqlDB.registeruser(first.getText().toString(),sur.getText().toString(),user.getText().toString(),email.getText().toString().trim(),pwd1.getText().toString().trim(),getDate());
                        Toast.makeText(getApplicationContext(),"Registration success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registerform.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Registration failed", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }



    public Boolean check(){
        String _first = first.getText().toString();
        String _surname = sur.getText().toString();
        String _user = user.getText().toString();
        String _email = email.getText().toString();
        String _pwd1 = pwd1.getText().toString();
        String _pwd2 = pwd2.getText().toString();

        Pattern email_pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Pattern name = Pattern.compile("^[A-Z][a-z]+( [A-Z][a-z]+)?$");
        Matcher email = email_pattern.matcher(_email);
        Matcher user = email_pattern.matcher(_user);
        Matcher fname = name.matcher(_first);
        Matcher sname = name.matcher(_surname);


        if (_first.isEmpty() || !fname.matches()) {
            Toast.makeText(getApplicationContext(),"Invalid First name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_surname.isEmpty() || !sname.matches()) {
            Toast.makeText(getApplicationContext(),"Invalid Surname", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_user.isEmpty() || user.matches()) {
            Toast.makeText(getApplicationContext(),"Invalid username", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_email.isEmpty() || !email.matches()) {
            Toast.makeText(getApplicationContext(),"Invalid email", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_pwd1.isEmpty() || _pwd2.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Password is/are empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (_pwd1.length() < 8 || _pwd2.length() < 8){
            Toast.makeText(getApplicationContext(),"Password too short. Should be 8 characters or longer", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!_pwd1.equals(_pwd2)) {
            Toast.makeText(getApplicationContext(),"Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    public String getDate(){
        DateFormat df = new SimpleDateFormat("EEEE, d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        return date;

    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Registerform.this, MainActivity.class);
        startActivity(intent);
    }
    public void onPause(){
        super.onPause();
        finish();
    }


}
