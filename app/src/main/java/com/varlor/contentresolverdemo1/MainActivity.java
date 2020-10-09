package com.varlor.contentresolverdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //通过ContentResolver调用ContentProvider插入一条记录
    public void insert(View view){
        //1.得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        //2.调用insert
        Uri uri = Uri.parse("content://com.varlor.contentproviderdemo2.personprovider/person/");
        ContentValues values = new ContentValues();
        values.put("name","Jack");
        uri = resolver.insert(uri, values);
        Toast.makeText(this,uri.toString(),Toast.LENGTH_SHORT).show();
    }

    public void delete(View view){
        //1.得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        //2.delete
        Uri uri = Uri.parse("content://com.varlor.contentproviderdemo2.personprovider/person/2");
        int deleteCount = resolver.delete(uri, null, null);
        Toast.makeText(this,"deleteCount="+deleteCount,Toast.LENGTH_SHORT).show();
    }

    public void update(View view){
        //1.得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        //2.执行update
        Uri uri = Uri.parse("content://com.varlor.contentproviderdemo2.personprovider/person/2");
        ContentValues values = new ContentValues();
        values.put("name","Jack2");
        int updateCount = resolver.update(uri, values, null, null);
        Toast.makeText(this,"updateCount="+updateCount,Toast.LENGTH_SHORT).show();
    }

    public void query(View view){
        //1.得到ContentResolver对象
        ContentResolver resolver = getContentResolver();
        //2.调用query，得到cursor
        Uri uri = Uri.parse("content://com.varlor.contentproviderdemo2.personprovider/person");
        Cursor cursor = resolver.query(uri, null, null, null, null);
        //3.取出cursor中的数据
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Toast.makeText(this,id+":"+name,Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}