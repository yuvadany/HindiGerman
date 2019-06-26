package com.bible.hindibible;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class PraisesActivity extends AppCompatActivity {
    DBHelper dbhelper = new DBHelper(this);
    String praises[] = { "Praise God" };
    ListView praiseView ;
    ArrayAdapter praiseArrayAdapter;
    private AdView mAdView;
    SharedPreferences sharedpreferences,sharedPreferencesReadMode;
    public static final String SHARED_PREF_FONT_SIZE = "font_size";
    public static final float TEXT_FONT_SIZE = 13;
    public static final String TEXT_FONT_SIZE_VAR = "text_float_size";
    public static final String SHARED_PREF_NIGHT_DAY_MODE = "Night_Day_Mode";
    public static final String TEXT_COLOUR_VAR = "Text_Colour_Var";
    public static final String BACKROUND_COLOUR_VAR = "Background_Colour_Var";
    public static final int TEXT_COLOUR = Color.parseColor("#000000");
    public static final int BACKROUND_COLOUR = Color.parseColor("#FFFFFF");
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
        praiseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, praises){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                sharedpreferences = getSharedPreferences(SHARED_PREF_FONT_SIZE, Context.MODE_PRIVATE);
                sharedPreferencesReadMode = getSharedPreferences(SHARED_PREF_NIGHT_DAY_MODE, Context.MODE_PRIVATE);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sharedpreferences.getFloat(TEXT_FONT_SIZE_VAR,TEXT_FONT_SIZE));
                tv.setBackgroundColor(sharedPreferencesReadMode.getInt(BACKROUND_COLOUR_VAR, BACKROUND_COLOUR));
                tv.setTextColor(sharedPreferencesReadMode.getInt(TEXT_COLOUR_VAR, TEXT_COLOUR));
                // Return the view
                return view;
            }
        };
        praiseView.setAdapter(praiseArrayAdapter);
        // Back button starts
        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Back button ends
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdLeftApplication() {

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    // back option starts
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    // back option ends
}
