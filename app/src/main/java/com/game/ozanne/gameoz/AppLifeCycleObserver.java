package com.game.ozanne.gameoz;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import com.game.ozanne.gameoz.serviceSocketIO.EventServiceImpl;

public class AppLifeCycleObserver implements LifecycleObserver {

    private Context mContext;

    /**
     * Use this constructor to create a new AppLifeCycleObserver
     *
     * @param context
     */
    public AppLifeCycleObserver(Context context) {
        mContext = context;
    }

    /**
     * When app enters foreground
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onEnterForeground() {

   /*     try {
            EventServiceImpl.getInstance().connect("TOTOTO");
        } catch (URISyntaxException e) {
            Toast.makeText(mContext, "Failed to connect to chat server.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
*/
        Log.i("FORE","ENTER FOREGROUND : " );

    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.i("FORE","ENTER STOP");
        EventServiceImpl.getInstance().disconnect();
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        Log.i("FORE","Background");
    }





}
