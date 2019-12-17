package com.example.adm.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SistemaDB extends SQLiteOpenHelper {
    //variáveis
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "sistemaManager";
    private static final String DB_TABLE = "dbTable";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    //metódo construtor
    public SistemaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("SistemaDB", "Metódo Construtor da super classe usado para a SistemaDB");
    }

    //metódo criar tabela
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB_TABLE = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + "(" +
                KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT" + ")";

        db.execSQL(CREATE_DB_TABLE);
        Log.d("SistemaDB", "oncREATE");
    }

    //metódo onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        Log.d("SistemaDB", "onUpgrade");
    }


    //METÓDO adicionar

    public void add(Contact contact) {//classe contato usar os gets

        SQLiteDatabase db = this.getWritableDatabase();//modo escrita

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());

        db.insert(DB_TABLE, null, values);
        db.close();

    }

    public void Del(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
    }

    //GET REGISTRO usa cursores

    Contacts getCont(int id) {
        SQLiteDatabase db = this.getReadableDatabase();//modo leitura
        
        Cursor cursor = db.query(DB_TABLE, new String[]{KEY_ID, DB_NAME}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null,null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        
        Contacts contacts = new Contacts(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return contacts;
    }
    
    public static final String TABLE_NAME = "usuarios";
public static final String DATABASE_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
db.execSQL(DATABASE_DROP_TABLE);

}
