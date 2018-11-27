package com.game.ozanne.gameoz.MainActivity;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import com.game.ozanne.gameoz.repository.Repository;
import com.game.ozanne.gameoz.serviceSocketIO.EventListener;
import com.game.ozanne.gameoz.serviceSocketIO.EventService;
import com.game.ozanne.gameoz.serviceSocketIO.EventServiceImpl;

import java.net.URISyntaxException;

public class MainPresenterImpl implements MainContract.MainPresenter {


    private static final String TAG = MainPresenterImpl.class.getName();

    @NonNull
    private final Repository mRepository;

    @NonNull
    private final MainContract.MainView mMainView;

    @NonNull
    private final EventListener mViewEventListener;



    public MainPresenterImpl(@NonNull Repository mRepository, @NonNull MainContract.MainView mMainView, @NonNull EventListener mViewEventListener) {
        this.mRepository = mRepository;
        this.mMainView = mMainView;
        this.mViewEventListener = mViewEventListener;


        mRepository.setEventListener(this);
        mMainView.setPresenter(this);
        mMainView.initView();
    }



    @Override
    public void unsubscribe() {
    mRepository.disconnect();
    }

    @Override
    public void subscribe(String userName) throws URISyntaxException {
        mRepository.connect(userName);
    }

    @Override
    public void onClickBouttonConnect(EditText text) throws URISyntaxException {
        if(StringUserNameManager.check(text)) {
            subscribe(text.getText().toString());
        }
        else
            mMainView.errorWithUsername();
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


    public void checkSocketId(){
        try {
          if( mRepository.getmRemoteDataSource().getmEventService().isConnected())
          mMainView.nextActivity();
          Log.i(TAG,"ok");
        }catch (Exception e){
            Log.i(TAG,e.getMessage());
        }

    }




}
