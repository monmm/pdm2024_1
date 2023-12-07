package com.example.tarea2.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tarea2.modelos.Mascota;

import java.util.ArrayList;

public class MascotaBDD {
    private static final int VERSION = 1;
    private static final String NOM_BDD = "mascota.db";
    private static final String TABLA_MASCOTAS = "tabla_mascotas";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOMBRE = "NOMBRE";
    private static final int NUM_COL_NOMBRE = 1;
    private static final String COL_SEXO = "SEXO";
    private static final int NUM_COL_SEXO = 2;
    private static final String COL_NACIMIENTO = "NACIMIENTO";
    private static final int NUM_COL_NACIMIENTO = 3;
    private static final String COL_RAZA = "RAZA";
    private static final int NUM_COL_RAZA = 4;
    private static final String COL_DUENO = "DUENO";
    private static final int NUM_COL_DUENO = 5;
    private static final String COL_TELEFONO = "TELEFONO";
    private static final int NUM_COL_TELEFONO = 6;
    private static final String COL_DIRECCION = "DIRECCION";
    private static final int NUM_COL_DIRECCION = 7;
    private static final String COL_CP = "CP";
    private static final int NUM_COL_CP = 8;

    private SQLiteDatabase bdd;
    private MascotaSQL mascotas;

    public MascotaBDD(Context context) {
        mascotas = new MascotaSQL(context, NOM_BDD, null, VERSION);
    }

    public void openForWrite() {
        bdd = mascotas.getWritableDatabase();
    }

    public void openForRead() {
        bdd = mascotas.getReadableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public long insertMascota(Mascota mascota) {
        ContentValues content = new ContentValues();
        content.put(COL_NOMBRE, mascota.getNombre());
        content.put(COL_SEXO, mascota.getSexo());
        content.put(COL_NACIMIENTO, mascota.getNacimiento());
        content.put(COL_RAZA, mascota.getRaza());
        content.put(COL_DUENO, mascota.getDueno());
        content.put(COL_TELEFONO, mascota.getTelefono());
        content.put(COL_DIRECCION, mascota.getDireccion());
        content.put(COL_CP, mascota.getCp());
        return bdd.insert(TABLA_MASCOTAS, null, content);
    }

    public int updateMascota(int id, Mascota mascota) {
        ContentValues content = new ContentValues();
        content.put(COL_NOMBRE, mascota.getNombre());
        content.put(COL_SEXO, mascota.getSexo());
        content.put(COL_NACIMIENTO, mascota.getNacimiento());
        content.put(COL_RAZA, mascota.getRaza());
        content.put(COL_DUENO, mascota.getDueno());
        content.put(COL_TELEFONO, mascota.getTelefono());
        content.put(COL_DIRECCION, mascota.getDireccion());
        content.put(COL_CP, mascota.getCp());
        return bdd.update(TABLA_MASCOTAS, content, COL_ID + " = " + id, null);
    }

    public int removeMascota(String nombre, String dueno) {
        return bdd.delete(TABLA_MASCOTAS, COL_NOMBRE + " = '" + nombre +
                "' AND " + COL_DUENO + " = '" + dueno + "'", null);
    }

    public Mascota getMascota(String nombre, String dueno) {
        Cursor c = bdd.query(TABLA_MASCOTAS,
                new String[]{COL_ID, COL_NOMBRE, COL_SEXO, COL_NACIMIENTO,
                        COL_RAZA, COL_DUENO, COL_TELEFONO, COL_DIRECCION, COL_CP},
                COL_NOMBRE + " LIKE \"" + nombre + "\" AND " + COL_DUENO + " LIKE \"" + dueno + "\"",
                null, null, null, COL_NOMBRE);
        return cursorToMascota(c);
    }

    public Mascota cursorToMascota(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        Mascota mascota = new Mascota();
        mascota.setId(c.getInt(NUM_COL_ID));
        mascota.setNombre(c.getString(NUM_COL_NOMBRE));
        mascota.setSexo(c.getString(NUM_COL_SEXO));
        mascota.setNacimiento(c.getString(NUM_COL_NACIMIENTO));
        mascota.setRaza(c.getString(NUM_COL_RAZA));
        mascota.setDueno(c.getString(NUM_COL_DUENO));
        mascota.setTelefono(c.getString(NUM_COL_TELEFONO));
        mascota.setDireccion(c.getString(NUM_COL_DIRECCION));
        mascota.setCp(c.getString(NUM_COL_CP));
        c.close();
        return mascota;
    }

    public ArrayList<Mascota> getAllMascotas(String dueno) {
        Cursor c = bdd.query(TABLA_MASCOTAS, new String[]{COL_ID, COL_NOMBRE, COL_SEXO,
                        COL_NACIMIENTO, COL_RAZA, COL_DUENO, COL_TELEFONO, COL_DIRECCION, COL_CP},
                COL_DUENO + " LIKE \"" + dueno + "\"", null, null, null, COL_NOMBRE);

        if (c.getCount() == 0) {
            c.close();
            return null;
        }

        ArrayList<Mascota> mascotaList = new ArrayList<>();
        while (c.moveToNext()) {
            Mascota mascota = new Mascota();
            mascota.setId(c.getInt(NUM_COL_ID));
            mascota.setNombre(c.getString(NUM_COL_NOMBRE));
            mascota.setSexo(c.getString(NUM_COL_SEXO));
            mascota.setNacimiento(c.getString(NUM_COL_NACIMIENTO));
            mascota.setRaza(c.getString(NUM_COL_RAZA));
            mascota.setDueno(c.getString(NUM_COL_DUENO));
            mascota.setTelefono(c.getString(NUM_COL_TELEFONO));
            mascota.setDireccion(c.getString(NUM_COL_DIRECCION));
            mascota.setCp(c.getString(NUM_COL_CP));
            mascotaList.add(mascota);
        }
        c.close();
        return mascotaList;
    }

    public boolean existeDueno(String nombreDueno) {
        Cursor cursor = bdd.query(
                true, // distinct
                TABLA_MASCOTAS, // tabla
                new String[]{COL_DUENO}, // columnas
                COL_DUENO + "=?", // where
                new String[]{nombreDueno}, // valores where
                null, // groupBy
                null, // having
                null, // orderBy
                null // limit
        );

        boolean existe = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }

        return existe;
    }


    public void resetDatabase() {
        mascotas.getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + TABLA_MASCOTAS);
        mascotas.onCreate(mascotas.getWritableDatabase());
    }

}