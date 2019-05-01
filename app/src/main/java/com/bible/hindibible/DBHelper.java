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
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        super(paramContext, DATABASE_NAME, null, 1);
        ctx = paramContext;
    }

    private static String getDatabasePath()
    {
        return ctx.getApplicationInfo().dataDir + "/databases/" + DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset()
            throws IOException
    {
        InputStream localInputStream = ctx.getAssets().open(DATABASE_NAME);
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
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
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
            File localFile = ctx.getDatabasePath(DATABASE_NAME);
            try {
                if (!localFile.exists()) {
                    CopyDataBaseFromAsset();
                }
            } catch (Exception e) {
                System.out.println("Error in saveBookmark");
            }
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

        File localFile = ctx.getDatabasePath(DATABASE_NAME);
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
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
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
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
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
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
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
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
        }
        ArrayList localArrayList = new ArrayList();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT title FROM ENGLISHSONGS where  " +
                "title like '%"+word+"%'", null);
        while (localCursor.moveToNext()) {
            localArrayList.add(localCursor.getString(0));
        }
        return localArrayList;
    }

    public void saveBookmark(String word) {
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
        }
        Log.i("myTag", "This is my message word" + word);
        ArrayList localArrayList = new ArrayList();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("verse", word);
        localContentValues.put("date", getCurrentDate());
        try {
            getWritableDatabase().insertOrThrow("bookmark", null, localContentValues);
            getWritableDatabase().close();
        } catch (Exception exception) {
            Log.i("myTag", "saveBookmark Exception #  " + exception);
        }
    }

    public String[] getAllBookmarks() {
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in saveBookmark");
        }
        ArrayList localArrayList = new ArrayList();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT VERSE,DATE FROM BOOKMARK order by DATE desc", null);
        while (localCursor.moveToNext()) {
            localArrayList.add(localCursor.getString(1) + "# \n" + localCursor.getString(0));
        }
        return (String[]) localArrayList.toArray(new String[localArrayList.size()]);
    }

    public void deleteBookmark(String selectedBookmark) {
        File localFile = ctx.getDatabasePath(DATABASE_NAME);
        String whereClause = "verse=?";
        try {
            if (!localFile.exists()) {
                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            System.out.println("Error in deleteBookmark");
        }
        try {
            getWritableDatabase().delete("bookmark", whereClause, new String[]{selectedBookmark});
            getWritableDatabase().close();
        } catch (Exception exception) {
            Log.i("myTag", "saveBookmark Exception #  " + exception);
        }
    }

    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss");
        return (formatter.format(date));
    }
}

