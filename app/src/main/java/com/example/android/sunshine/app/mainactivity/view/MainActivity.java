package com.example.android.sunshine.app.mainactivity.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.android.sunshine.app.R;
import com.example.android.sunshine.app.mainactivity.Common.StateMaintainer;
import com.example.android.sunshine.app.mainactivity.model.MainModel;
import com.example.android.sunshine.app.mainactivity.mvp.MainMVP;
import com.example.android.sunshine.app.mainactivity.presenter.MainPresenter;
import com.example.android.sunshine.app.mainactivity.view.recycler.WeatherFeedAdapter;


public class MainActivity extends AppCompatActivity implements MainMVP.REquiredViewOps{

    private WeatherFeedAdapter mAdapter;


    private MainMVP.ProvidedPresenterOps mPresenter;

    // Responsible to maintain the object's integrity
    // during configurations change
    private final StateMaintainer mStateMaintainer
            = new StateMaintainer(MainActivity.class.getName() , getFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();


    }





    /**
     * Setup Model View Presenter pattern.
     * Use a {@link StateMaintainer} to maintain the
     * Presenter and Model instances between configuration changes.
     * Could be done differently,
     * using a dependency injection for example.
     */
    private void setUpMvp(){

        //check if statemaintainer has been created
        if(mStateMaintainer.firstTimeIn()){

            //create the presenter
            MainPresenter presenter = new MainPresenter(this);

            //create the model
            MainModel model = new MainModel(presenter);

            //set presenter to model
            presenter.setModel(model);

            //add presenter and model to StateMaintainer for preserving
            mStateMaintainer.put(presenter);
            mStateMaintainer.put(model);

            // Set the Presenter as a interface
            // To limit the communication with it
            mPresenter = presenter ;
        }else {

            //get the presenter
            mPresenter = mStateMaintainer.get(MainPresenter.class.getName());

            //updated view in presenter
            mPresenter.setView(this);
        }

    }


    /**
     * Setup the Views
     */
    private void setUpViews(){

        RecyclerView mList = (RecyclerView) findViewById(R.id.list_feeds);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setLayoutManager(linearLayoutManager);
        setUpMvp();
        mAdapter = new WeatherFeedAdapter(mPresenter);
        mList.setAdapter(mAdapter);
        mList.setItemAnimator(new DefaultItemAnimator());


    }


    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy(isChangingConfigurations());
    }
}
