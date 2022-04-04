package com.example.android.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {
    EditText username,password;
    Button btn;
    DBHelper DB;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login_activity);
        username=(EditText)findViewById(R.id.editTextEmail);
        password=(EditText)findViewById(R.id.editTextPassword);
        btn=(Button)findViewById(R.id.cirLoginButton);
        DB=new DBHelper(this);
        tv=(TextView) findViewById(R.id.vp1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent it=new Intent(getApplicationContext(),Password_activity.class);
             startActivity(it);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String user=username.getText().toString();
             String pass=password.getText().toString();
             if(user.equals("") || pass.equals(""))
             {
                 Toast.makeText(Login_activity.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
             }
             else
             {
                 Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                 if( checkuserpass==true)
                 {
                     Toast.makeText(Login_activity.this,"Sign in Successful",Toast.LENGTH_LONG).show();
                     Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                     startActivity(intent);
                     overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                 }
                 else
                 {
                     Toast.makeText(Login_activity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();

                 }

             }

            }
        });
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, Registration_activity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }


}