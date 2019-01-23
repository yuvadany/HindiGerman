package com.bible.hindibible;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class EnglishSongsActivity extends AppCompatActivity {
    ArrayList<String> listitems;
    Bundle bundle = new Bundle();
    DBHelper dbhelper = new DBHelper(this);
    String[] arrayOfString= { "Jesus, name above all names" };
    ArrayAdapter localArrayAdapter;
    private AdView mAdView;
    String defaulthint = "Search here";
    ArrayList songsList = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_songs);
        setTitle("Worship Songs");
        ListView localListView;
        try
        {
            Object localObject[] = { "No Songs Found" };
            this.dbhelper.openDataBase();
            arrayOfString = dbhelper.getSongDetails();
            localListView = (ListView)findViewById(R.id.listviewallsongs);
            localArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfString);
            localListView.setAdapter(localArrayAdapter);
            localListView.setOnItemClickListener(this.myOnItemClickListener);
        }catch (Exception localException) {
            System.out.println("Error...  # " + localException.getMessage());
        }
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
        // Back button starts
        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // Back button ends
        // search starts

        SearchView localSearchView = (SearchView)findViewById(R.id.searchSong);
        localSearchView.setQueryHint(this.defaulthint);
        localSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String paramAnonymousString) {
                SearchView localSearchView = (SearchView) EnglishSongsActivity.this.findViewById(R.id.searchSong);
                if ((paramAnonymousString == null) || ("".equals(paramAnonymousString))) {
                    localSearchView.setQueryHint("Please Search with other word");
                    songsList = new ArrayList();
                    songsList.add("Please Search with different word");
                    ArrayAdapter localArrayAdapter = new ArrayAdapter(EnglishSongsActivity.this, android.R.layout.simple_list_item_1, EnglishSongsActivity.this.songsList);
                    ((ListView) EnglishSongsActivity.this.findViewById(R.id.searchresult)).setAdapter(localArrayAdapter);
                }
                return true;
            }

            public boolean onQueryTextSubmit(String paramAnonymousString) {
                Object localObject[] = {"No matches Found"};
                songsList = new ArrayList();

                try {
                    //  SongsActivity.this.dbhelper.openDataBase();
                 /*   dbhelper.openDataBase();                   */
                    songsList = dbhelper.searchSong(paramAnonymousString);
                } catch (Exception localException) {
                    Log.d("Db Open issue ", localException.getMessage());
                }
                ArrayAdapter localArrayAdapter;
                ListView localListView;
                //localArrayAdapter = new ArrayAdapter(SongsActivity.this, android.R.layout.simple_list_item_1, SongsActivity.this.listitems);
                localArrayAdapter = new ArrayAdapter(EnglishSongsActivity.this, android.R.layout.simple_list_item_1, songsList);
                localListView = (ListView) EnglishSongsActivity.this.findViewById(R.id.searchresult);
                localListView.setAdapter(localArrayAdapter);
                localListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle localBundle = new Bundle();
                        String song = ((TextView) view).getText().toString();
                        //Toast.makeText(getBaseContext(), story, Toast.LENGTH_LONG).show();
                        Intent localIntent = new Intent(EnglishSongsActivity.this, LyricsActivity.class);
                        localBundle.putString("title", song);
                        String lyrics = dbhelper.getLyrics(song);
                        localBundle.putString("lyrics", lyrics);
                        localIntent.putExtras(localBundle);
                        EnglishSongsActivity.this.startActivity(localIntent);
                    }
                });
                return true;
            }
        });
        //search ends
    }
    AdapterView.OnItemClickListener myOnItemClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
            String title = paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt).toString();
            int number = title.indexOf(".");
            title = title.substring(number+1);
            Bundle localBundle = new Bundle();
            String lyrics = dbhelper.getLyrics(title);
            Intent localIntent = new Intent(EnglishSongsActivity.this, LyricsActivity.class);
            localBundle.putString("title", title);
            localBundle.putString("lyrics", lyrics);
            localIntent.putExtras(localBundle);
            EnglishSongsActivity.this.startActivity(localIntent);
        }
    };
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
    // back option starts
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    // back option ends
}
