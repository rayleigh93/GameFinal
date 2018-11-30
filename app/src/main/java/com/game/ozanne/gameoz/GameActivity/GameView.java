package com.game.ozanne.gameoz.GameActivity;


import android.arch.lifecycle.ProcessLifecycleOwner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.game.ozanne.gameoz.AppLifeCycleObserver;
import com.game.ozanne.gameoz.GameActivity.Model.ManagerGridGame;
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

    private List<LinearLayout> linearLayouts;
    private GameContract.PresenterGame mPresenter;

    ManagerGridGame managerGridGame;

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


         managerGridGame = new ManagerGridGame(this,grid);

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


                managerGridGame.checkDiffGameObject(gameObject);

            }
        });
    }


    @Override
    public void setPresenter(GameContract.PresenterGame presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        // Observer to detect if the app is in background or foreground.
        AppLifeCycleObserver lifeCycleObserver
                = new AppLifeCycleObserver(getApplicationContext());

        // Adding the above observer to process lifecycle
        ProcessLifecycleOwner.get()
                .getLifecycle()
                .addObserver(lifeCycleObserver);




    }

    @Override
    public void clickOnCase(int position) {
        mPresenter.sendAction(position);
        Log.i("TOTO", Integer.toString(position));
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




    // Quand l'UI à été loaded
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

       // ManagerGridGame managerGridGame = new ManagerGridGame(getApplicationContext(),grid,null);
      //  managerGridGame.checkDiffGameObject();

    }
}
