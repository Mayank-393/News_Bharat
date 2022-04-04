package com.example.android.newsfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {
    TextView username;
    EditText pass,repass;
    Button btn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        username=(TextView)findViewById(R.id.email_id);
        pass=(EditText)findViewById(R.id.pass);
        repass=(EditText)findViewById(R.id.repass);
        btn=(Button)findViewById(R.id.confirm);
        DB=new DBHelper(this);
        Intent intent=getIntent();
        username.setText(intent.getStringExtra("username"));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String user=username.getText().toString();
            String password=pass.getText().toString();
            String repassword=repass.getText().toString();
            if(password.equals(repassword))
            {
                Boolean checkpassupdate = DB.updatepassword(user, password);
                if (checkpassupdate == true) {
                    Intent intent = new Intent(getApplicationContext(), Login_activity.class);
                    startActivity(intent);
                    Toast.makeText(ResetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ResetActivity.this, "Password is not Updated Successfully", Toast.LENGTH_SHORT).show();

                }
            }
            else
            {
               Toast.makeText(ResetActivity.this,"Password Not Matching",Toast.LENGTH_SHORT).show();

            }


            }

        });

    }
}