package com.betrayal.atcutter.scripts.data.constants;

public interface DatabaseConstants {
    String TABLE_NAME = "users";
    String COLUMN_ID = "id";
    String COLUMN_EMAIL = "email";
    String COLUMN_PASSWORD = "password";
    String COLUMN_PIN_CODE = "pinCode";

    String ENSURE_CREATED = String.format("CREATE TABLE IF NOT EXISTS \"%s\"(" +
            "\"%s\" INTEGER NOT NULL PRIMARY KEY UNIQUE," +
            "\"%s\" TEXT NOT NULL," +
            "\"%s\" TEXT NOT NULL," +
            "\"%s\" TEXT NOT NULL" +
            ");", TABLE_NAME, COLUMN_ID, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PIN_CODE);

    String SELECT_ALL = String.format("SELECT * FROM \"%s\";", TABLE_NAME);
}
