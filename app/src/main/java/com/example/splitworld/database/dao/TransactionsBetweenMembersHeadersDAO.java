package com.example.splitworld.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.splitworld.database.helper.DBOpenHelper;
import com.example.splitworld.database.model.TransactionsBetweenMembersHeadersModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionsBetweenMembersHeadersDAO extends AbstrataDAO {
    private final String[]
            columns =
            {
                    TransactionsBetweenMembersHeadersModel.COLUMN_ID,
                    TransactionsBetweenMembersHeadersModel.COLUMN_EXPENSE_TYPE,
                    TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY,
                    TransactionsBetweenMembersHeadersModel.COLUMN_DESCRIPTION,
                    TransactionsBetweenMembersHeadersModel.COLUMN_TOTAL,
                    TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY_NAME
            };

    public TransactionsBetweenMembersHeadersDAO(Context context) {
        db_helper = new DBOpenHelper(context);
    }

    public long Insert(TransactionsBetweenMembersHeadersModel transaction) {
        long rowAffect = 0;
        try {
            Open();
            ContentValues values = new ContentValues();
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_EXPENSE_TYPE, transaction.getExpense_type());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY, transaction.getPayed_by());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_DESCRIPTION, transaction.getExpense_description());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_TOTAL, transaction.getExpense_total_value());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY_NAME, transaction.getPayed_by_name());
            rowAffect = db.insert(TransactionsBetweenMembersHeadersModel.TABLE_NAME, null, values);
        } finally {
            Close();
        }
        return rowAffect;
    }
    public int Update(TransactionsBetweenMembersHeadersModel transaction) {
        int rowsAffected = 0;
        try {
            Open();
            ContentValues values = new ContentValues();
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_EXPENSE_TYPE, transaction.getExpense_type());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY, transaction.getPayed_by());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_DESCRIPTION, transaction.getExpense_description());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_TOTAL, transaction.getExpense_total_value());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY_NAME, transaction.getPayed_by_name());

            rowsAffected = db.update(TransactionsBetweenMembersHeadersModel.TABLE_NAME, values,
                    TransactionsBetweenMembersHeadersModel.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(transaction.getId())});
        } finally {
            Close();
        }
        return rowsAffected;
    }
    public double getTotalTransactionsByMemberId(int memberId) {
        double total = 0;
        Cursor cursor = null;
        try {
            Open();
            cursor = db.rawQuery("SELECT SUM(" + TransactionsBetweenMembersHeadersModel.COLUMN_TOTAL + ") as total FROM " + TransactionsBetweenMembersHeadersModel.TABLE_NAME + " WHERE " + TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY + " = ?", new String[]{String.valueOf(memberId)});
            if (cursor != null && cursor.moveToFirst()) {
                total = cursor.getDouble(cursor.getColumnIndexOrThrow("total"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            Close();
        }
        return total;
    }

    public List<TransactionsBetweenMembersHeadersModel> findAll() {
        List<TransactionsBetweenMembersHeadersModel> transactions = new ArrayList<>();
        Cursor cursor = null;
        try {
            Open();
            cursor = db.query(TransactionsBetweenMembersHeadersModel.TABLE_NAME, columns, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    TransactionsBetweenMembersHeadersModel transaction = new TransactionsBetweenMembersHeadersModel();
                    transaction.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_ID)));
                    transaction.setExpense_type(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_EXPENSE_TYPE)));
                    transaction.setPayed_by(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY)));
                    transaction.setExpense_description(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_DESCRIPTION)));
                    transaction.setExpense_total_value(cursor.getDouble(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_TOTAL)));
                    transaction.setPayed_by_name(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY_NAME)));
                    transactions.add(transaction);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            Close();
        }
        return transactions;
    }
    public TransactionsBetweenMembersHeadersModel findById(int id) {
        TransactionsBetweenMembersHeadersModel transaction = null;
        Cursor cursor = null;
        try {
            Open();
            cursor = db.query(TransactionsBetweenMembersHeadersModel.TABLE_NAME, columns,
                    TransactionsBetweenMembersHeadersModel.COLUMN_ID + " = ?",
                    new String[]{String.valueOf(id)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                transaction = new TransactionsBetweenMembersHeadersModel();
                transaction.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_ID)));
                transaction.setExpense_type(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_EXPENSE_TYPE)));
                transaction.setPayed_by(cursor.getInt(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY)));
                transaction.setExpense_description(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_DESCRIPTION)));
                transaction.setExpense_total_value(cursor.getDouble(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_TOTAL)));
                transaction.setPayed_by_name(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY_NAME)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            Close();
        }
        return transaction;
    }

    public void deleteById(int id) {
        try {
            Open();
            db.delete(TransactionsBetweenMembersHeadersModel.TABLE_NAME, TransactionsBetweenMembersHeadersModel.COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        } finally {
            Close();
        }
    }
}