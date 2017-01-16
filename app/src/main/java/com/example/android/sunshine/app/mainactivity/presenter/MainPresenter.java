package com.example.android.sunshine.app.mainactivity.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sunshine.app.R;
import com.example.android.sunshine.app.mainactivity.mvp.MainMVP;
import com.example.android.sunshine.app.mainactivity.view.recycler.WeatherFeedViewHolder;

import java.lang.ref.WeakReference;

/**
 * Created by VutkaBilai on 1/15/17.
 * mail : la4508@gmail.com
 */

public class MainPresenter implements MainMVP.RequiredPresenterOps,MainMVP.ProvidedPresenterOps {


    // View reference. We use as a WeakReference
    // because the Activity could be destroyed at any time
    // and we don't want to create a memory leak
    private WeakReference<MainMVP.REquiredViewOps> mView;

    //model reference
    private MainMVP.ProvidedModelOps mModel;


    public MainPresenter(MainMVP.REquiredViewOps mView) {
        this.mView = new WeakReference<MainMVP.REquiredViewOps>(mView);
    }



    /**
     * Called by View every time it is destroyed.
     *
     * @param isChangingConfigurations true: is changing configuration
     *                                 and will be recreated
     */
    @Override
    public void onDestroy(boolean isChangingConfigurations) {
        //view should be null every time onDestroy is called
        mView = null;
        //infor model about the event
        mModel.onDestroy(isChangingConfigurations);

        //activity destroyed
        if (!isChangingConfigurations) {
            mModel = null;
        }
    }


    /**
     * Called by View during the reconstruction events
     *
     * @param view Activity instance
     */
    @Override
    public void setView(MainMVP.REquiredViewOps view) {
        mView = new WeakReference<MainMVP.REquiredViewOps>(view);
    }


    /**
     * Create the RecyclerView holder and setup its view
     * @param container
     * @param viewType
     * @return
     */
    @Override
    public WeatherFeedViewHolder createViewHolder(ViewGroup container, int viewType) {

        WeatherFeedViewHolder viewHolder ;
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        View feedRow = inflater.inflate(R.layout.list_item_forecast , container , false);
        viewHolder = new WeatherFeedViewHolder(feedRow);



        return viewHolder;
    }

    /**
     * Binds ViewHolder with RecyclerView
     * @param holder
     * @param position
     */
    @Override
    public void bindViewHolder(WeatherFeedViewHolder holder, int position) {
        holder.feedTv.setText(mModel.getDummyWeatherFeeds().get(position));
    }

    @Override
    public int getItemCount() {
        return mModel.getFeedCount() ;
    }


    /**
     * Called by Activity during MVP setup. Only called once.
     *
     * @param model Model instance
     */
    public void setModel(MainMVP.ProvidedModelOps model) {

        mModel = model;


    }



    @Override
    public Context getAppContext() {
        try{
            return getView().getAppContext();
        }catch (NullPointerException e){
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public Context gerActivityContext() {
        try{
            return getView().getActivityContext();
        }catch (NullPointerException e){
            e.printStackTrace();

            return null;
        }
    }


    /**
     * Return the View reference.
     * Could throw an exception if the View is unavailable.
     *
     * @return {@link MainMVP.REquiredViewOps}
     * @throws NullPointerException when View is unavailable
     */
    private MainMVP.REquiredViewOps getView() throws NullPointerException{

        if (mView != null)
            return mView.get();
        else {
            throw new NullPointerException("view is unavailable");
        }
    }
}
