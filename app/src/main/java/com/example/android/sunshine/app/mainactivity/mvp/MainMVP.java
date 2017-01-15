package com.example.android.sunshine.app.mainactivity.mvp;

import android.content.Context;
import android.widget.TextView;

import com.example.android.sunshine.app.mainactivity.view.MainActivity;

/**
 * Created by VutkaBilai on 1/15/17.
 * mail : la4508@gmail.com
 */

public interface MainMVP {


    /**
     * Required View methods available to Presenter.
     * A passive layer, responsible to show data
     * and receive user interactions
     * (presenter -> view )
     */
    interface REquiredViewOps{

        Context getAppContext();
        Context getActivityContext();

    }


    /**
     * Operations offered to View to communicate with Presenter.
     * Process user interaction, sends data requests to Model, etc.
     * (view -> presenter)
     */

    interface ProvidedPresenterOps{
        void onDestroy(boolean isChangingConfigurations);
        void setView(REquiredViewOps view);
        void setText(TextView tv);
    }


    /**
     * required Presenter operation available
     * to model (model -> presenter)
     */
    interface RequiredPresenterOps{
        Context getAppContext();
        Context gerActivityContext();

    }



    /**
     * Operations offered to model to communicate with presenter
     * Handles all data business logic
     * (presenter -> model )
     */
    interface ProvidedModelOps{
        void onDestroy(boolean isChangingConfigurations);

        String getString();
    }
}
