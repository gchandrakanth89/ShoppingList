package com.rb.shoppinglist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pervacio on 27-06-2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<Item> itemsList;

    public ListAdapter(Context context, List<Item> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

//        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(lp);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        holder.setData(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView itemNameTv;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNameTv = (TextView) itemView.findViewById(R.id.item_name);
        }

        public void setData(Item item) {
            itemNameTv.setText(item.getName());
        }
    }
}
