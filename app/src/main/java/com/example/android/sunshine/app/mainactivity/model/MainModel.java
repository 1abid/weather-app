package com.example.android.sunshine.app.mainactivity.model;

import com.example.android.sunshine.app.mainactivity.mvp.MainMVP;

/**
 * Created by VutkaBilai on 1/15/17.
 * mail : la4508@gmail.com
 */

public class MainModel implements MainMVP.ProvidedModelOps {

    private MainMVP.RequiredPresenterOps mPresenter;


    public MainModel(MainMVP.RequiredPresenterOps mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void onDestroy(boolean isChangingConfigurations) {

        if(!isChangingConfigurations){
            mPresenter = null ;
        }
    }

    @Override
    public String getString() {
        return "Hello MVP";
    }
}
