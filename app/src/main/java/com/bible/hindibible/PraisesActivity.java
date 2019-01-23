package com.bible.hindibible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
