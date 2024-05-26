package com.example.splitworld.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.splitworld.database.helper.DBOpenHelper;
import com.example.splitworld.database.model.MemberModel;

import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends AbstrataDAO {
    private final String[]
            columns =
            {
                    MemberModel.COLUMN_ID,
                    MemberModel.COLUMN_NAME,
                    MemberModel.COLUMN_TOTAL_PAID,
                    MemberModel.COLUMN_TOTAL_LOAN
            };

    public MemberDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long Insert(MemberModel memberModel) {
        long rowAffect = 0;
        try {
            Open();
            ContentValues values = new ContentValues();
            values.put(MemberModel.COLUMN_NAME, memberModel.getName());
            values.put(MemberModel.COLUMN_TOTAL_PAID, memberModel.getTotal_paid());
            values.put(MemberModel.COLUMN_TOTAL_LOAN, memberModel.getTotal_loan());
            rowAffect = db.insert(MemberModel.TABLE_NAME, null, values);
        } finally {
            Close();
        }
        return rowAffect;
    }

    public List<MemberModel> findAll() {
        List<MemberModel> members = new ArrayList<>();
        Cursor cursor = null;
        try {
            Open();
            cursor = db.query(MemberModel.TABLE_NAME, columns, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    MemberModel member = new MemberModel();
                    member.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_ID)));
                    member.setName(cursor.getString(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_NAME)));
                    member.setTotal_paid(cursor.getDouble(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_TOTAL_PAID)));
                    member.setTotal_loan(cursor.getDouble(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_TOTAL_LOAN)));
                    members.add(member);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            Close();
        }
        return members;
    }
    public String findNameById(int id) {
        String name = null;
        Cursor cursor = null;
        try {
            Open();
            cursor = db.query(MemberModel.TABLE_NAME, new String[]{MemberModel.COLUMN_NAME}, MemberModel.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_NAME));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            Close();
        }
        return name;
    }
    public MemberModel findById(int id) {
        Cursor cursor = null;
        MemberModel memberModel = null;
        try {
            Open();
            cursor = db.query(MemberModel.TABLE_NAME, columns, MemberModel.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                memberModel = new MemberModel();
                memberModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_ID)));
                memberModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_NAME)));
                memberModel.setTotal_paid(cursor.getDouble(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_TOTAL_PAID)));
                memberModel.setTotal_loan(cursor.getDouble(cursor.getColumnIndexOrThrow(MemberModel.COLUMN_TOTAL_LOAN)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            Close();
        }
        return memberModel;
    }

    public int deleteMember(int id) {
        int rowsAffected = 0;
        try {
            Open();
            rowsAffected = db.delete(MemberModel.TABLE_NAME, MemberModel.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        } finally {
            Close();
        }
        return rowsAffected;
    }
}
