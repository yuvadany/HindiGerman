package com.bible.hindibible;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, AdapterView.OnItemSelectedListener {
    //  DBHelper dbhelper = new DBHelper(this);
    public int book_number = 1;
    ScrollView first,second,third;
    public HashMap chaptersMap = new HashMap<String, Integer>();
    TextView hindi_verses, english_verses, single_view;
    ListView englishList, singleList,hindiList;
    Spinner book, chapter;
    String language = "both";
    Bundle bundle = new Bundle();
    ScrollView englishview;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;
    private FloatingActionButton fabShare, fab1, fab2, fab3, fab4;
    private Boolean isFabOpen = false;
    BooksChapters chapters = new BooksChapters();
    String defaulthint = "Search here";
    ArrayList<String> listitems;
    Button closePopupBtn,shareVerse,shareVerseOnly,shareEearchResult,closeShareResult;
    PopupWindow popupWindow,popupWindowSearch;
    DrawerLayout verseLayout;
    TextView todayverse;
    String verseToday;
    DBHelper dbhelper = new DBHelper(this);
    String searchResult = "test";
    String verseSelected = "Amen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Popup Verse Starts
        verseToday = "Amen";
        Calendar cal = Calendar.getInstance();
        int doy = cal.get(Calendar.DAY_OF_YEAR);
        LayoutInflater layoutInflater = (LayoutInflater) Main2Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.versepopup,null);
        verseLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);
        shareVerse = (Button) customView.findViewById(R.id.shareVerse);
        todayverse = (TextView) customView.findViewById(R.id.versetoday);
        popupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // Getting daily verse

        // todayverse.setText("Amen");
        try {
            this.dbhelper.openDataBase();
            verseToday = dbhelper.getVerse(doy);
            todayverse.setText(verseToday);
        }catch(Exception e){

        }
        findViewById(R.id.drawer_layout).post(new Runnable() {
            public void run() {
                popupWindow.showAtLocation(findViewById(R.id.drawer_layout), Gravity.CENTER, 0, 0);
            }
        });

        //close the popup window on button click
        closePopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        shareVerse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String app_url = "https://play.google.com/store/apps/details?id=com.bible.hindibible";
                verseToday = verseToday + "\n\n"+app_url;
                try {
                    Intent localIntent2 = new Intent("android.intent.action.SEND");
                    localIntent2.setType("text/plain");
                    localIntent2.putExtra("android.intent.extra.SUBJECT", "Today's Word  आज की  वचन  #");
                    localIntent2.putExtra("android.intent.extra.TEXT", verseToday);
                    startActivity(Intent.createChooser(localIntent2, "Today's  verse Share"));
                } catch (Exception e) {

                }
            }
        });
        // Popup Verse Ends
        //share Verse/Search result starts

        //share Verse/Search result ends
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Screen width set starts Jan 23 -2018 by Yuvaraj Palanisamy
        int width = getWindowManager().getDefaultDisplay().getWidth();
        //  int height = getWindowManager().getDefaultDisplay().getHeight();
        /*first = (ScrollView) findViewById(R.id.scrollView1);
        second = (ScrollView) findViewById(R.id.scrollView2);
        third = (ScrollView) findViewById(R.id.scrollView3);*/
        // Toast.makeText(getApplicationContext(), "Width " + width, Toast.LENGTH_LONG).show();
        //first.getLayoutParams().width = width / 2 - 5;
        //  second.getLayoutParams().
        /*RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) second.getLayoutParams();
        layoutParams.setMargins(width / 2 + 5, 0, 0, 0);
        second.setLayoutParams(layoutParams);*/
        //Screen width set starts Jan 23 -2018 by Yuvaraj Palanisamy Ends
        singleList = ((ListView) findViewById(R.id.SingleText));
        singleList.setVisibility(View.GONE);
        hindiList = ((ListView) findViewById(R.id.hindi_text));
        englishList = ((ListView) findViewById(R.id.english_text));
        book = (Spinner) findViewById(R.id.books_spinner);
        chapter = (Spinner) findViewById(R.id.chapters_spinner);
        /*// Chapter spinner starting point
        RelativeLayout.LayoutParams layoutParamsSpinner = (RelativeLayout.LayoutParams) second.getLayoutParams();
        layoutParamsSpinner.setMargins(width / 2, 0, 0, 0);
        chapter.setLayoutParams(layoutParamsSpinner);
        //*/
        book.setOnItemSelectedListener(this);
        chapter.setOnItemSelectedListener(this);
       // englishview = (ScrollView) findViewById(R.id.scrollView2);
        fabShare = (FloatingActionButton) findViewById(R.id.share);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String app_url = "https://play.google.com/store/apps/details?id=com.bible.hindibible";
                try {
                    Intent localIntent2 = new Intent("android.intent.action.SEND");
                    localIntent2.setType("text/plain");
                    localIntent2.putExtra("android.intent.extra.SUBJECT", "The Holy Bible Hindi & English NIV Parallel");
                    localIntent2.putExtra("android.intent.extra.TEXT", "\nHi,\n Check on this Holy Bible Hindi English Parallel App\n\n" + app_url + " \n\n");
                    startActivity(Intent.createChooser(localIntent2, "Hindi English Bible Share"));
                } catch (Exception e) {

                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        SearchView   localSearchView = (SearchView)findViewById(R.id.searchverse);
        localSearchView.setQueryHint(this.defaulthint);
        localSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String paramAnonymousString) {
                SearchView localSearchView = (SearchView) Main2Activity.this.findViewById(R.id.searchverse);
                if ((paramAnonymousString == null) || ("".equals(paramAnonymousString))) {
                    localSearchView.setQueryHint("Type a word here");
                    // Main2Activity.this.listitems = new ArrayList();
                    listitems = new ArrayList();
                    // Main2Activity.this.listitems.add("Please Search with Song name");
                   // listitems.add("Please Search with different word");
                    ArrayAdapter localArrayAdapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, Main2Activity.this.listitems);
                    ((ListView) Main2Activity.this.findViewById(R.id.searchresult)).setAdapter(localArrayAdapter);
                }
                return true;
            }

            public boolean onQueryTextSubmit(String paramAnonymousString) {
                Object localObject[] = {"No matches Found"};
              /*  SearchView   localSearchView = (SearchView)findViewById(R.id.searchverse);
                 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                 imm.hideSoftInputFromWindow(localSearchView.getWindowToken(), 0);*/
                try {
                    //  Main2Activity.this.dbhelper.openDataBase();
                 /*   dbhelper.openDataBase();                   */
                    String[] arrayOfString = searchVerse(paramAnonymousString);
                    localObject = arrayOfString;
                    searchResult = arrayToString (arrayOfString);
                } catch (Exception localException) {
                    Log.d("Db Open issue ", localException.getMessage());
                }
                ArrayAdapter localArrayAdapter;
                ListView localListView;
                Main2Activity.this.listitems = new ArrayList(Arrays.asList((Object[]) localObject));
                listitems = new ArrayList(Arrays.asList((Object[]) localObject));
                //localArrayAdapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, Main2Activity.this.listitems);
                localArrayAdapter = new ArrayAdapter(Main2Activity.this, android.R.layout.simple_list_item_1, listitems);
                localListView = (ListView) Main2Activity.this.findViewById(R.id.searchresult);
                localListView.setAdapter(localArrayAdapter);
               /* localListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle localBundle = new Bundle();
                    LayoutInflater layoutInflater = (LayoutInflater) Main2Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    verseSelected = ((TextView) view).getText().toString();
                    View customViewShare = layoutInflater.inflate(R.layout.sharesearch,null);
                    shareEearchResult = (Button) customViewShare.findViewById(R.id.shareSearchResult);
                    shareVerseOnly = (Button) customViewShare.findViewById(R.id.shareVerseOnly);
                    closeShareResult = (Button) customViewShare.findViewById(R.id.closeShareResult);
                    popupWindowSearch = new PopupWindow(customViewShare, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                *//*    shareVerseOnly.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent localIntent2 = new Intent("android.intent.action.SEND");
                                localIntent2.setType("text/plain");
                                localIntent2.putExtra("android.intent.extra.SUBJECT", "Share Word वचन  #");
                                localIntent2.putExtra("android.intent.extra.TEXT", verseSelected);
                                startActivity(Intent.createChooser(localIntent2, "Selected verse Share"));
                            } catch (Exception e) {

                            }
                        }
                    });
                    shareEearchResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent localIntent2 = new Intent("android.intent.action.SEND");
                                localIntent2.setType("text/plain");
                                localIntent2.putExtra("android.intent.extra.SUBJECT", "Share Search वचन  #");
                                localIntent2.putExtra("android.intent.extra.TEXT", searchResult);
                                startActivity(Intent.createChooser(localIntent2, "Share search Result"));
                            } catch (Exception e) {

                            }
                        }
                    });
                    closeShareResult.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindowSearch.dismiss();
                        }
                    });
                    //Toast.makeText(Main2Activity.this,  searchResult , Toast.LENGTH_SHORT).show() ;
                    findViewById(R.id.drawer_layout).post(new Runnable() {
                        public void run() {
                            popupWindowSearch.showAtLocation(findViewById(R.id.drawer_layout), Gravity.CENTER, 0, 0);

                        }
                    });*//*
                }
            });*/
                return true;
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int arg2, long arg3) { /* TODO Auto-generated method stub*/
        String sp1 = String.valueOf(book.getSelectedItem());
        String sp2 = String.valueOf(chapter.getSelectedItem());
        /*first.fullScroll(ScrollView.FOCUS_UP);
        second.fullScroll(ScrollView.FOCUS_UP);
        third.fullScroll(ScrollView.FOCUS_UP);*/
        switch (parent.getId()) {
            case R.id.books_spinner: {

                int chapters = getChapters(sp1); /*  verses.setText(sp1);*/
                List<String> list = new ArrayList<String>();
                for (int i = 0; i < chapters; i++) {
                    list.add(Integer.toString(i + 1));
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                chapter.setAdapter(dataAdapter);
                //String Kannada_verses = dbhelper.getVerses("kan_bible", getBooks(sp1),1);
                String hindi_verse = "No verses";
                //raw text test starts
                int num = 5;
                int id = 8;
                id = this.getResources().getIdentifier("hin_1_1", "raw", this.getPackageName());
                InputStream inputStream = getResources().openRawResource(id);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int in;
                try {
                    in = inputStream.read();
                    while (in != -1) {
                        byteArrayOutputStream.write(in);
                        in = inputStream.read();
                    }
                    inputStream.close();
                    // System.out.println(" Next  " + byteArrayOutputStream.toString());
                    hindi_verse = byteArrayOutputStream.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //raw text test ends
                ArrayAdapter praiseArrayAdapter5 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse("1", "1", "hin_"));
                hindiList.setAdapter(praiseArrayAdapter5);
/*                hindi_verses.setText(hindi_verse);*/
                //  String enlish_verses = dbhelper.getVerses("eng_bible", getBooks(sp1),1);
                // english_verses.setText(enlish_verses);
                String enlish_verse = "Not Found";
               // english_verses.setText(getVerse("1", "1", "niv_"));
                ArrayAdapter praiseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse("1", "1", "niv_"));
                englishList.setAdapter(praiseArrayAdapter);
                if ("hindi".equalsIgnoreCase(language)) {
                    praiseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse("1", "1", "hin_"));
                    singleList.setAdapter(praiseArrayAdapter);
                } else if ("niv".equalsIgnoreCase(language)) {
                    //single_text.setText(getVerse("1", "1", "niv_"));
                    praiseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse("1", "1", "niv_"));
                    singleList.setAdapter(praiseArrayAdapter);
                }
                break;
            }
            case R.id.chapters_spinner: {

                //third.fullScroll(ScrollView.FOCUS_UP);
                //String Kannada_verses = dbhelper.getVerses("kan_bible", getBooks(sp1),Integer.parseInt(sp2));
                String file = "hin_" + getBooks(sp1) + "_" + Integer.parseInt(sp2);
                String hindi_verse = "test ";
                int id = 1;
                id = this.getResources().getIdentifier(file, "raw", this.getPackageName());
                InputStream inputStream = getResources().openRawResource(id);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int in;
                try {
                    in = inputStream.read();
                    while (in != -1) {
                        byteArrayOutputStream.write(in);
                        in = inputStream.read();
                    }
                    inputStream.close();
                    hindi_verse = byteArrayOutputStream.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ArrayAdapter praiseArrayAdapter6 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "hin_"));
                hindiList.setAdapter(praiseArrayAdapter6);
               /* hindi_verses.setText(hindi_verse);*/
                //String enlish_verses = dbhelper.getVerses("eng_bible", getBooks(sp1),Integer.parseInt(sp2));
                // english_verses.setText(enlish_verses);
                String enlish_verse = "Not Found";
                ArrayAdapter praiseArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "niv_"));
                englishList.setAdapter(praiseArrayAdapter);
               // english_verses.setText(getVerse(sp1, sp2, "niv_"));
                if ("hindi".equalsIgnoreCase(language)) {
                    ArrayAdapter praiseArrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "hin_"));
                    singleList.setAdapter(praiseArrayAdapter1);
                   // single_text.setText(getVerse(sp1, sp2, "hin_"));
                } else if ("niv".equalsIgnoreCase(language)) {
                    ArrayAdapter praiseArrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "niv_"));
                    singleList.setAdapter(praiseArrayAdapter2);
                    //single_text.setText(getVerse(sp1, sp2, "niv_"));
                }
                break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent localIntent1;
        localIntent1 = new Intent(Main2Activity.this, Main2Activity.class);
        String sp1 = String.valueOf(book.getSelectedItem());
        String sp2 = String.valueOf(chapter.getSelectedItem());
        switch (id) {
            case R.id.fab1:
                animateFAB();
                break;
            case R.id.fab2: {
                singleList.setVisibility(View.VISIBLE);
                //single_text.setVisibility(View.VISIBLE);
                hindiList.setVisibility(View.GONE);
                englishList.setVisibility(View.GONE);
                ArrayAdapter praiseArrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "hin_"));
                singleList.setAdapter(praiseArrayAdapter2);
                language = "hindi";
                break;
            }
            case R.id.fab3: {
                this.bundle.putString("verses", "In the beginning God created the heaven and the earth.");
                singleList.setVisibility(View.VISIBLE);
                hindiList.setVisibility(View.GONE);
                englishList.setVisibility(View.GONE);
                language = "niv";
                ArrayAdapter praiseArrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "niv_"));
                singleList.setAdapter(praiseArrayAdapter2);
                break;
            }
            case R.id.fab4: {
                singleList.setVisibility(View.GONE);
                hindiList.setVisibility(View.VISIBLE);
                englishList.setVisibility(View.VISIBLE);
                ArrayAdapter praiseArrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "niv_"));
                englishList.setAdapter(praiseArrayAdapter2);
                ArrayAdapter praiseArrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getVerse(sp1, sp2, "hin_"));
                hindiList.setAdapter(praiseArrayAdapter3);
                language = "both";
                break;
            }
        }
    }

    public void animateFAB() {

        if (isFabOpen) {
            fab2.startAnimation(rotate_backward);
            // fab2.startAnimation(fab_close);
            //  fab3.startAnimation(fab_close);
            // fab4.startAnimation(fab_close);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = false;
        } else {

            fab1.startAnimation(rotate_forward);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = true;

        }
    }

    public ArrayList getVerse(String sp1, String sp2, String bible) {
        String verses = "Not Found";
        String file = bible + getBooks(sp1) + "_" + Integer.parseInt(sp2);
        ArrayList<String> versesArrayList = new ArrayList();
        int id = 1;
        String line;
        id = this.getResources().getIdentifier(file, "raw", this.getPackageName());
        InputStream inputStream = getResources().openRawResource(id);
        int in;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((line = br.readLine()) != null) {
                versesArrayList.add(line);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return versesArrayList;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.rate) {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.bible.hindibible"));
            startActivity(intent);
        }else if (id == R.id.more) {
           Intent intent=new Intent(Intent.ACTION_VIEW);
           intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=YUVARAJ+PALANISAMY"));
           startActivity(intent);
       }
       else if (id == R.id.praises) {
           Intent localIntent = new Intent(Main2Activity.this, PraisesActivity.class);
           Main2Activity.this.startActivity(localIntent);
       }
       else if (id == R.id.nav_share) {
            String app_url = "https://play.google.com/store/apps/details?id=com.bible.hindibible";
            try {
                Intent localIntent2 = new Intent("android.intent.action.SEND");
                localIntent2.setType("text/plain");
                localIntent2.putExtra("android.intent.extra.SUBJECT", "Hindi English Bible ");
                localIntent2.putExtra("android.intent.extra.TEXT", "\nHi,\n Check on this  Hindi English Parallel Holy Bible App\n\n" + app_url + " \n\n");
                startActivity(Intent.createChooser(localIntent2, "Hindi Bible Share "));
            } catch (Exception e) {

            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


   // }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stu

    }

    public String[] searchVerse (String verse ) {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        String verses = "Not Found";
        StringBuffer sb = new StringBuffer();
        String file = "niv_1_1";
        String[] words = new String[2];

        int id = 1;
        words[0] = verse.substring(0, 1).toUpperCase() + verse.substring(1).toLowerCase();
        words[1] = verse.substring(0, 1).toLowerCase() + verse.substring(1).toLowerCase();
        //Toast.makeText(Main2Activity.this, words[1], Toast.LENGTH_SHORT).show();
       /* for (int m=0; m<words.length; m++)
        {*/
        // verse = words[m];

        for (int i = 1; i <= 66; i++) {
            for (int j = 1; j <= chapters.getChaptersCount(i); j++) {
                file = "niv_" + i + "_" + j;
                id = this.getResources().getIdentifier(file, "raw", this.getPackageName());
                //  Toast.makeText(Main2Activity.this,  this.getPackageName(), Toast.LENGTH_SHORT).show();
                InputStream inputStream = getResources().openRawResource(id);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int in;
                String line;
                String result;
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    while ((line = br.readLine()) != null) {
                        if (line.contains(words[0]) || line.contains(words[1])) {
                            sb.append(chapters.getBookName(i) + ":" + j + "\n" + line + "\n");
                        }
                    }
                    verses = sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Toast.makeText(Main2Activity.this, msg, Toast.LENGTH_LONG).show();
        for (int i = 1; i <= 66; i++) {
            for (int j = 1; j <= chapters.getChaptersCount(i); j++) {
                file = "hin_" + i + "_" + j;
                id = this.getResources().getIdentifier(file, "raw", this.getPackageName());
                //  Toast.makeText(Main2Activity.this,  this.getPackageName(), Toast.LENGTH_SHORT).show();
                InputStream inputStream = getResources().openRawResource(id);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int in;
                String line;
                String result;
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    while ((line = br.readLine()) != null) {
                        if (line.contains(words[0]) || line.contains(words[1])) {
                            sb.append(chapters.getBookName(i) + ":" + j + "\n" + line + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(null == sb.toString() || "".equalsIgnoreCase(sb.toString())) {
            Toast.makeText(Main2Activity.this, " No match found" , Toast.LENGTH_SHORT).show();
        } else {
            sb.append("\n\n\n");
        }
        return stringbufferToArray(sb);

    }

    public String[] stringbufferToArray(StringBuffer paramStringBuffer)
    {
        ArrayList localArrayList = new ArrayList();
        StringTokenizer localStringTokenizer = new StringTokenizer(paramStringBuffer.toString(), "\n");
        while (localStringTokenizer.hasMoreTokens()) {
            localArrayList.add(localStringTokenizer.nextToken());
        }
        int i = localArrayList.size();
        String[] arrayOfString = new String[i];
        Iterator localIterator = localArrayList.iterator();
        for (int j = 0; (localIterator.hasNext()) && (i - 1 >= 0); j++)
        {
            arrayOfString[j] = localIterator.next().toString();
            i--;
        }
        return arrayOfString;
    }
    public int getChapters(String bookName)
    {
        int chapters=1;
        if (bookName.equalsIgnoreCase("उत्पत्ति-Genesis"))
        {
            return 50;
        } else if (bookName.equalsIgnoreCase("निर्गमन-Exodus"))
        {  return 40;
        } else if (bookName.equalsIgnoreCase("लैव्यव्यवस्था-Leviticus"))
        {  return 27;
        } else if(bookName.equalsIgnoreCase("गिनती-Numbers"))
        {   return 36;
        } else if (bookName.equalsIgnoreCase("व्यवस्थाविवरण-Deuteronomy")) {
            return 34;
        }else if (bookName.equalsIgnoreCase("यहोशू-Joshua"))
        {    return 24;
        } else if (bookName.equalsIgnoreCase("न्यायियों-Judges"))
        {  return 21;
        } else if (bookName.equalsIgnoreCase("रूत-Ruth"))
        {  return 4;
        } else if (bookName.equalsIgnoreCase("1 शमूएल-1 Samuel"))
        {   return 31;
        }else if (bookName.equalsIgnoreCase("2 शमूएल-2 Samuel"))
        {   return 24;
        }else if (bookName.equalsIgnoreCase("1 राजा-1 Kings"))
        {   return 22;
        }    else if (bookName.equalsIgnoreCase("2 राजा-2 Kings"))
        {   return 25;
        } else if (bookName.equalsIgnoreCase("1 इतिहास-1 Chronicles"))
        {   return 29;
        } else if (bookName.equalsIgnoreCase("2 इतिहास-2 Chronicles"))
        {   return 36;
        } else if (bookName.equalsIgnoreCase("एज्रा-Ezra"))
        { return 10;
        } else if (bookName.equalsIgnoreCase("नहेमायाह-Nehemiah"))
        { return 13;
        } else if (bookName.equalsIgnoreCase("एस्तेर-Esther"))
        { return 10;
        }else if (bookName.equalsIgnoreCase("अय्यूब-Job"))
        { return 42;
        } else if (bookName.equalsIgnoreCase("भजन संहिता-Psalms"))
        { return 150;
        } else if (bookName.equalsIgnoreCase("नीतिवचन-Proverbs"))
        { return 31;
        } else if (bookName.equalsIgnoreCase("सभोपदेशक-Ecclesiastes"))
        { return 12;
        } else if (bookName.equalsIgnoreCase("श्रेष्ठगीत-Song of Songs"))
        { return 8;
        } else if (bookName.equalsIgnoreCase("यशायाह-Isaiah"))
        { return 66;
        } else if (bookName.equalsIgnoreCase("यिर्मयाह-Jeremiah"))
        { return 52;
        }else if (bookName.equalsIgnoreCase("विलापगीत-Lamentations"))
        { return 5;
        } else if (bookName.equalsIgnoreCase("यहेजकेल-Ezekiel"))
        { return 48;
        } else if (bookName.equalsIgnoreCase("दानिय्येल-Daniel"))
        { return 12;
        } else if (bookName.equalsIgnoreCase("होशे-Hosea"))
        { return 14;
        } else if (bookName.equalsIgnoreCase("योएल-Joel"))
        { return 3;
        } else if (bookName.equalsIgnoreCase("आमोस-Amos"))
        { return 9;
        } else if (bookName.equalsIgnoreCase("ओबद्दाह-Obadiah"))
        { return 1;
        }else if (bookName.equalsIgnoreCase("योना-Jonah"))
        { return 4;
        } else if (bookName.equalsIgnoreCase("मीका-Micah"))
        { return 7;
        } else if (bookName.equalsIgnoreCase("नहूम-Nahum")) {
            return 3;
        } else if (bookName.equalsIgnoreCase("हबक्कूक-Habakkuk")) {
            return 3;
        } else if (bookName.equalsIgnoreCase("सपन्याह-Zephaniah"))
        { return 3;
        } else if (bookName.equalsIgnoreCase("हाग्गै-Haggai"))
        { return 2;
        } else if (bookName.equalsIgnoreCase("जकर्याह-Zechariah"))
        { return 14;
        }else if (bookName.equalsIgnoreCase("मलाकी-Malachi"))
        { return 4;
        }    else if (bookName.equalsIgnoreCase("मत्ती-Matthew"))
        { return 28;
        }    else if (bookName.equalsIgnoreCase("मरकुस-Mark"))
        { return 16;
        }else if (bookName.equalsIgnoreCase("लूका-Luke"))
        { return 24;
        }else if (bookName.equalsIgnoreCase("यूहन्ना-John"))
        { return 21;
        }else if (bookName.equalsIgnoreCase("प्रेरितों के काम-Acts"))
        { return 28;
        }    else if (bookName.equalsIgnoreCase("रोमियो-Romans"))
        { return 16;
        }    else if (bookName.equalsIgnoreCase("1 कुरिन्थियों-1 Corinthians"))
        { return 16;
        }else if (bookName.equalsIgnoreCase("2 कुरिन्थियों-2 Corinthians"))
        { return 13;
        }else if (bookName.equalsIgnoreCase("गलातियों-Galatians"))
        { return 6;
        }else if (bookName.equalsIgnoreCase("इफिसियों-Ephesians"))
        { return 6;
        }else if (bookName.equalsIgnoreCase("फिलिप्पियों-Philippians"))
        { return 4;
        }else if (bookName.equalsIgnoreCase("कुलुस्सियों-Colossians"))
        { return 4;
        }else if (bookName.equalsIgnoreCase("1 थिस्सलुनीकियों-1 Thessalonians"))
        { return 5;
        }else if (bookName.equalsIgnoreCase("2 थिस्सलुनीकियों-2 Thessalonians"))
        { return 3;
        }else if (bookName.equalsIgnoreCase("1 तीमुथियुस-1 Timothy"))
        { return 6;
        }else if (bookName.equalsIgnoreCase("2 तीमुथियुस-2 Timothy"))
        { return 4;
        }else if (bookName.equalsIgnoreCase("तीतुस-Titus"))
        { return 3;
        }else if (bookName.equalsIgnoreCase("फिलेमोन-Philemon"))
        { return 1;
        }else if (bookName.equalsIgnoreCase("इब्रानियों-Hebrews"))
        { return 13;
        }else if (bookName.equalsIgnoreCase("याकूब-James"))
        { return 5;
        }else if (bookName.equalsIgnoreCase("1 पतरस-1 Peter"))
        { return 5;
        }else if (bookName.equalsIgnoreCase("2 पतरस-2 Peter"))
        { return 3;
        }else if (bookName.equalsIgnoreCase("1 यूहन्ना-1 John"))
        { return 5;
        }else if (bookName.equalsIgnoreCase("2 यूहन्ना-2 John"))
        { return 1;
        }else if (bookName.equalsIgnoreCase("3 यूहन्ना-3 John"))
        {  return 1;
        }    else if (bookName.equalsIgnoreCase("यहूदा-Jude"))
        {  return 1;
        }    else if (bookName.equalsIgnoreCase("प्रकाशित वाक्य-Revelation"))
        {  return 22;
        }
        return chapters;
    }


    public int getBooks(String bookName)
    {
        int books=1;
        if (bookName.equalsIgnoreCase("उत्पत्ति-Genesis"))
        {
            return 1;
        } else if (bookName.equalsIgnoreCase("निर्गमन-Exodus"))
        {  return 2;
        } else if (bookName.equalsIgnoreCase("लैव्यव्यवस्था-Leviticus"))
        {  return 3;
        } else if(bookName.equalsIgnoreCase("गिनती-Numbers"))
        {   return 4;
        } else if (bookName.equalsIgnoreCase("व्यवस्थाविवरण-Deuteronomy")) {
            return 5;
        }else if (bookName.equalsIgnoreCase("यहोशू-Joshua"))
        {    return 6;
        } else if (bookName.equalsIgnoreCase("न्यायियों-Judges"))
        {  return 7;
        } else if (bookName.equalsIgnoreCase("रूत-Ruth"))
        {  return 8;
        } else if (bookName.equalsIgnoreCase("1 शमूएल-1 Samuel"))
        {   return 9;
        }else if (bookName.equalsIgnoreCase("2 शमूएल-2 Samuel"))
        {   return 10;
        }else if (bookName.equalsIgnoreCase("1 राजा-1 Kings"))
        {   return 11;
        }    else if (bookName.equalsIgnoreCase("2 राजा-2 Kings"))
        {   return 12;
        } else if (bookName.equalsIgnoreCase("1 इतिहास-1 Chronicles"))
        {   return 13;
        } else if (bookName.equalsIgnoreCase("2 इतिहास-2 Chronicles"))
        {   return 14;
        } else if (bookName.equalsIgnoreCase("एज्रा-Ezra"))
        { return 15;
        } else if (bookName.equalsIgnoreCase("नहेमायाह-Nehemiah"))
        { return 16;
        } else if (bookName.equalsIgnoreCase("एस्तेर-Esther"))
        { return 17;
        }else if (bookName.equalsIgnoreCase("अय्यूब-Job"))
        { return 18;
        } else if (bookName.equalsIgnoreCase("भजन संहिता-Psalms"))
        { return 19;
        } else if (bookName.equalsIgnoreCase("नीतिवचन-Proverbs"))
        { return 20;
        } else if (bookName.equalsIgnoreCase("सभोपदेशक-Ecclesiastes"))
        { return 21;
        } else if (bookName.equalsIgnoreCase("श्रेष्ठगीत-Song of Songs"))
        { return 22;
        } else if (bookName.equalsIgnoreCase("यशायाह-Isaiah"))
        { return 23;
        } else if (bookName.equalsIgnoreCase("यिर्मयाह-Jeremiah"))
        { return 24;
        }else if (bookName.equalsIgnoreCase("विलापगीत-Lamentations"))
        { return 25;
        } else if (bookName.equalsIgnoreCase("यहेजकेल-Ezekiel"))
        { return 26;
        } else if (bookName.equalsIgnoreCase("दानिय्येल-Daniel"))
        { return 27;
        } else if (bookName.equalsIgnoreCase("होशे-Hosea"))
        { return 28;
        } else if (bookName.equalsIgnoreCase("योएल-Joel"))
        { return 29;
        } else if (bookName.equalsIgnoreCase("आमोस-Amos"))
        { return 30;
        } else if (bookName.equalsIgnoreCase("ओबद्दाह-Obadiah"))
        { return 31;
        }else if (bookName.equalsIgnoreCase("योना-Jonah"))
        { return 32;
        } else if (bookName.equalsIgnoreCase("मीका-Micah"))
        { return 33;
        } else if (bookName.equalsIgnoreCase("नहूम-Nahum")) {
            return 34;
        } else if (bookName.equalsIgnoreCase("हबक्कूक-Habakkuk")) {
            return 35;
        } else if (bookName.equalsIgnoreCase("सपन्याह-Zephaniah"))
        { return 36;
        } else if (bookName.equalsIgnoreCase("हाग्गै-Haggai"))
        { return 37;
        } else if (bookName.equalsIgnoreCase("जकर्याह-Zechariah"))
        { return 38;
        }else if (bookName.equalsIgnoreCase("मलाकी-Malachi"))
        { return 39;
        }    else if (bookName.equalsIgnoreCase("मत्ती-Matthew"))
        { return 40;
        }    else if (bookName.equalsIgnoreCase("मरकुस-Mark"))
        { return 41;
        }else if (bookName.equalsIgnoreCase("लूका-Luke"))
        { return 42;
        }else if (bookName.equalsIgnoreCase("यूहन्ना-John"))
        { return 43;
        }else if (bookName.equalsIgnoreCase("प्रेरितों के काम-Acts"))
        { return 44;
        }    else if (bookName.equalsIgnoreCase("रोमियो-Romans"))
        { return 45;
        }    else if (bookName.equalsIgnoreCase("1 कुरिन्थियों-1 Corinthians"))
        { return 46;
        }else if (bookName.equalsIgnoreCase("2 कुरिन्थियों-2 Corinthians"))
        { return 47;
        }else if (bookName.equalsIgnoreCase("गलातियों-Galatians"))
        { return 48;
        }else if (bookName.equalsIgnoreCase("इफिसियों-Ephesians"))
        { return 49;
        }else if (bookName.equalsIgnoreCase("फिलिप्पियों-Philippians"))
        { return 50;
        }else if (bookName.equalsIgnoreCase("कुलुस्सियों-Colossians"))
        { return 51;
        }else if (bookName.equalsIgnoreCase("1 थिस्सलुनीकियों-1 Thessalonians"))
        { return 52;
        }else if (bookName.equalsIgnoreCase("2 थिस्सलुनीकियों-2 Thessalonians"))
        { return 53;
        }else if (bookName.equalsIgnoreCase("1 तीमुथियुस-1 Timothy"))
        { return 54;
        }else if (bookName.equalsIgnoreCase("2 तीमुथियुस-2 Timothy"))
        { return 55;
        }else if (bookName.equalsIgnoreCase("तीतुस-Titus"))
        { return 56;
        }else if (bookName.equalsIgnoreCase("फिलेमोन-Philemon"))
        { return 57;
        }else if (bookName.equalsIgnoreCase("इब्रानियों-Hebrews"))
        { return 58;
        }else if (bookName.equalsIgnoreCase("याकूब-James"))
        { return 59;
        }else if (bookName.equalsIgnoreCase("1 पतरस-1 Peter"))
        { return 60;
        }else if (bookName.equalsIgnoreCase("2 पतरस-2 Peter"))
        { return 61;
        }else if (bookName.equalsIgnoreCase("1 यूहन्ना-1 John"))
        { return 62;
        }else if (bookName.equalsIgnoreCase("2 यूहन्ना-2 John"))
        { return 63;
        }else if (bookName.equalsIgnoreCase("3 यूहन्ना-3 John"))
        {  return 64;
        }    else if (bookName.equalsIgnoreCase("यहूदा-Jude"))
        {  return 65;
        }    else if (bookName.equalsIgnoreCase("प्रकाशित वाक्य-Revelation"))
        {  return 66;
        }
        return books;
    }

    public String arrayToString(String[] array)
    {
        StringBuilder builder = new StringBuilder();
        for(String s : array) {
            builder.append(s+"\n");
        }
        String searchresult = builder.toString();
        return searchresult;
    }
}
