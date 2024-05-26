package com.example.splitworld.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.splitworld.database.model.MemberModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;
import com.example.splitworld.database.model.TransactionsBetweenMembersLinesModel;
import com.example.splitworld.database.model.UserModel;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "data.db";
    private static final int DB_VERSION = 1;
    public DBOpenHelper(Context context){
        super(context,DATABASE_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserModel.CREATE_TABLE);
        db.execSQL(MemberModel.CREATE_TABLE);
        db.execSQL(TransactionsBetweenMembersHeadersModel.CREATE_TABLE);
        db.execSQL(TransactionsBetweenMembersLinesModel.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
