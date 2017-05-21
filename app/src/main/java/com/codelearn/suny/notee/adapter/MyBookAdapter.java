package com.codelearn.suny.notee.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codelearn.suny.notee.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suny on 2017/5/21.
 */

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.Holder> {

    private List<String> mList = new ArrayList<>();

    public MyBookAdapter() {
        mList.add("haha");
        mList.add("哈哈");
        mList.add("呵呵");
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_items, parent, false);
        MyBookAdapter.Holder holder = new MyBookAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.et_book.setText(mList.get(position));
        holder.et_book.setCursorVisible(false);
        holder.et_book.setSelectAllOnFocus(true);
       holder.et_book.clearFocus();
//        holder.im_book.setBackground(Drawable.createFromPath(""));
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
