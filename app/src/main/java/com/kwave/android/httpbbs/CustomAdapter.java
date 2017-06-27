package com.kwave.android.httpbbs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kwave on 2017-06-26.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CardHoler>{
        List<Bbs> list;

    public CustomAdapter() {
        list = new ArrayList<>();
    }
    public void setList(List<Bbs> list) {
        this.list = list;
    }
    @Override
    public CardHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list,parent,false);
        return new CardHoler(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.CardHoler holder, int position) {
        Bbs bbs = list.get(position);
        holder.no.setText(Integer.toString(bbs.id));        // 숫자만 넣으면 에러
        holder.title.setText(bbs.title);
        holder.date.setText(bbs.date);
        holder.content.setText(bbs.content);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CardHoler extends RecyclerView.ViewHolder {
        TextView no;
        TextView title;
        TextView date;
        TextView content;

        public CardHoler(View itemView) {
            super(itemView);
            no = (TextView) itemView.findViewById(R.id.textNo);
            title = (TextView) itemView.findViewById(R.id.textTitle);
            date = (TextView) itemView.findViewById(R.id.textDate);
            content = (TextView) itemView.findViewById(R.id.textContent);

        }
    }
}

