package com.game.ozanne.gameoz.remoteDataSource;

import com.game.ozanne.gameoz.serviceSocketIO.EventListener;
import com.game.ozanne.gameoz.serviceSocketIO.EventService;

import java.net.URISyntaxException;
import java.util.List;

import io.reactivex.Flowable;


/**
 * Main interface for accessing data. It extends EventListener to receive
 * incoming events from a remote data source.
 */
public interface DataSource extends EventListener {

    void connect(String userName) throws URISyntaxException;

    void disconnect();

    void setEventListener(EventListener eventListener);

    Flowable<List<Integer>> sendAction(List<Integer> position);

    EventService getmEventService();

}
