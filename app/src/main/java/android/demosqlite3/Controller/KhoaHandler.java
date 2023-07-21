package android.demosqlite3.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.demosqlite3.Model.Khoa;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import javax.xml.xpath.XPath;

public class KhoaHandler extends SQLiteOpenHelper {
    public static final String DB_NAME ="qlsv3";
    public static final String PATH ="/data/data/android.demosqlite3/database/qlsv3.db";
    private static final String TABLE_NAME="Khoa";
    private static final String MKHOA_COL = "makhoa";
    private static final String TKHOA_COL = "tenkhoa";


    public KhoaHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql ="CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + MKHOA_COL+ " INTEGER NOT NULL UNIQUE," +
                TKHOA_COL + " TEXT NOT NULL UNIQUE," +
                " PRIMARY KEY( " +MKHOA_COL+ "))";

        db.execSQL(sql);
        db.close();
    }
    public void initData()
    {
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('1','CNTT')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('2','TCKT')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME +" VALUES ('3','QTKD')";
        db.execSQL(sql3);
        db.close();
    }
    public ArrayList<Khoa>loadData()
    {
        ArrayList<Khoa> kq=new ArrayList<>();

        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("select *from Khoa",null);
        cursor.moveToFirst();
        do {
            Khoa k = new Khoa();
            k.setMakhoa(cursor.getInt(0));
            k.setTenkhoa(cursor.getString(1));
            kq.add(k);
        }while (cursor.moveToNext());
        return kq;
    }
    public void insertANewRecord(int makhoa, String tenkhoa)
    {
        SQLiteDatabase db =SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(MKHOA_COL,makhoa);
        values.put(TKHOA_COL,tenkhoa);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void updateKhoa(Khoa oldKhoa, Khoa newKhoa)
    {
        SQLiteDatabase db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values=new ContentValues();
        values.put(MKHOA_COL,newKhoa.getMakhoa());
        values.put(TKHOA_COL, newKhoa.getTenkhoa());
        db.update(TABLE_NAME,values,MKHOA_COL+"=?",
                new String[]{String.valueOf(oldKhoa.getMakhoa())});
        db.close();
    }









}
