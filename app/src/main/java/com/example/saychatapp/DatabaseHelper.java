package com.example.saychatapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Registrierung.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE user(vorname text, nachname text, email text PRIMARY KEY, passwort text) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS user ");
    }

    //Daten hinzufügen
    public boolean einfügen(String vorname, String nachname, String email, String passwort){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("vorname", vorname);
        cv.put("nachname", nachname);
        cv.put("email", email);
        cv.put("passwort", passwort);
        long ins = db.insert("user", null, cv);
        if(ins==1){
            return false;
        } else{
            return true;
        }
    }

    //Datenbank wird nach vorhandener Email durchsucht
    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=?", new String[]{email});
        if(cursor.getCount()>0){
            return false;
        } else{
            return true;
        }
    }

    //Registrierungs- und Loginformular werden gegengeprüft (Email Registrierung == Email Login && Passwort Registrierung == Passwort Login)
    public Boolean checkFormular(String email, String passwort){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM user WHERE email=? AND passwort=? ", new String[]{email, passwort});
        if(cursor.getCount()>0){
            return true;
        } else{
            return false;
        }
    }
}
