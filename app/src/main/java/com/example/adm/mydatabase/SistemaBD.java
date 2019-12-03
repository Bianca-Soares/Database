package com.example.adm.mydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SistemaDB extends SQLiteOpenHelper{
    //variáveis
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "sistemaManager";
    private static final String DB_TABLE = "dbTable";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    
    //metódo construtor
    public SistemaDB(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("SistemaDB", "Metódo Construtor da super classe usado para a SistemaDB");
    } 

    //metódo criar tabela
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_DB_TABLE = "CREATE TABLE IF NOT EXISTS" + DB_TABLE + "(" + 
                        KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + "TEXT"+")";

        db.execSQL(CREATE_DB_TABLE);
        Log.d("SistemaDB", "oncREATE");
    }
    //metódo onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXITS" + DB_TABLE);
        Log.d("SistemaDB", "onUpgrade");
    }


    //METÓDO adicionar

    public void add(Contact contact){//classe contato usar os gets

        SQLiteDatabase db = this.getWritebleDatabase();//modo escrita

        ContentValues values = new ContentValues();
        db.put(KEY_NAME, contat.getName());
        
        db.insert(DB_TABLE, null, values);
        db.close();

    }

}