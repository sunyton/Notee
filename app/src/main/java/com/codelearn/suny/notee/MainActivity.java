package com.codelearn.suny.notee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codelearn.suny.notee.activity.NoteActivity;
import com.codelearn.suny.notee.adapter.MyAdapter;
import com.codelearn.suny.notee.adapter.MyBookAdapter;
import com.codelearn.suny.notee.database.DatabaseHelper;
import com.codelearn.suny.notee.database.DbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv2;
    private MyBookAdapter mMyAdapter;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private SharedPreferences sp;
    private TextView tv_book;
    private List<BookInfo> mBookInfoList = new ArrayList<>();
    private int[] img = {R.drawable.notebook1, R.drawable.notebook2, R.drawable.notebook3};
    private DatabaseHelper mDatabaseHelper;
    private DbManager mDbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDatabaseHelper = new DatabaseHelper(this);
        mDbManager = new DbManager(this);

//recyclerview设置
        rv2 = (RecyclerView) findViewById(R.id.rv);
        rv2.setLayoutManager(new GridLayoutManager(this, 2));
        query();
        mMyAdapter = new MyBookAdapter(mBookInfoList, this);

        rv2.setAdapter(mMyAdapter);
        rv2.setHasFixedSize(true);

//Navigationview,toolbar设置
        mNavigationView = (NavigationView) findViewById(R.id.nv);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl);
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_add:
                        BookInfo bookInfo = new BookInfo();
                        bookInfo.setBookName("我的笔记本" + mBookInfoList.size());
                        bookInfo.setBookImg(img[mBookInfoList.size() % 3]);
                        mBookInfoList.add(bookInfo);
                        mDbManager.add_book("我的笔记本" + mBookInfoList.size(),img[mBookInfoList.size() % 3]);
                        mMyAdapter.notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });

        mToolbar.setTitleTextColor(getResources().getColor(R.color.backgroud));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

//
    }

//    public void bookClick(View view) {
//        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
//        intent.putExtra("name", );
//        startActivity(intent);
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void query() {
        SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from book", null);
        while (cursor.moveToNext()) {
            String bookName = cursor.getString(cursor.getColumnIndex("bookname"));
            int bookimg = cursor.getInt(cursor.getColumnIndex("bookimg"));
            BookInfo info = new BookInfo();
            info.setBookImg(bookimg);
            info.setBookName(bookName);
            mBookInfoList.add(info);

        }
        cursor.close();
        db.close();
    }


}
