package com.example.android.newsfeed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME="Login.db";




    public DBHelper(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table user(Username TEXT primary key,Password TEXT,Email TEXT,Mobile TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");

    }
    public Boolean insertData(String username, String password, String Email, String mobile)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("Username",username);
        contentValues.put("Password",password);
        contentValues.put("Email",Email);
        contentValues.put("Mobile",mobile);
        long result=MyDB.insert("user",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }
    public Boolean checkusername(String username)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from user where Email=?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public Boolean checkusernamepassword(String email,String password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from user where Email=? and Password=?",new String[] {email,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean updatepassword(String username,String password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password",password);
        long result=MyDB.update("user",contentValues,"Email=?",new String[]{username});
        if(result==-1)
        {
            return false;
        }
        else
            return true;

    }



}
