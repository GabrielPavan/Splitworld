package com.example.splitworld.database.model;

public class TransactionsBetweenMembersHeadersModel {
    public static final String TABLE_NAME = "transactions_between_members_headers";
    public static final String
            COLUMN_ID = "_id",
            COLUMN_TOTAL = "total_value",
            COLUMN_EXPENSE_TYPE = "expense_type",
            COLUMN_DESCRIPTION = "description",
            COLUMN_PAYED_BY = "payed_by",
            COLUMN_PAYED_BY_NAME = "payed_by_name";

    public static final String
            CREATE_TABLE =
            "create table if not exists " + TABLE_NAME
                    + "("
                    + COLUMN_ID + " integer primary key autoincrement, "
                    + COLUMN_EXPENSE_TYPE + " text not null, "
                    + COLUMN_DESCRIPTION + " text not null, "
                    + COLUMN_PAYED_BY + " int not null, "
                    + COLUMN_PAYED_BY_NAME + " text not null, "
                    + COLUMN_TOTAL + " double not null"
                    + ");";
    public static final String
            DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";

    private int id, payed_by;
    private String expense_type, expense_description, expense_date, payed_by_name;
    private double expense_total_value;
    private String is_equal_division;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getExpense_type() {
        return expense_type;
    }
    public void setExpense_type(String expense_type) {
        this.expense_type = expense_type;
    }
    public String getExpense_description() {
        return expense_description;
    }
    public void setExpense_description(String expense_description) {
        this.expense_description = expense_description;
    }
    public double getExpense_total_value() {
        return expense_total_value;
    }
    public void setExpense_total_value(double expense_total_value) {
        this.expense_total_value = expense_total_value;
    }
    public int getPayed_by() {
        return payed_by;
    }
    public void setPayed_by(int payed_by) {
        this.payed_by = payed_by;
    }
    public String getPayed_by_name() {
        return payed_by_name;
    }
    public void setPayed_by_name(String payed_by_name) {
        this.payed_by_name = payed_by_name;
    }
}