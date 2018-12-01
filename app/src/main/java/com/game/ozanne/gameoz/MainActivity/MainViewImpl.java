package com.game.ozanne.gameoz.MainActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.game.ozanne.gameoz.GameActivity.View.GameView;
import com.game.ozanne.gameoz.R;
import com.game.ozanne.gameoz.remoteDataSource.RemoteDataSource;
import com.game.ozanne.gameoz.repository.Repository;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainViewImpl extends AppCompatActivity implements MainContract.MainView{


    private static final String TAG = MainViewImpl.class.getName();
    @BindView(R.id.editText)
    EditText editText;

    @OnClick(R.id.button)  public void onClickConnection() {
        // When click on button
        try {
            mMainPresenter.onClickBouttonConnect(editText);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private MainContract.MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainPresenter = new MainPresenterImpl(Repository.getInstance
                (RemoteDataSource.getInstance(), RemoteDataSource.getInstance())
                , this,
                this);





    }


    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.checkSocketId();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void setPresenter(MainContract.MainPresenter mainPresenter) {
        mMainPresenter = mainPresenter;
    }

    @Override
    public void errorWithUsername() {
        Toast.makeText(this, "Error with Username", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }


    @Override
    public void onConnect(Object... args) {
        this.nextActivity();
    }

    @Override
    public void onDisconnect(Object... args) {

    }

    @Override
    public void onUserJoined(Object... args) {

    }

    @Override
    public void onGameCreated(Object... args) {

    }

    @Override
    public void onNewAction(Object... args) {

    }

    public void nextActivity(){
        startActivity(new Intent(this,GameView.class));
    }


}
