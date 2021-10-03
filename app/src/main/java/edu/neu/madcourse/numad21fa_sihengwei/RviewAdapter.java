package edu.neu.madcourse.numad21fa_sihengwei;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<ItemLink> itemLinks;
    private ItemLinkClickListener listener;

    public RviewAdapter(ArrayList<ItemLink> itemLinks) {
        this.itemLinks = itemLinks;
    }

    public void setOnItemClickListener(ItemLinkClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new RviewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RviewHolder holder, int position) {
        ItemLink currentItem = itemLinks.get(position);

        holder.tvLinkName.setText(currentItem.getLinkName());
        holder.tvLinkUrl.setText(currentItem.getLinkUrl());
    }

    @Override
    public int getItemCount() {
        return itemLinks.size();
    }
}
