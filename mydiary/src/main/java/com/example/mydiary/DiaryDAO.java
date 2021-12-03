package com.example.mydiary;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class DiaryDAO {
    DBHelper dbHelper;
    String tableName = "memo";

    //목록조회
    public static ArrayList<DiaryVO> selectAll (DBHelper dbHelper) {
        ArrayList<DiaryVO> list = new ArrayList<DiaryVO>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select _id, title, content, time, img from diary order by _id desc";
        Cursor cursor = db.rawQuery(sql, null);
        while ( cursor.moveToNext()){
            DiaryVO diaryVO = new DiaryVO();
            diaryVO.setId(cursor.getString(0));
            diaryVO.setTitle(cursor.getString(1));
            diaryVO.setContent(cursor.getString(2));
            diaryVO.setTime(cursor.getString(3));
            diaryVO.setImg(cursor.getString(4));
            list.add(diaryVO);
        }
        db.close();
        return list;
    }
    //등록
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insert(DBHelper dbHelper, DiaryVO diaryVO){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", diaryVO.getTitle());
        contentValues.put("content", diaryVO.getContent());
        if(diaryVO.getImg() != null) {
            contentValues.put("img", diaryVO.getImg());
        }

        //현재시간 설정
        LocalDate dt  = LocalDate.now();
        String sdt = dt.format(DateTimeFormatter.ISO_DATE);
        contentValues.put("time", sdt);

        db.insert("diary",null, contentValues);
        db.close();
    }
    public  static  void delete(DBHelper dbHelper, String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("diary", "_id=?", new String[]{id});
        db.close();
    };
    public  static  void update(DBHelper dbHelper, DiaryVO diaryVO){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", diaryVO.getTitle());
        contentValues.put("content", diaryVO.getContent());
        if(diaryVO.getImg() != null) {
            contentValues.put("img", diaryVO.getImg());
        }
        db.update("diary", contentValues, "_id=?", new String[]{diaryVO.getId()});
        db.close();
    }

}
