package com.example.android.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Password_activity extends AppCompatActivity {
    EditText email;
    Button btn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_activity);
        email=(EditText)findViewById(R.id.username);
        btn=(Button)findViewById(R.id.Reset);
        DB=new DBHelper(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em=email.getText().toString();
                Boolean checkuser=DB.checkusername(em);
                if(checkuser==true)
                {
                    Intent intent=new Intent(getApplicationContext(),ResetActivity.class);
                    intent.putExtra("username",em);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Password_activity.this,"Wrong Email ID",Toast.LENGTH_SHORT).show();
                     
                }

            }
        });
    }
}