package com.codelearn.suny.notee.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codelearn.suny.notee.MainActivity;
import com.codelearn.suny.notee.NoteInfo;
import com.codelearn.suny.notee.R;
import com.codelearn.suny.notee.adapter.MyAdapter;
import com.codelearn.suny.notee.adapter.MyDecoration;
import com.codelearn.suny.notee.database.DbManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suny on 2017/5/20.
 */

public class NoteActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MyAdapter mMyAdapter;
    private FloatingActionButton fab;
    private String bookName;
//    private List<NoteInfo> mNoteInfos = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_note);



        rv = (RecyclerView) findViewById(R.id.rv);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, AddActivity.class);
                intent.putExtra("name", bookName);
                startActivity(intent);
                finish();
            }
        });
        bookName = getIntent().getStringExtra("name");
        Task myTask = new Task(this);
        String[] bookNamelist = {bookName};
        myTask.execute(bookNamelist[0]);



    }


    public class Task extends AsyncTask<String, Void, List<NoteInfo>> {

        private Context mContext;
        public Task(Context context) {
            mContext = context;
        }

        @Override
        protected List<NoteInfo> doInBackground(String... params) {
            List<NoteInfo> list;
            DbManager dbManager = new DbManager(mContext);
            list = dbManager.query(params[0]);
            return list;
        }

        @Override
        protected void onPostExecute(List<NoteInfo> noteInfos) {
            if (noteInfos == null) {
                NoteInfo noteInfo = new NoteInfo();
                noteInfo.setNoteBody("哈哈哈哈");
                noteInfo.setNoteTitle("我的日记");
                noteInfo.setNoteDate("00:00");
                noteInfos.add(noteInfo);

            }
            rv.setLayoutManager(new GridLayoutManager(mContext, 2));
//        initList();
            mMyAdapter = new MyAdapter(noteInfos);
            rv.setAdapter(mMyAdapter);
            rv.setHasFixedSize(true);

        }
    }
}
