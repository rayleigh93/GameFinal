package com.game.ozanne.gameoz.GameActivity.Model;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.Pair;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
import com.game.ozanne.gameoz.GameActivity.Presenter.CaseAvailableListener;
import com.game.ozanne.gameoz.GameActivity.View.GridGameView;
import com.game.ozanne.gameoz.R;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GameManager  {


    public final static String TAG = GameManager.class.getName();

    //private static boolean secondClick = false;

    GameObject mGameObject;
    boolean secondClick = false;
    CaseAvailableListener listener;
    CaseAvailable caseAvailable;


    public GameManager(CaseAvailableListener listener) {
        this.mGameObject = new GameObject();
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean isGameObjectChanged(GameObject gameObject){

        if(!this.mGameObject.equals(gameObject)){
            // Si il y a eu un changement
            this.mGameObject = gameObject;
            return true;
        }
        else {
            // SI rien n'as changé alors on n'update rien
            return false;
        }
    }

    public void clickOnCase(int position){

        if (mGameObject.getPlayerTurn() &&
                mGameObject.getTableauGame().get(position).getTypeTiles() != 3) {

            if (mGameObject.getColorPlayer().equals("yellow")) {
                if (mGameObject.getTableauGame().get(position).getTypeCase().equals("yellow")) {
                    secondClick = true;
                    // Fait apparaitre les case potientiells
                    caseAvailable = generateAvailableCase(position);
                    listener.sendCaseAvailable(caseAvailable);
                }
                else if(mGameObject.getTableauGame().get(position).getTypeCase().equals("vide")
                        && secondClick){
                    for(int i =0;i<caseAvailable.getTableauPositionAvailable().size();i++){
                        if(caseAvailable.getTableauPositionAvailable().get(i) == position){
                            secondClick = false;
                            caseAvailable = new CaseAvailable();
                            listener.sendCaseAvailable(caseAvailable);
                        }
                    }
                }

                else {
                    secondClick = false;
                    caseAvailable = new CaseAvailable();
                }

            }
            else if (mGameObject.getColorPlayer().equals("blue")) {
                if (mGameObject.getTableauGame().get(position).getTypeCase().equals("blue")) {
                   secondClick = true;
                    // Fait apparaitre les case potientiells
                    generateAvailableCase(position);
                    caseAvailable = generateAvailableCase(position);
                    listener.sendCaseAvailable(caseAvailable);
                }
                else if(mGameObject.getTableauGame().get(position).getTypeCase().equals("vide")
                        && secondClick){
                    for(int i =0;i<caseAvailable.getTableauPositionAvailable().size();i++){
                        if(caseAvailable.getTableauPositionAvailable().get(i) == position){
                            secondClick = false;
                            caseAvailable = new CaseAvailable();
                            listener.sendCaseAvailable(caseAvailable);
                        }
                    }
                }
                else {
                   secondClick = false;
                    caseAvailable = new CaseAvailable();
                }
            }




        }
        else {
            secondClick = false;
            caseAvailable = new CaseAvailable();
        }



    }

    public CaseAvailable generateAvailableCase(int position){
        CaseAvailable caseAvailable = new CaseAvailable();
        int index ;

        for(int p = 0;p<8;p++) {
            for (int i = 0;i<8; i++) {
                index =  (8*p) + i;
                if(index == position){
                    List< Pair<Integer,Integer>> lists = createListCompare(i,p);
                    for(int o = 0;o<lists.size();o++){
                        int positionAvailable = lists.get(o).first + ( lists.get(o).second *8 );
                        if( (lists.get(o).first >= 0 && lists.get(o).first < 8)
                                && (lists.get(o).second >= 0 && lists.get(o).second < 8)
                                && (mGameObject.getTableauGame().get(positionAvailable).getTypeCase().equals("vide")))
                        {
                            caseAvailable.tableauPositionAvailable.add(positionAvailable);
                            }
                    }

                }
            }
        }

        return caseAvailable;
    }

    public  List< Pair<Integer,Integer>> createListCompare(int i ,int p){
        List< Pair<Integer,Integer>> lists = new ArrayList<>(8);

        lists.add(new Pair<>( (i-1),(p-1)));
        lists.add(new Pair<>( (i),(p-1)));
        lists.add(new Pair<>( (i+1),(p-1)));
        lists.add(new Pair<>( (i-1),(p)));
        lists.add(new Pair<>( (i+1),(p)));
        lists.add(new Pair<>( (i-1),(p+1)));
        lists.add(new Pair<>( (i),(p+1)));
        lists.add(new Pair<>( (i+1),(p+1)));

        return lists;
    }


    public GameObject getmGameObject() {
        return mGameObject;
    }

    public void setmGameObject(GameObject mGameObject) {
        this.mGameObject = mGameObject;
    }
}
