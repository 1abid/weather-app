package com.example.android.sunshine.app.mainactivity.view.recycler;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.android.sunshine.app.mainactivity.mvp.MainMVP;

/**
 * Created by vutka bilai on 1/16/17.
 * mail : la4508@gmail.com
 */

public class WeatherFeedAdapter extends RecyclerView.Adapter<WeatherFeedViewHolder> {

    private MainMVP.ProvidedPresenterOps mPresenter;

    public WeatherFeedAdapter(MainMVP.ProvidedPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public WeatherFeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mPresenter.createViewHolder(parent , viewType);
    }


    @Override
    public void onBindViewHolder(WeatherFeedViewHolder holder, int position) {
        mPresenter.bindViewHolder(holder , position);
    }


    @Override
    public int getItemCount() {
        return mPresenter.getItemCount();
    }
}
