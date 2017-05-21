package com.codelearn.suny.notee;

import android.content.Intent;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.codelearn.suny.notee.activity.NoteActivity;
import com.codelearn.suny.notee.adapter.MyAdapter;
import com.codelearn.suny.notee.adapter.MyBookAdapter;
import com.codelearn.suny.notee.database.DbManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv2;
    private MyBookAdapter mMyAdapter;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//recyclerview设置
        rv2 = (RecyclerView) findViewById(R.id.rv);
        rv2.setLayoutManager(new GridLayoutManager(this, 2));
        mMyAdapter = new MyBookAdapter();
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

    public void bookClick(View view) {
        Intent intent = new Intent(MainActivity.this, NoteActivity.class);
        intent.putExtra("name", "我的笔记本");
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
