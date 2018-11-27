package com.game.ozanne.gameoz.remoteDataSource;

import com.game.ozanne.gameoz.serviceSocketIO.EventListener;
import com.game.ozanne.gameoz.serviceSocketIO.EventService;
import com.game.ozanne.gameoz.serviceSocketIO.EventServiceImpl;

import java.net.URISyntaxException;

import io.reactivex.Flowable;


/**
 * Remote data source.
 * Implement Data Source + EventListener pour recevoir les infos du serveur
 */
public class RemoteDataSource implements DataSource {



    private static RemoteDataSource INSTANCE;

    /**
     * Envoyé les donénes aux EventServiceImpl afin de les envoyé aux serveurs
     */
    private static EventService mEventService = EventServiceImpl.getInstance();

    /**
     * Envoyé les données aux Repository qui viennent du serveur
     */
    private EventListener mRepoEventListener;


    private RemoteDataSource() {
        mEventService.setEventListener(this);
    }


    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }


    public  EventService getmEventService() {
        return mEventService;
    }


    @Override
    public void setEventListener(EventListener eventListener) {
        mRepoEventListener = eventListener;
    }

    @Override
    public Flowable<Integer> sendAction(Integer position) {
        return mEventService.sendAction(position);
    }


    @Override
    public void connect(String userName) throws URISyntaxException {
        mEventService.connect(userName);
    }

    @Override
    public void disconnect() {
        mEventService.disconnect();
    }


    @Override
    public void onConnect(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onDisconnect(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onUserJoined(args);
    }

    @Override
    public void onGameCreated(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onGameCreated(args);

    }

    @Override
    public void onNewAction(Object... args) {
        if (mRepoEventListener != null)
            mRepoEventListener.onNewAction(args);
    }



}
