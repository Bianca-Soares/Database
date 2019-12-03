package com.example.adm.mydatabase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE= "phone";

    public DatabaseHelper(Context context)
    {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("DatabaseHelper", "Metódo Construtor da classe DatabaseHelper");
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
            String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT" + ")";
            db.execSQL(CREATE_CONTACTS_TABLE);
        Log.d("DatabaseHelper", "OnCreate");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        Log.d("DatabaseHelper", "OnUpgrade");
    }


    void addContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone_number());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public void deletar(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
    }
    
    Contact getContact (int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {KEY_ID, KEY_NAME, KEY_PHONE}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return contact;
    }

    public List<Contact> getAllContacts()
    {

        List<Contact>  contactList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {

            do
            {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));

                contactList.add(contact);
            }
            while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateContact (Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values =  new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone_number());

        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?", new String[] {String.valueOf(contact.getId())});
    }

    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?", new String[] {String.valueOf(contact.getId())});
        db.close();
    }

    public int getContactCount()
    {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();

    }

}
