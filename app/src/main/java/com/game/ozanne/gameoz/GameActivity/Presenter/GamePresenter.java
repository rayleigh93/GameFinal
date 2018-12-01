package com.game.ozanne.gameoz.GameActivity.Presenter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.GameContract;
import com.game.ozanne.gameoz.GameActivity.Model.CaseAvailable;
import com.game.ozanne.gameoz.GameActivity.Model.GameManager;
import com.game.ozanne.gameoz.GameActivity.Model.GridGameManager;
import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
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

    // S'occupe de gerer le GrisGameView (sui est la grille de la partie)
    GridGameManager gridGameManager;
    GameManager gameManager;


    public GamePresenter(@NonNull Repository mRepository, @NonNull GameContract.ViewGame mView, @NonNull EventListener mViewEventListener) {
        this.mRepository = mRepository;
        this.mView = mView;
        this.mViewEventListener = mViewEventListener;

        // Setting the view's eventListener in the repository so that
        // when server sends events to repository, it passes the
        // events to the view
        mRepository.setEventListener(this);

        this.gameManager = new GameManager(this);

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


    public void initGridGameManager(RelativeLayout relativeLayout, Context context){
        // initialisation du gridGameManager
        gridGameManager = new GridGameManager(context,this);
    }



    // a chaque reception du JSON GAME OBJECT
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean checkDiffGameObject(GameObject gameObject) {
        // Check si il y a une diff de gameObject
        if(gameManager.isGameObjectChanged(gameObject)) {
            Log.i("TEST","AZER : " + gameManager.isGameObjectChanged(gameObject));
            mView.setGridGameView(gridGameManager.createOrUpdateGridLayout(gameObject, mView.getGrid()));
            return true;
        }
        else{
            return false;
        }

    }


    @Override
    public void clickOnCase(int position) {
            // Agir avec le game Manager
            gameManager.clickOnCase(position);
    }



    @Override
    public void sendCaseAvailable(CaseAvailable caseAvailable) {
            gridGameManager.updateGridLayoutWithCaseAvaible(caseAvailable,mView.getGridGameView(),gameManager.getmGameObject());
    }


}
