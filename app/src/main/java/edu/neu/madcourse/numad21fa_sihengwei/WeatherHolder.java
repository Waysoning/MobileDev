package edu.neu.madcourse.numad21fa_sihengwei;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherHolder extends RecyclerView.ViewHolder{

    public TextView tvDate;
    public ImageView ivWeather;
    public TextView tvminTemp;
    public TextView tvmaxTemp;

    public WeatherHolder(@NonNull View itemView) {
        super(itemView);
        this.tvDate = itemView.findViewById(R.id.tv_date);
        this.ivWeather = itemView.findViewById(R.id.iv_weather);
        this.tvminTemp = itemView.findViewById(R.id.tv_min_temp);
        this.tvmaxTemp = itemView.findViewById(R.id.tv_max_temp);
    }
}
