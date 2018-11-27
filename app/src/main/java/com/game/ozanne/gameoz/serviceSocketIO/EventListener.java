package com.game.ozanne.gameoz.serviceSocketIO;


/**
 * Main interface to listen to server events.
 *
 */
public interface EventListener {

    /**
     * Quand un User se connecte au Socket
     * @param args
     */
    void onConnect(Object... args);

    /**
     * Quand un User se déconnecte au Socket
     * @param args
     */
    void onDisconnect(Object... args);

    /**
     * Quand un User se connecte au Socket on reçois le nom de la personne connecté
     * @param args
     */
    void onUserJoined(Object... args);


    /**
     * Quand une game à été crée
     * @param args
     */
    void onGameCreated(Object... args);


    void onNewAction(Object... args);

}
