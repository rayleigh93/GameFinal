package com.game.ozanne.gameoz.GameActivity;

import android.support.annotation.NonNull;

import com.game.ozanne.gameoz.repository.Repository;
import com.game.ozanne.gameoz.serviceSocketIO.EventListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GamePresenter implements GameContract.PresenterGame {



    @NonNull
    private final Repository mRepository;

    @NonNull
    private GameContract.ViewGame mView;


    @NonNull
    private final CompositeDisposable mCompositeDisposable;

    @NonNull
    private final EventListener mViewEventListener;


    public GamePresenter(@NonNull Repository mRepository, @NonNull GameContract.ViewGame mView, @NonNull EventListener mViewEventListener) {
        this.mRepository = mRepository;
        this.mView = mView;
        this.mViewEventListener = mViewEventListener;

        // Setting the view's eventListener in the repository so that
        // when server sends events to repository, it passes the
        // events to the view
        mRepository.setEventListener(this);


        mCompositeDisposable = new CompositeDisposable();
        mView.setPresenter(this);
        mView.initView();
    }


    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
        mRepository.disconnect();
    }

    @Override
    public void onConnect(Object... args) {
        mViewEventListener.onConnect(args);
    }

    @Override
    public void onDisconnect(Object... args) {
        mViewEventListener.onDisconnect(args);
    }

    @Override
    public void onUserJoined(Object... args) {
        mViewEventListener.onUserJoined(args);
    }

    @Override
    public void onGameCreated(Object... args) {
        mViewEventListener.onGameCreated(args);
    }

    @Override
    public void onNewAction(Object... args) {
        mViewEventListener.onNewAction(args);
    }

    @Override
    public void sendAction(Integer position) {
        Disposable disposable = mRepository.sendAction(position)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                            mView.onActionSend();
                    }
                });
            mCompositeDisposable.add(disposable);
    }


}
