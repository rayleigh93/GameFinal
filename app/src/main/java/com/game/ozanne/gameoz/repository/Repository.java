package com.game.ozanne.gameoz.repository;

import android.support.annotation.NonNull;

import com.game.ozanne.gameoz.remoteDataSource.DataSource;
import com.game.ozanne.gameoz.serviceSocketIO.EventListener;
import com.game.ozanne.gameoz.serviceSocketIO.EventService;

import java.net.URISyntaxException;

import io.reactivex.Flowable;

public class Repository implements DataSource {


    private static Repository INSTANCE = null;
    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;
    private EventListener mPresenterEventListener;


    // Prevent direct instantiation
    public Repository(@NonNull DataSource remoteDataSource,
                       @NonNull DataSource localDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
        mRemoteDataSource.setEventListener(this);
    }



    public static Repository getInstance(@NonNull DataSource remoteDataSource,
                                         @NonNull DataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(remoteDataSource, localDataSource);
        }

        return INSTANCE;
    }


    public DataSource getmRemoteDataSource() {
        return mRemoteDataSource;
    }

    @Override
    public void setEventListener(EventListener eventListener) {
        mPresenterEventListener = eventListener;
    }

    @Override
    public Flowable<Integer> sendAction(Integer position) {
        return mRemoteDataSource.sendAction(position);
    }

    @Override
    public EventService getmEventService() {
        return mRemoteDataSource.getmEventService();
    }


    @Override
    public void connect(String userName) throws URISyntaxException {
        mRemoteDataSource.connect(userName);
    }

    @Override
    public void disconnect() {
        mRemoteDataSource.disconnect();
    }


    @Override
    public void onConnect(Object... args) {
        if (mPresenterEventListener != null)
            mPresenterEventListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        if (mPresenterEventListener != null)
            mPresenterEventListener.onDisconnect(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        if (mPresenterEventListener != null)
            mPresenterEventListener.onUserJoined(args);
    }

    @Override
    public void onGameCreated(Object... args) {
        if (mPresenterEventListener != null)
            mPresenterEventListener.onGameCreated(args);

    }

    @Override
    public void onNewAction(Object... args) {
        if (mPresenterEventListener != null)
            mPresenterEventListener.onNewAction(args);
    }
}
