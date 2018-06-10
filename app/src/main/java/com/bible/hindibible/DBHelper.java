package com.bible.hindibible;

/**
 * Created by user on 5/15/2018.
 */
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


    public String getVerse(int doy)
    {
        int day = doy;
        String verse = "Amen";
        ArrayList<String> localArrayList = new ArrayList();
        Cursor localCursor = getReadableDatabase().rawQuery("SELECT verse  FROM verses where id ="+doy, null);
        while (localCursor.moveToNext()) {
            verse = localCursor.getString(0);
        }
        return  verse;
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
            //  CopyDataBaseFromAsset();
            return SQLiteDatabase.openDatabase(localFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
        }
        catch (IOException localIOException)
        {
            throw new RuntimeException("Error creating source database", localIOException);
        }
    }



}

