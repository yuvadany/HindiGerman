package com.bible.hindibible;

/**
 * Created by user on 5/15/2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DBHelper
        extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "dailyversehindi.sqlite";
    private static final int DATABASE_VERSION = 1;
    private static final String DB_PATH_SUFFIX = "/databases/";
    static Context ctx;
    Bundle bundle = new Bundle();

    public DBHelper(Context paramContext)
    {
        super(paramContext, "dailyversehindi.sqlite", null, 1);
        ctx = paramContext;
    }

    private static String getDatabasePath()
    {
        return ctx.getApplicationInfo().dataDir + "/databases/" + "dailyversehindi.sqlite";
    }

    public void CopyDataBaseFromAsset()
            throws IOException
    {
        InputStream localInputStream = ctx.getAssets().open("dailyversehindi.sqlite");
        String str = getDatabasePath();
        File localFile = new File(ctx.getApplicationInfo().dataDir + "/databases/");
        if (!localFile.exists()) {
            localFile.mkdir();
        }
        FileOutputStream localFileOutputStream = new FileOutputStream(str);
        byte[] arrayOfByte = new byte['Ѐ'];
        for (;;)
        {
            int i = localInputStream.read(arrayOfByte);
            if (i <= 0) {
                break;
            }
            localFileOutputStream.write(arrayOfByte, 0, i);
        }
        localFileOutputStream.flush();
        localFileOutputStream.close();
        localInputStream.close();
    }


    public ArrayList getVerse(int doy)
    {
        try {
            CopyDataBaseFromAsset();
        }
        catch(Exception e )
        {
            System.out.println("Error in updateVersesDate");
        }
        int day = doy;
        ArrayList<String> dateAndVerse = new ArrayList<String>();
        String verse = "Amen";
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT verse,date  FROM verses where id ="+doy, null);
        while (localCursor.moveToNext()) {
            dateAndVerse.add( localCursor.getString(0));
            dateAndVerse.add( localCursor.getString(1));
            System.out.println(dateAndVerse.add( localCursor.getString(0) + " id and Date "
                    +dateAndVerse.add( localCursor.getString(1))));
        }
        return  dateAndVerse;
    }

    public boolean updateVersesDate(String doy,String today) {
        // openDataBase();
        try {
            //CopyDataBaseFromAsset();
            ContentValues contentValues = new ContentValues();
            contentValues.put("date", today);
            getWritableDatabase().update("verses", contentValues, "ID = ?", new String[]{doy});
            getWritableDatabase().close();
            return true;
        }
        catch(Exception e )
        {
            System.out.println("Error in updateVersesDate");
        }
        return true;
    }


    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {}

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}

    public SQLiteDatabase openDataBase()
    {

        File localFile = ctx.getDatabasePath("dailyversehindi.sqlite");

        try
        {
            if (!localFile.exists()) {CopyDataBaseFromAsset(); }
            //the below block commented to addres Favorite table refresh on each application cloing time
            // CopyDataBaseFromAsset();
            return SQLiteDatabase.openDatabase(localFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch (IOException localIOException)
        {
            throw new RuntimeException("Error creating source database", localIOException);
        }
    }

    public String getPraises(String  id)
    {
        try {
            CopyDataBaseFromAsset();
        }
        catch(Exception e )
        {
            System.out.println("Error in updateVersesDate");
        }
        int number2 = Integer.parseInt(id)-99;
        String from = String.valueOf(number2);
        StringBuffer sf = new StringBuffer();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT id,praise,reference FROM praisesenglishkjvniv where id between " + number2 + " and " + id, null);
        while (localCursor.moveToNext()) {
            sf.append("\n"+localCursor.getString(0) +".  " + localCursor.getString(1) + "  ( "+ localCursor.getString(2) +" )\n" ) ;
        }
        return sf.toString();
    }

    public String[] getSongDetails()
    {
        try {
            CopyDataBaseFromAsset();
        }
        catch(Exception e )
        {
            System.out.println("Error in updateVersesDate");
        }
        ArrayList localArrayList = new ArrayList();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT TITLE FROM ENGLISHSONGS ORDER BY title", null);
        int i=1;
        while (localCursor.moveToNext()) {
            localArrayList.add(i+"."+localCursor.getString(0));
            i++;
        }
        return (String[])localArrayList.toArray(new String[localArrayList.size()]);
    }

    public String getLyrics(String title)
    {
        Cursor localCursor = getReadableDatabase().rawQuery("Select  title,lyrics from ENGLISHSONGS where title ='" + title + "'", null);
        int i = 0;
        String str = new String();
        while (localCursor.moveToNext())
        {
            str =  localCursor.getString(0) + "\n" +localCursor.getString(1) ;
        }
        return str;
    }
    public String[] getPraises()
    {
        try {
            CopyDataBaseFromAsset();
        }
        catch(Exception e )
        {
            System.out.println("Error in getPraises() - Hindi Praises");
        }
        String verse = "Amen";
        ArrayList<String> praiseArrayList = new ArrayList();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT id,praise,reference  FROM praisesHindi", null);
        while (localCursor.moveToNext()) {
            praiseArrayList.add(localCursor.getString(0)+". "+localCursor.getString(1) +" ("+localCursor.getString(2)+" )");
        }
        return (String[])praiseArrayList.toArray(new String[praiseArrayList.size()]);
    }
    public ArrayList searchSong(String word)
    {
        System.out.println(" Keyword # "+word);
        try {
            CopyDataBaseFromAsset();
        }
        catch(Exception e )
        {
            System.out.println("Error in updateVersesDate");
        }
        ArrayList localArrayList = new ArrayList();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT title FROM ENGLISHSONGS where  " +
                "title like '%"+word+"%'", null);
        while (localCursor.moveToNext()) {
            localArrayList.add(localCursor.getString(0));
        }
        return localArrayList;
    }
}

