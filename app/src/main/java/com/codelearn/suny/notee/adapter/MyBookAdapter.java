package com.codelearn.suny.notee.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codelearn.suny.notee.BookInfo;
import com.codelearn.suny.notee.MainActivity;
import com.codelearn.suny.notee.R;
import com.codelearn.suny.notee.activity.NoteActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suny on 2017/5/21.
 */

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.Holder> {

    private List<BookInfo> mList;
    private Context mContext;

    public MyBookAdapter(List<BookInfo> list, Context context) {
        mContext = context;
        mList = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items, parent, false);
        MyBookAdapter.Holder holder = new MyBookAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.et_book.setText(mList.get(position).getBookName());
        holder.im_book.setBackground(mContext.getDrawable(mList.get(position).getBookImg()));
//        holder.im_book.setBackground(Drawable.createFromPath(""));
        holder.im_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NoteActivity.class);
                intent.putExtra("name", holder.et_book.getText());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        private TextView et_book;
        private ImageView im_book;
        public Holder(View itemView) {
            super(itemView);
            et_book = (TextView) itemView.findViewById(R.id.et_book);
            im_book = (ImageView) itemView.findViewById(R.id.im_book);

        }
    }
}
