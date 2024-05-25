package com.example.splitworld.database.model;

public class TransactionsBetweenMembersModel {
    public static final String TABLE_NAME = "transactions_between_members";
    public static final String
            COLUMN_ID = "_id",
            COLUMN_MEMBER_WHO_PAID = "member_who_paid",
            COLUMN_MEMBER_WHO_BORROWED = "member_who_borrowed",
            COLUMN_TOTAL_BORROWED = "total_borrowed";

    public static final String
            CREATE_TABLE =
            "create table if not exists " + TABLE_NAME
                    + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_MEMBER_WHO_PAID + " int not null, "
                    + COLUMN_MEMBER_WHO_BORROWED + " int not null, "
                    + COLUMN_TOTAL_BORROWED + " double"
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";
}
