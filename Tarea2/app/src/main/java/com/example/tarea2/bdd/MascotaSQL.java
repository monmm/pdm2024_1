package com.example.tarea2.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MascotaSQL extends SQLiteOpenHelper {

    private static final String TABLA_MASCOTAS = "tabla_mascotas";
    private static final String COL_ID = "ID";
    private static final String COL_NOMBRE = "NOMBRE";
    private static final String COL_SEXO = "SEXO";
    private static final String COL_NACIMIENTO = "NACIMIENTO";
    private static final String COL_RAZA = "RAZA";
    private static final String COL_DUENO = "DUENO";
    private static final String COL_TELEFONO = "TELEFONO";
    private static final String COL_DIRECCION = "DIRECCION";
    private static final String COL_CP = "CP";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_BDD = "CREATE TABLE " + TABLA_MASCOTAS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE + " TEXT NOT NULL, " +
            COL_SEXO + " TEXT NOT NULL, " +
            COL_NACIMIENTO + " TEXT NOT NULL, " +
            COL_RAZA + " TEXT NOT NULL, " +
            COL_DUENO + " TEXT NOT NULL, " +
            COL_TELEFONO + " TEXT NOT NULL, " +
            COL_DIRECCION + " TEXT NOT NULL, " +
            COL_CP + " TEXT NOT NULL," +
            "CONSTRAINT unique_mascota UNIQUE (" + COL_NOMBRE + ", " + COL_DUENO + ")" +
            ");";

    public MascotaSQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_MASCOTAS);
        onCreate(db);
    }
}