package com.example.studenttrack;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    public static final String name = "Student_InfoNew.db";
    private static final String ID_1 = "id";


    public DBhelper(Context context) {

        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table students_details(" + ID_1 + " INTEGER primary key , NAME TEXT,DEPARTMENT TEXT,SEMESTER TEXT,DATE TEXT,Phone_Number INTEGER)");
        db.execSQL("Create table eti_marks(ID INTEGER primary key,UT1 TEXT,UT2 TEXT,THEORY TEXT)");
        db.execSQL("Create table mad_marks(ID INTEGER primary key, UT1 TEXT,UT2 TEXT,THEORY TEXT)");
        db.execSQL("Create table wdp_marks(ID INTEGER primary key, UT1 TEXT,UT2 TEXT,THEORY TEXT)");
        db.execSQL("Create table pwp_marks(ID INTEGER primary key, UT1 TEXT,UT2 TEXT,THEORY TEXT)");
        db.execSQL("Create table madAttendence(ID INTEGER primary key,Attendence TEXT )");
        db.execSQL("Create table etiAttendence(ID INTEGER primary key,Attendence TEXT )");
        db.execSQL("Create table pwpAttendence(ID INTEGER primary key,Attendence TEXT )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists students_details");
        db.execSQL("Drop table if exists eti_marks");
        db.execSQL("Drop table if exists mad_marks");
        db.execSQL("Drop table if exists wdp_marks");
        db.execSQL("Drop table if exists pwp_marks");
        db.execSQL("Drop table if exists madAttendence");
        db.execSQL("Drop table if exists etiAttendence");
        db.execSQL("Drop table if exists pwpAttendence");
    }
    //Methodd to add the Student Details to the Database

    Boolean insertStudent(String id, String name, String department, String semester, String date, String phone) {

        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues student = new ContentValues();
        student.put("id", id);
        student.put("Name", name);
        student.put("Department", department);
        student.put("Semester", semester);
        student.put("Date", date);
        student.put("Phone_Number", phone);
        long result = myDb.insert("students_details", null, student);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    Boolean insertEtiMarks(String id,String unit1,String unit2,String Theory){
        SQLiteDatabase mydb = getWritableDatabase();
        ContentValues Etimarks = new ContentValues();
        Etimarks.put("ID",id);
        Etimarks.put("UT1",unit1);
        Etimarks.put("UT2",unit2);
        Etimarks.put("THEORY",Theory);
        long result = mydb.insert("eti_marks",null,Etimarks);
        if (result==-1){
            return true;
        }else {
            return false;
        }

    }



    Boolean insertMadMarks(String id,String unit1,String unit2,String Theory){
        SQLiteDatabase mydb = getWritableDatabase();
        ContentValues Madmarks = new ContentValues();
        Madmarks.put("ID",id);
        Madmarks.put("UT1",unit1);
        Madmarks.put("UT2",unit2);
        Madmarks.put("THEORY",Theory);
        long result = mydb.insert("mad_marks",null,Madmarks);
        if (result==-1){
            return true;
        }else {
            return false;
        }

    }

    Boolean insertPwpMarks(String id,String unit1,String unit2,String Theory){
        SQLiteDatabase mydb = getWritableDatabase();
        ContentValues Pwpmarks = new ContentValues();
        Pwpmarks.put("ID",id);
        Pwpmarks.put("UT1",unit1);
        Pwpmarks.put("UT2",unit2);
        Pwpmarks.put("THEORY",Theory);
        long result = mydb.insert("pwp_marks",null,Pwpmarks);
        if (result==-1){
            return true;
        }else {
            return false;
        }

    }
    Boolean insertMADAttendence(String id, String Attendence) {
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("ATTENDENCE", Attendence);
        long result = myDb.insert("madAttendence", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }


    Boolean insertETIAttendence(String id, String Attendence) {
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("ATTENDENCE", Attendence);
        long result = myDb.insert("etiAttendence", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }

    Boolean insertPWPAttendence(String id, String Attendence) {
        SQLiteDatabase myDb = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Id", id);
        contentValues.put("ATTENDENCE", Attendence);
        long result = myDb.insert("pwpAttendence", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }


    public Boolean CheckDuplicateID(String id) {
        SQLiteDatabase myDB = getWritableDatabase();
        Cursor cursor = myDB.rawQuery("Select * from students_details where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }


    @SuppressLint("Range")
    public ArrayList<String> getPrimarykey() {
        SQLiteDatabase myDb = getReadableDatabase();
        ArrayList<String> getPrimarykey = new ArrayList<>();
        Cursor cursor = myDb.query("students_details", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            getPrimarykey.add(cursor.getString(cursor.getColumnIndex(ID_1)));
            cursor.moveToNext();
        }


        cursor.close();
        return getPrimarykey;
    }

    public int CountStudent() {

        SQLiteDatabase myDB = getReadableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT COUNT(*) FROM students_details;", null);
        cursor.moveToFirst();
        Integer count = cursor.getInt(0);
        cursor.close();
        myDB.close();

        return count;
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id,NAME,DEPARTMENT from students_details", null);
        return cursor;
    }

    public Cursor searchData(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM students_details WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public Cursor searchETI(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT UT1,UT2,THEORY FROM eti_marks WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public Cursor searchMAD(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT UT1,UT2,THEORY FROM mad_marks WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public Cursor searchWDP(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT UT1,UT2,THEORY FROM wdp_marks WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public Cursor searchPWP(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT UT1,UT2,THEORY FROM pwp_marks WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }




    public Cursor searchETIAttendence(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Attendence FROM etiAttendence WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }
    public Cursor searchMADAttendence(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Attendence FROM madAttendence WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }
    public Cursor searchPWPAttendence(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Attendence FROM pwpAttendence WHERE id=?";
        Cursor cursor = db.rawQuery(query, new String[]{studentId});
        return cursor;
    }

    public void deleteData(String studentID) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM  students_details WHERE id=?;", new String[]{studentID});
        sqLiteDatabase.execSQL("DELETE FROM  mad_marks WHERE id=?;",new String[]{studentID});
        sqLiteDatabase.execSQL("DELETE FROM  eti_marks WHERE id=?;",new String[]{studentID});
        sqLiteDatabase.execSQL("DELETE FROM  pwp_marks WHERE id=?;",new String[]{studentID});
        sqLiteDatabase.execSQL("DELETE FROM  pwpAttendence WHERE id=?;",new String[]{studentID});
        sqLiteDatabase.execSQL("DELETE FROM  madAttendence WHERE id=?;",new String[]{studentID});
        sqLiteDatabase.execSQL("DELETE FROM  etiAttendence WHERE id=?;",new String[]{studentID});
    }
}