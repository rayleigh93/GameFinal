package com.game.ozanne.gameoz.MainActivity;

interface BaseView<T> {

     void initView();

     void setPresenter(MainContract.MainPresenter mainPresenter);

     void errorWithUsername();

     void nextActivity();

}
