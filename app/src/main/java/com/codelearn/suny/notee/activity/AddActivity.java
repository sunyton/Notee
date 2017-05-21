package com.codelearn.suny.notee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codelearn.suny.notee.R;
import com.codelearn.suny.notee.database.DbManager;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by suny on 2017/5/21.
 */

public class AddActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private EditText et_title, et_body;
    private TextView tv_time;
    DbManager manager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Calendar c = Calendar.getInstance();

        final String date = String.valueOf((int) c.getTimeInMillis());
        Log.i("TAG", String.valueOf(date));
        mToolbar = (Toolbar) findViewById(R.id.add_toolbar);
        et_title = (EditText) findViewById(R.id.add_title);
        et_body = (EditText) findViewById(R.id.add_body);
        tv_time = (TextView) findViewById(R.id.ad_time);

        c.get(Calendar.HOUR_OF_DAY);

        tv_time.setText(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));


        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);
        manager = new DbManager(this);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.add("我的笔记本", et_title.getText().toString(), et_body.getText().toString(), date);
                Intent intent = new Intent(AddActivity.this, NoteActivity.class);
                intent.putExtra("name", "我的笔记本");
                startActivity(intent);
                finish();

            }
        });
    }
}
