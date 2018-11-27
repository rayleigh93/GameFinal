package com.game.ozanne.gameoz.MainActivity;

import android.widget.EditText;

import java.net.URISyntaxException;

interface BasePresenter {


    void unsubscribe();

    void subscribe(String userName) throws URISyntaxException;

    void onClickBouttonConnect(EditText text) throws URISyntaxException;

     void checkSocketId();
}
