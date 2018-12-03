package com.game.ozanne.gameoz.serviceSocketIO;

import java.net.URISyntaxException;
import java.util.List;

import io.reactivex.Flowable;

public interface EventService {


    void connect(String username) throws URISyntaxException;

    void disconnect();

    Flowable<List<Integer>> sendAction(List<Integer> position);

    void setEventListener(EventListener eventListener);

    boolean isConnected();


}
