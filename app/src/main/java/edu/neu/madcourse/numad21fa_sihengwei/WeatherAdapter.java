package edu.neu.madcourse.numad21fa_sihengwei;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {

    private final ArrayList<ItemWeather> itemWeathers;

    public WeatherAdapter(ArrayList<ItemWeather> itemWeathers) {
        this.itemWeathers = itemWeathers;
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
        ItemWeather itemWeather = itemWeathers.get(position);
        holder.tvDate.setText(itemWeather.getDate());
        holder.ivWeather.setImageResource(getDrawableId(itemWeather.getState()));
        holder.tvminTemp.setText(itemWeather.getMinTemp());
        holder.tvmaxTemp.setText(itemWeather.getMaxTemp());
    }

    @Override
    public int getItemCount() {
        return itemWeathers.size();
    }

    private int getDrawableId(String state){
        int imageId;
        switch (state){
            case "sn":
                imageId = R.drawable.sn;
                break;
            case "sl":
                imageId = R.drawable.sl;
                break;
            case "h":
                imageId = R.drawable.h;
                break;
            case "t":
                imageId = R.drawable.t;
                break;
            case "hr":
                imageId = R.drawable.hr;
                break;
            case "lr":
                imageId = R.drawable.lr;
                break;
            case "s":
                imageId = R.drawable.s;
                break;
            case "hc":
                imageId = R.drawable.hc;
                break;
            case "lc":
                imageId = R.drawable.lc;
                break;
            case "c":
                imageId = R.drawable.c;
                break;
            default:
                imageId = 0;
        }
        return imageId;
    }
}
