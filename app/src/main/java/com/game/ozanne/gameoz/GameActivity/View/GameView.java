package com.game.ozanne.gameoz.GameActivity.View;


import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.game.ozanne.gameoz.AppLifeCycleObserver;
import com.game.ozanne.gameoz.GameActivity.GameContract;
import com.game.ozanne.gameoz.GameActivity.Presenter.GamePresenter;
import com.game.ozanne.gameoz.GameActivity.Model.GridGameManager;
import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
import com.game.ozanne.gameoz.R;
import com.game.ozanne.gameoz.remoteDataSource.RemoteDataSource;
import com.game.ozanne.gameoz.repository.Repository;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameView extends AppCompatActivity implements GameContract.ViewGame {

    @BindView(R.id.grid)
    RelativeLayout grid;

    private GameContract.PresenterGame mPresenter;

    GridGameView gridGameView;

    private static final String TAG = GameView.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mPresenter = new GamePresenter(new Repository(RemoteDataSource.getInstance()
                , RemoteDataSource.getInstance())
                , this,
                this);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnect(Object... args) {

    }

    @Override
    public void onDisconnect(Object... args) {

    }

    @Override
    public void onUserJoined(Object... args) {

    }

    @Override
    public void onGameCreated(Object... args) {
        String json = (String) args[0].toString();
      //  Log.i("JsonTest", json);
        Gson gson = new Gson();
        final GameObject gameObject = gson.fromJson(json,GameObject.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // Stuff that updates the UI
               // Log.i("JsonTest",   gameObject.getUserNameOne());


            }
        });
    }

    @Override
    public void onNewAction(Object... args) {
        final String json = (String) args[0].toString();
        Gson gson = new Gson();
        final GameObject gameObject = gson.fromJson(json,GameObject.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                // Si il y a aucun gridgameView on le crée, sinon on l'update
                // il faut voir si les deux gameobject sont diff
                if(mPresenter.checkDiffGameObject(gameObject)){
                    updateUI();
                }
                else {

                }
            }
        });
    }


    @Override
    public void setPresenter(GameContract.PresenterGame presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initView() {
        // Observer to detect if the app is in background or foreground.
        AppLifeCycleObserver lifeCycleObserver
                = new AppLifeCycleObserver(getApplicationContext());

        // Adding the above observer to process lifecycle
        ProcessLifecycleOwner.get()
                .getLifecycle()
                .addObserver(lifeCycleObserver);

        mPresenter.initGridGameManager(grid, this);
    }

    @Override
    public void onActionSend() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Stuff that updates the UI
                Toast.makeText(GameView.this, "ok envoyee", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void updateUI()
    {
        this.grid.addView(this.gridGameView.getmLayoutParent());

//            for (int i = 0; i < this.gridGameView.getmListLayout().size(); i++) {
//                this.grid.addView(this.gridGameView.getmListLayout().get(i));
//            }

    }


    public GridGameView getGridGameView() {
        return gridGameView;
    }

    public void setGridGameView(GridGameView gridGameView) {
        this.gridGameView = gridGameView;
    }

    public RelativeLayout getGrid() {
        return grid;
    }

    public void setGrid(RelativeLayout grid) {
        this.grid = grid;
    }
}
