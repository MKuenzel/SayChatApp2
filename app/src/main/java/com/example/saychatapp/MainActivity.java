package com.example.saychatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText et_vorname_reg, et_nachname_reg, et_email_reg, et_password_reg;
    TextView tv_reg2;
    Button  btn_reg;

    final String databaseName = "databaseUser.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        //Variablen vom Registrierungsformular werden initialisiert
        et_vorname_reg = (EditText) findViewById(R.id.et_vorname_reg);
        et_nachname_reg = (EditText) findViewById(R.id.et_nachname_reg);
        et_email_reg = (EditText) findViewById(R.id.et_email_reg);
        et_password_reg =(EditText) findViewById(R.id.et_password_reg);
        tv_reg2 = (TextView) findViewById(R.id.tv_reg2);

        //Login TextView initialisieren
        tv_reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });

        //Button initialisierung
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = et_vorname_reg.getText().toString();
                String s2 = et_nachname_reg.getText().toString();
                String s3 = et_email_reg.getText().toString();
                String s4 = et_password_reg.getText().toString();

                if(s3.equals("")||s4.equals("")){
                    Toast.makeText(getApplicationContext(), "Bitte geben Sie Ihre Emailadresse und Passwort ein.", Toast.LENGTH_SHORT).show();
                } else{
                    Boolean checkEmail = db.checkEmail(s3);
                    if(checkEmail==true){
                        Boolean einfügen = db.einfügen(s1,s2,s3,s4);
                        if(einfügen==true){
                            Toast.makeText(getApplicationContext(), "Erfolgreich Registriert!", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Emailadresse existiert bereits!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
