package com.game.ozanne.gameoz.serviceSocketIO;

import java.net.URISyntaxException;

import io.reactivex.Flowable;

public interface EventService {


    void connect(String username) throws URISyntaxException;

    void disconnect();

    Flowable<Integer> sendAction(Integer position);

    void setEventListener(EventListener eventListener);

    boolean isConnected();


}
