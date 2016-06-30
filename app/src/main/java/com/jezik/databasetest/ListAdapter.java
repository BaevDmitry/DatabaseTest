package com.jezik.databasetest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 29.06.2016.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context context;
    List<UserData> dataList = new ArrayList<>();
    Listener listener;

    public ListAdapter(Context context, List<UserData> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.listener = (Listener) context;
    }

    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(convertView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {

        holder.iv_delete.setTag(position);
        holder.tv_name.setText(dataList.get(position).name);

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.nameToChange(dataList.get((Integer) view.getTag()).name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        ImageView iv_delete;

        public ListViewHolder(View itemView){
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
        }
    }
}
