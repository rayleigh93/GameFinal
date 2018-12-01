package com.game.ozanne.gameoz.GameActivity;



import android.content.Context;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.ClickOnCaseGrilleGame;
import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
import com.game.ozanne.gameoz.GameActivity.Presenter.CaseAvailableListener;
import com.game.ozanne.gameoz.GameActivity.Presenter.GameBasePresenter;
import com.game.ozanne.gameoz.GameActivity.View.GameBaseView;
import com.game.ozanne.gameoz.serviceSocketIO.EventListener;

public interface GameContract {


    interface ViewGame extends GameBaseView<PresenterGame>, EventListener{

        // Update UI to show the message has been delivered
            void onActionSend();


    }

    interface PresenterGame extends GameBasePresenter, EventListener, ClickOnCaseGrilleGame,CaseAvailableListener {

        // Action à envoyé
        void sendAction(Integer position);

       void  initGridGameManager(RelativeLayout relativeLayout, Context context);
       boolean checkDiffGameObject(GameObject gameObject);


    }








}
