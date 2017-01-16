package com.example.android.sunshine.app.mainactivity.view.recycler;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.sunshine.app.R;

/**
 * Created by vutka bilai on 1/16/17.
 * mail : la4508@gmail.com
 */

public class WeatherFeedViewHolder extends RecyclerView.ViewHolder {

    public TextView feedTv;
    public WeatherFeedViewHolder(View itemView) {
        super(itemView);

        feedTv = (TextView) itemView.findViewById(R.id.list_item_forecast_textview);
    }
}
