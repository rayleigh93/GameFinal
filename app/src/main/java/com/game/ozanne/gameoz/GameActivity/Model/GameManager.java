package com.game.ozanne.gameoz.GameActivity.Model;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
import com.game.ozanne.gameoz.R;

import java.io.PipedOutputStream;

public class GameManager {


    public final static String TAG = GameManager.class.getName();


    public static void check(GameObject gameObject, GridGameObject gridGameObject, RelativeLayout relativeLayout, int position){

        try {
            if (gameObject.getPlayerTurn() &&
                    gameObject.getTableauGame().get(position).getTypeTiles() != 3) {

                if (gameObject.getColorPlayer().equals("yellow")) {
                    if (gameObject.getTableauGame().get(position).getTypeCase().equals("yellow")) {
                        Log.i("TOTO", "OK Yellow ");
                    }

                } else if (gameObject.getColorPlayer().equals("blue")) {
                    if (gameObject.getTableauGame().get(position).getTypeCase().equals("blue")) {

                        Log.i("TOTO", "OK Blue ");
                    }
                }

            }
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
        }



    }











}
