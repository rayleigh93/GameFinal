package com.game.ozanne.gameoz.serviceSocketIO;

import android.util.Log;

import java.net.URISyntaxException;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * Implementation of {@link EventService} which connects and disconnects to the server.
 * It also sends and receives events from the server.
 */
public class EventServiceImpl implements EventService {

    private static final String TAG = EventServiceImpl.class.getSimpleName();
    private static final String SOCKET_URL = "http://192.168.0.49:3000";

    //EVENT_CONNECT = connect
    private static final String EVENT_CONNECT = Socket.EVENT_CONNECT;
    private static final String EVENT_DISCONNECT = Socket.EVENT_DISCONNECT;
    private static final String EVENT_JOINED = "userjoined";
    private static final String EVENT_GAME_CREATED = "gamecreated";
    private static final String EVENT_PLAYER_PLAY = "playerplay";

    private static final String EMIT_ADD_USER = "add user";
    private static final String EMIT_SEND_ACTION = "sendaction";

    private static EventService INSTANCE;
    private static EventListener mEventListener;
    private static Socket mSocket;
    private String mUsername;

    public EventServiceImpl() {
    }

    /**
     * EventService est un Singleton, c'est la methode pour crée une instance de cette dernière
     * @return
     */
    public static EventService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new EventServiceImpl();
        }
        return INSTANCE;
    }



    @Override
    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    @Override
    public boolean isConnected() {
        return mSocket.connected();
    }


    /**
     * Quand l'User se connecte au serveur
     * @param userName
     */
    @Override
    public void connect(String userName) throws URISyntaxException {

        mUsername = userName;
        mSocket = IO.socket(SOCKET_URL);

        mSocket.on(EVENT_CONNECT,onConnect);
        mSocket.on(EVENT_DISCONNECT,onDisconnect);
        mSocket.on(EVENT_JOINED,onUserJoined);
        mSocket.on(EVENT_GAME_CREATED,onGameCreated);
        mSocket.on(EVENT_PLAYER_PLAY,onNewAction);

        mSocket.connect();
    }

    /**
     * Quand l'User se deconnecte du serveur
     */
    @Override
    public void disconnect() {
        mSocket.disconnect();
    }



    @Override
    public Flowable<List<Integer>> sendAction(final List<Integer> position) {
        return Flowable.create(new FlowableOnSubscribe<List<Integer>>() {
            @Override
            public void subscribe(FlowableEmitter<List<Integer>> emitter) throws Exception {
                mSocket.emit(EMIT_SEND_ACTION, position);
                emitter.onNext(position);
            }
        }, BackpressureStrategy.BUFFER);
    }


    /**
     * Quand un User est connecté, on emit le nom au serveur pour que ce dernier l'envoie aux clients
     */
    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG, "call: onConnect");
           mSocket.emit(EMIT_ADD_USER, mUsername);
           mSocket.emit("search game",mUsername);
            if (mEventListener != null) mEventListener.onConnect(args);
        }
    };



    /**
     * Quand un User est connecté on annonce aux clients cette connection (avec le nom du user)
     */
    private Emitter.Listener onUserJoined = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data = (String) args[0];
            Log.i(TAG, "call: onUser Joined : " + data);
            if (mEventListener != null) mEventListener.onUserJoined(args);
        }
    };



    /**
     * Quand un User est deconnecté on annonce aux clients cette déconnection
     */
    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data = (String) args[0];
            Log.i(TAG, "call: onDisconnect : " + data);
            if (mEventListener != null) mEventListener.onDisconnect(args);
        }
    };


    /**
     * Quand une game est crée entre deux joueurs !
     */
    private Emitter.Listener onGameCreated = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.i(TAG,mSocket.id());
            Log.i(TAG, "call: onGameCreated : " );
            if (mEventListener != null) mEventListener.onGameCreated(args);
        }
    };


    /**
     * Reçois le Json Game en boucle
     */
    private Emitter.Listener onNewAction = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
          //  Log.i(TAG, "call: onPlayerPlay" );
            if (mEventListener != null) mEventListener.onNewAction(args);
        }
    };








}
