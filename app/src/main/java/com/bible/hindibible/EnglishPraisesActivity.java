package com.bible.hindibible;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class EnglishPraisesActivity  extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] praisesArray = {"1-100", "101-200", "201-300", "301-400", "401-500",
            "501-600", "601-700", "701-800", "801-900", "901-1000"};
    ScrollView scroll;
    Spinner praises_spinner;
    String ta_verse;
    DBHelper dbhelper = new DBHelper(this);
    TextView praises_text;
    String sp1;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_praises);
        setTitle("1000 Praises");
        scroll = ((ScrollView) findViewById(R.id.scrollPraise));
        praises_text = ((TextView) findViewById(R.id.prsies1000));
        praises_spinner = (Spinner) findViewById(R.id.praises_spinner);
        praises_spinner.setOnItemSelectedListener(this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stu

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3) {
        String sp1 = String.valueOf(praises_spinner.getSelectedItem());
        switch (parent.getId()) {
            case R.id.praises_spinner: {
                String praises = "No verses";
                try {
                    praises = dbhelper.getPraises(getList(sp1));
                } catch (Exception e) {

                }
                praises_text.setText(praises);
                scroll.fullScroll(ScrollView.FOCUS_UP);
            }
        }
    }
    public String getList(String number) {
        if (number.equalsIgnoreCase("1-100")) {
            return "100";
        } else if (number.equalsIgnoreCase("101-200")) {
            return "200";
        } else if (number.equalsIgnoreCase("201-300")) {
            return "300";
        } else if (number.equalsIgnoreCase("301-400")) {
            return "400";
        } else if (number.equalsIgnoreCase("401-500")) {
            return "500";
        } else if (number.equalsIgnoreCase("501-600")) {
            return "600";
        } else if (number.equalsIgnoreCase("601-700")) {
            return "700";
        } else if (number.equalsIgnoreCase("701-800")) {
            return "800";
        } else if (number.equalsIgnoreCase("801-900")) {
            return "900";
        } else if (number.equalsIgnoreCase("901-1000")) {
            return "1000";
        }
        return "500";
    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}

