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
                    TransactionsBetweenMembersHeadersModel.COLUMN_EQUAL_DIVISION,
                    TransactionsBetweenMembersHeadersModel.COLUMN_DATE,
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
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_EQUAL_DIVISION, transaction.isIs_equal_division());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_DATE, transaction.getExpense_date());
            values.put(TransactionsBetweenMembersHeadersModel.COLUMN_PAYED_BY_NAME, transaction.getPayed_by_name());
            rowAffect = db.insert(TransactionsBetweenMembersHeadersModel.TABLE_NAME, null, values);
        } finally {
            Close();
        }
        return rowAffect;
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
                    transaction.setIs_equal_division((cursor.getString(cursor.getColumnIndexOrThrow(TransactionsBetweenMembersHeadersModel.COLUMN_EQUAL_DIVISION))));
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
}