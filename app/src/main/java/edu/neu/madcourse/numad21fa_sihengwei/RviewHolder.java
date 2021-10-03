package edu.neu.madcourse.numad21fa_sihengwei;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {

    public TextView tvLinkName;
    public TextView tvLinkUrl;


    public RviewHolder(@NonNull View itemView, final ItemLinkClickListener listener) {
        super(itemView);
        this.tvLinkName = itemView.findViewById(R.id.tv_linkName);
        this.tvLinkUrl = itemView.findViewById(R.id.tv_linkUrl);

        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(listener != null){
                    int position = getLayoutPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemLinkClick(position);
                    }
                }
            }
        });
    }
}
