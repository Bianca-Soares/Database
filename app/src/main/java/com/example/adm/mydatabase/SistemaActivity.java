package com.example.adm.mydatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class SistemaActivity extends AppCompatActivity
{
    TextView textView;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        
        textView.setText(text);

        // Button btn = (Button) findViewById(R.id.btn);

        // SharedPreferences sp = PreferencesManager.grtDefaultSharedPreferences(getBaseContext());

        // sp.edit().putBoolean("está_logado", false).apply();

        // if(sp.getBoolean("está_logado", false)){
        //     // condição
        // }
        

        //criar o objeto do tipo SistemaDB
        
        SistemaDB db = new SistemaDB();

    }
}
