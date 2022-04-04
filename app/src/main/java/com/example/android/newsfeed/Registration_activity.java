package com.example.android.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration_activity extends AppCompatActivity {
    EditText username,password,emailadd,mobile;
    Button btn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activity);
        changeStatusBarColor();
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        emailadd=(EditText)findViewById(R.id.email);
        mobile=(EditText)findViewById(R.id.mobile);
        btn=(Button)findViewById(R.id.cirRegisterButton);
        DB=new DBHelper(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String email=emailadd.getText().toString();
                String mob=mobile.getText().toString();

                if(user.equals("") || pass.equals("")||email.equals("")||mob.equals("") )
                {
                    Toast.makeText(Registration_activity.this,"Please Fill All the Details",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean insert=DB.insertData(user,pass,email,mob);
                    if(insert==true)
                    {
                        Toast.makeText(Registration_activity.this,"Registered Successful",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
                    }
                    else
                    {
                        Toast.makeText(Registration_activity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
    public void changeStatusBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));

        }
    }
    public void onLoginClick(View view)
    {
        startActivity(new Intent(this,Login_activity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}