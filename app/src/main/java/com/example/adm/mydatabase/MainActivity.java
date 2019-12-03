package com.example.adm.mydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    TextView textView;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        DatabaseHelper db = new DatabaseHelper(this);

        db.addContact(new Contact("Steve Formoso", "9321-4430"));
        db.addContact(new Contact("Zero Berto Humberto da Silva", "(8267-7025"));
        db.addContact(new Contact("Rolando Caio da Rocha", "8745-1123"));
        db.addContact(new Contact("Omar Telo", "8766-4411"));


        List<Contact> contacts = db.getAllContacts();


        for (Contact c : contacts  )
        {

            String log = "ID: " + c.getId() + ", NAME: " + c.getName() + ", NUMBER: " + c.getPhone_number() + "\n";
            text = text + log;
        }
        textView.setText(text);
    }
}
