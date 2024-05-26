package com.example.splitworld.database.model;

public class TransactionsBetweenMembersLinesModel {
    public static final String TABLE_NAME = "transactions_between_members_lines";
    public static final String
            COLUMN_ID = "_id",
            COLUMN_TRANSACTION_HEADERS_ID = "transaction_header_id",
            COLUMN_MEMBER_WHO_PAID = "member_who_paid",
            COLUMN_MEMBER_WHO_BORROWED = "member_who_borrowed",
            COLUMN_TOTAL_BORROWED = "total_borrowed";

    public static final String
            CREATE_TABLE =
            "create table if not exists " + TABLE_NAME
                    + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_TRANSACTION_HEADERS_ID + " integer not null, "
                    + COLUMN_MEMBER_WHO_PAID + " int not null, "
                    + COLUMN_MEMBER_WHO_BORROWED + " int not null, "
                    + COLUMN_TOTAL_BORROWED + " double"
                    + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";

    private int id, transaction_header_id, member_who_paid, member_who_borrowed;
    private double total_borrowed;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTransaction_header_id() {
        return transaction_header_id;
    }
    public void setTransaction_header_id(int transaction_header_id) {
        this.transaction_header_id = transaction_header_id;
    }
    public int getMember_who_paid() {
        return member_who_paid;
    }
    public void setMember_who_paid(int member_who_paid) {
        this.member_who_paid = member_who_paid;
    }
    public int getMember_who_borrowed() {
        return member_who_borrowed;
    }
    public void setMember_who_borrowed(int member_who_borrowed) {
        this.member_who_borrowed = member_who_borrowed;
    }
    public double getTotal_borrowed() {
        return total_borrowed;
    }
    public void setTotal_borrowed(double total_borrowed) {
        this.total_borrowed = total_borrowed;
    }
}