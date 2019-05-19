package com.example.saychatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText et_email_log, et_password_log;
    Button btn_log;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        et_email_log = (EditText)findViewById(R.id.et_email_log);
        et_password_log = (EditText)findViewById(R.id.et_passwort_log);
        btn_log = (Button)findViewById(R.id.btn_log);

        //Login Button initialisieren!
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email_log.getText().toString();
                String passwort = et_password_log.getText().toString();
                Boolean checkFormular = db.checkFormular(email, passwort);
                if(checkFormular==true){
                    Toast.makeText(getApplicationContext(),"LOGIN ERFOLGREICH!", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "FALSCHE EMAIL ODER FALSCHES PASSWORT!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
