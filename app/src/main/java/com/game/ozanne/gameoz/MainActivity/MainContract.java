package com.game.ozanne.gameoz.MainActivity;

import com.game.ozanne.gameoz.serviceSocketIO.EventListener;

public interface MainContract {

    interface MainView extends BaseView<MainPresenter>, EventListener {


    }

    interface MainPresenter extends BasePresenter, EventListener {


    }



}
