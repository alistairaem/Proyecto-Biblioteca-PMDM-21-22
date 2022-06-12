package com.example.biblioteca.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.biblioteca.entities.Libro;
import com.example.biblioteca.entities.Prestamo;

public class DatabaseHelper extends SQLiteOpenHelper {

    String sqlCreateLibro = "CREATE TABLE Libros (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, autor TEXT, genero TEXT, subgenero TEXT, leido INTEGER)";
    String sqlCreatePrestamo = "CREATE TABLE Prestamos (id INTEGER PRIMARY KEY AUTOINCREMENT, fechaInicio TEXT, fechaFin TEXT, finalizado INTEGER, libro TEXT)";

    public DatabaseHelper(Context context) {
        super(context, "biblioteca.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateLibro);
        db.execSQL(sqlCreatePrestamo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Libros");
        db.execSQL("DROP TABLE IF EXISTS Prestamos");
        onCreate(db);
    }

    // Crear un libro
    public long createLibro(Libro libro) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", libro.getTitulo());
        values.put("autor", libro.getAutor());
        values.put("genero", libro.getGenero());
        values.put("subgenero", libro.getSubgenero());
        values.put("leido", 0);
        long id = db.insert("Libros", null, values);
        db.close();
        return id;
    }

    // Devolver un libro por id
    public Libro getLibro(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Libros WHERE id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        Libro libro = new Libro();
        if (cursor.moveToFirst()) {
            libro.setId(cursor.getLong(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setGenero(cursor.getString(3));
            libro.setSubgenero(cursor.getString(4));
            libro.setLeido(cursor.getInt(5));
        }
        cursor.close();
        db.close();
        return libro;
    }

    // Devuelve todos los libros
    public Libro[] getAllLibros() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Libros";
        Cursor cursor = db.rawQuery(sql, null);
        Libro[] libros = new Libro[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            Libro libro = new Libro();
            libro.setId(cursor.getLong(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setGenero(cursor.getString(3));
            libro.setSubgenero(cursor.getString(4));
            libro.setLeido(cursor.getInt(5));
            libros[i] = libro;
            i++;
        }
        cursor.close();
        db.close();
        return libros;
    }

    // Marca como leido un libro
    public void marcarLeido(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("leido", 1);
        db.update("Libros", values, "id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    // Devuelve los libros leidos
    public Libro[] getLibrosLeidos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Libros WHERE leido = 1";
        Cursor cursor = db.rawQuery(sql, null);
        Libro[] libros = new Libro[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            Libro libro = new Libro();
            libro.setId(cursor.getLong(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setGenero(cursor.getString(3));
            libro.setSubgenero(cursor.getString(4));
            libro.setLeido(cursor.getInt(5));
            libros[i] = libro;
            i++;
        }
        cursor.close();
        db.close();
        return libros;
    }

    // Crear un prestamo
    public long createPrestamo(Prestamo prestamo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fechaInicio", prestamo.getFechaInicio());
        values.put("fechaFin", prestamo.getFechaFin());
        values.put("finalizado", prestamo.getFinalizado());
        values.put("libros", prestamo.getLibro().getId());

        long id = db.insert("Prestamos", null, values);
        db.close();
        return id;
    }
}

