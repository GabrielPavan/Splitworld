package com.example.splitworld.database.model;

public class UserModel {
    public static final String TABLE_NAME = "user";
    public static final String
        COLUMN_ID = "_id",
        COLUMN_NAME = "name",
        COLUMN_EMAIL = "email",
        COLUMN_PASSWORD = "password";

    public static final String
        CREATE_TABLE =
            "create table if not exists " + TABLE_NAME
                + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_EMAIL + " text not null, "
                + COLUMN_PASSWORD + " text not null"
                + ");";
    public static final String
        DROP_TABLE = "drop table if exists " + TABLE_NAME + ";";

    private int id;
    private String name, email, password;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}