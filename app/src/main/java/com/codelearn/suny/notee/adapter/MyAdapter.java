package com.codelearn.suny.notee.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codelearn.suny.notee.NoteInfo;
import com.codelearn.suny.notee.R;

import java.util.List;

/**
 * Created by suny on 2017/5/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Holder> {

    private List<NoteInfo> mNoteInfoList;

    public MyAdapter(List<NoteInfo> noteInfoList) {
        mNoteInfoList = noteInfoList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_lists, parent, false);
        Holder myHolder = new Holder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tv_title.setText(mNoteInfoList.get(position).getNoteTitle());
        holder.tv_body.setText(mNoteInfoList.get(position).getNoteBody());
//        holder.tv_date.setText(mNoteInfoList.get(position).getNoteDate());

    }

    @Override
    public int getItemCount() {
        return mNoteInfoList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView tv_title,tv_body, tv_date;

        public Holder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_body = (TextView) itemView.findViewById(R.id.tv_body);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);

        }
    }
}
