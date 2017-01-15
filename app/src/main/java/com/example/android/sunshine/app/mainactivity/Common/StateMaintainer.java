package com.example.android.sunshine.app.mainactivity.Common;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 * Created by VutkaBilai on 1/15/17.
 * mail : la4508@gmail.com
 */

public class StateMaintainer {

    protected final String TAG = getClass().getSimpleName() ;

    private final String mStateMaintainerTag;
    private final WeakReference<FragmentManager> mFragmentManager;

    private StateMngFragment mStateMaintainerFrag;
    private boolean isRecreating;

    public StateMaintainer(String mStateMaintainerTag, FragmentManager mFragmentManager) {
        this.mStateMaintainerTag = mStateMaintainerTag;
        this.mFragmentManager = new WeakReference<FragmentManager>(mFragmentManager);
    }


    /**
     * Creates the Fragment responsible to maintain the objects.
     * @return  true: fragment just created
     */
    public boolean firstTimeIn(){

        try{

            mStateMaintainerFrag = (StateMngFragment) mFragmentManager.get().findFragmentByTag(mStateMaintainerTag);


            if(mStateMaintainerFrag == null){
                Log.d(TAG , "no saved fragment found to retained " + mStateMaintainerFrag);

                mStateMaintainerFrag = new StateMngFragment();
                mFragmentManager.get().beginTransaction().add(mStateMaintainerFrag , mStateMaintainerTag).commit();
                isRecreating = false ;

                return true ;
            }else {

                Log.d(TAG , "saved fragment found , recreting fragment "+mStateMaintainerFrag);
                isRecreating = true ;

                return false;
            }
        }catch (NullPointerException e){
            Log.e(TAG , "Error "+e.getMessage());

            return false;
        }
    }


    /**
     * Return <strong>true</strong> it the current Activity was recreated at least one time
     * @return  If the Activity was recreated
     */
    public boolean wasRecreated() { return isRecreating; }


    /**
     * inset the object to be preserved
     * @param key
     * @param obj
     */
    public void put(String key , Object obj){
        mStateMaintainerFrag.put(key , obj);
    }


    /**
     * inset the object to be preserved
     * @param obj
     */
    public void put(Object obj){
        mStateMaintainerFrag.put(obj);
    }

    /**
     * Recovers the object saved
     * @param key   Object's TAG
     * @param <T>   Object type
     * @return      Object saved
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key)  {
        return mStateMaintainerFrag.get(key);

    }

    /**
     * Checks the existence of a given object
     * @param key   Key to verification
     * @return      true: Object exists
     */
    public boolean hasKey(String key) {
        return mStateMaintainerFrag.get(key) != null;
    }

    /**
     * Fragment resposible to preserve objects.
     * Instantiated only once. Uses a hashmap to save objs
     */
    public static class StateMngFragment extends Fragment{


        private HashMap<String , Object> mData = new HashMap<>();


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //grants fragment will be saved
            setRetainInstance(true);
        }

        /**
         * Insert objects on the hashmap
         * @param key   Reference key
         * @param obj   Object to be saved
         */
        public void  put(String key , Object obj){
            mData.put(key , obj);
        }


        /**
         * Insert objects on the hashmap
         * @param obj   Reference key
         */
        public void put(Object obj){
            put(obj.getClass().getName() , obj);
        }


        /**
         * Recovers saved object
         * @param key   Reference key
         * @param <T>   Object type
         * @return      Object saved
         */
        @SuppressWarnings("unchecked")
        public <T> T get(String key){
            return (T) mData.get(key);
        }

    }
}
