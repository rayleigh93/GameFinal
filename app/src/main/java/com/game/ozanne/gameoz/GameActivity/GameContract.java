package com.game.ozanne.gameoz.GameActivity;



import com.game.ozanne.gameoz.serviceSocketIO.EventListener;

public interface GameContract {


    interface ViewGame extends GameBaseView<PresenterGame>, EventListener, ClickOnCaseGrilleGame {

        // Update UI to show the message has been delivered
            void onActionSend();
    }

    interface PresenterGame extends GameBasePresenter, EventListener {


        // Action à envoyé
        void sendAction(Integer position);

    }








}
