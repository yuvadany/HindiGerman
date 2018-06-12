package com.bible.hindibible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PraisesActivity extends AppCompatActivity {
    DBHelper dbhelper = new DBHelper(this);
    String praises[] = { "Praise God" };
    ListView praiseView ;
    ArrayAdapter praiseArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praises);
        setTitle("Praises स्तुति");
        praiseView = (ListView) findViewById(R.id.praisesView);
        try
        {
            this.dbhelper.openDataBase();
            praises = dbhelper.getPraises();
        }
        catch (Exception localException)
        {
            System.out.println("Error...  # " + localException.getMessage());
        }
        praiseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, praises);
        praiseView.setAdapter(praiseArrayAdapter);
    }
}
