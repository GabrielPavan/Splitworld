package com.example.splitworld.database.model;

import androidx.annotation.NonNull;

import java.security.PublicKey;

public class MemberModel {
    public static final String TABLE_NAME = "member";
    public static final String
            COLUMN_ID = "_id",
            COLUMN_NAME = "name",
            COLUMN_TOTAL_LOAN = "total_loan",
            COLUMN_TOTAL_PAID= "total_paid";

    public static final String
        CREATE_TABLE =
            "create table if not exists " + TABLE_NAME
                + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_TOTAL_LOAN + " double, "
                + COLUMN_TOTAL_PAID + " double"
                + ");";

    public static final String
            DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";

    private int id;
    private String name;
    private double total_loan, total_paid;

    public MemberModel(){};
    public MemberModel(String name, double total_loan, double total_paid) {
        this.name = name;
        this.total_paid = total_paid;
        this.total_loan = total_loan;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getTotal_loan() {
        return total_loan;
    }
    public void setTotal_loan(double total_loan) {
        this.total_loan = total_loan;
    }
    public double getTotal_paid() {
        return total_paid;
    }
    public void setTotal_paid(double total_paid) {
        this.total_paid = total_paid;
    }
}
