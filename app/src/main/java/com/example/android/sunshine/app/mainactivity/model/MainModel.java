package com.example.android.sunshine.app.mainactivity.model;

import com.example.android.sunshine.app.mainactivity.mvp.MainMVP;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by VutkaBilai on 1/15/17.
 * mail : la4508@gmail.com
 */

public class MainModel implements MainMVP.ProvidedModelOps {

    private MainMVP.RequiredPresenterOps mPresenter;

    public ArrayList<String> weatherfeeds ;

    private // Create some dummy data for the ListView.  Here's a sample weekly forecast
            String[] data = {
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    };

    public MainModel(MainMVP.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;

        weatherfeeds = getDummyWeatherFeeds();
    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {

        if(!isChangingConfigurations){
            mPresenter = null ;
        }
    }

    @Override
    public ArrayList<String> getDummyWeatherFeeds() {

        weatherfeeds = new ArrayList<>(Arrays.asList(data));
        return weatherfeeds;
    }

    @Override
    public int getFeedCount() {
        return  weatherfeeds.size() ;
    }


}
