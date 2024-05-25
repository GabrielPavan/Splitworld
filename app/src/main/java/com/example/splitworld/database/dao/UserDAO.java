package com.example.splitworld.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Path;

import com.example.splitworld.database.helper.DBOpenHelper;
import com.example.splitworld.database.model.UserModel;

public class UserDAO extends AbstrataDAO{
    private final String[]
        columns =
            {
                    UserModel.COLUMN_ID,
                    UserModel.COLUMN_NAME,
                    UserModel.COLUMN_EMAIL,
                    UserModel.COLUMN_PASSWORD
            };

    public UserDAO(Context context) {db_helper = new DBOpenHelper(context);}
    public long Insert(UserModel userModel){
        long rowAffect = 0;
        try {
            Open();
            ContentValues values = new ContentValues();
            values.put(UserModel.COLUMN_NAME, userModel.getName());
            values.put(UserModel.COLUMN_EMAIL, userModel.getEmail());
            values.put(UserModel.COLUMN_PASSWORD, userModel.getPassword());

            rowAffect = db.insert(UserModel.TABLE_NAME, null, values);
        } finally {
            Close();
        }
        return rowAffect;
    }
    public boolean TryLogin(final String mail, final String password){
        try {
            Open();
            Cursor cursor = db.query(UserModel.TABLE_NAME,
                                    columns,
                                    UserModel.COLUMN_EMAIL + "=? AND " + UserModel.COLUMN_PASSWORD + "=?;",
                                    new String[]{mail,password},
                                    null,
                                    null,
                                    null);
            return cursor.getCount() == 1;
        } finally {
            Close();
        }
    }
    public void Delete(final long idUser){
        try {
            Open();
            db.delete(UserModel.TABLE_NAME, UserModel.COLUMN_ID + "= ?", new String[]{""+idUser});
        } finally {
            Close();
        }
    }
    public void DeleteALL(){
        try {
            Open();
            db.delete(UserModel.TABLE_NAME, null, null);
        } finally {
            Close();
        }
    }
}
