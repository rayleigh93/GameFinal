package com.game.ozanne.gameoz.GameActivity.Model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.game.ozanne.gameoz.GameActivity.Model.POJO.GameObject;
import com.game.ozanne.gameoz.GameActivity.View.GridGameView;
import com.game.ozanne.gameoz.R;

public class GridGameManager {

    private Context mContext;
    private ClickOnCaseGrilleGame mClickListener;

    private static final int NUMBER_CELL_COLLUMN = 8;

    public GridGameManager(Context mContext, ClickOnCaseGrilleGame clickOnCaseGrilleGame) {
        this.mContext = mContext;
        this.mClickListener = clickOnCaseGrilleGame;
    }


    // Si il n'existe pas encore de GameObject, on crée alors la GridGameView pour la première fois
    public GridGameView createOrUpdateGridLayout(GameObject gameObject,RelativeLayout layoutParent){

        GridGameView gridView = new GridGameView(mClickListener);
        LinearLayout parent = new LinearLayout(mContext);
        parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        parent.setWeightSum(8);
        parent.setOrientation(LinearLayout.VERTICAL);
       // layoutParent.addView(parent);
        gridView.setmLayoutParent(parent);

        for(int i = 0 ; i<8;i++){

           // View view = LayoutInflater.from(mContext).inflate(R.layout.cell, parent, false);
            LinearLayout ligne = new LinearLayout(mContext);
           // LinearLayout ligne = (LinearLayout) view.findViewById(R.id.cell_game);
            ligne.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1));
            ligne.setWeightSum(8);
            ligne.setOrientation(LinearLayout.HORIZONTAL);
            parent.addView(ligne);

            for(int p =0;p<8;p++){

                LinearLayout layout = new LinearLayout(mContext);
                gridView.getmListLayout().add(layout);

                layout.setOnClickListener(gridView);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
                params.setMargins(1,1,1,1);
                layout.setLayoutParams(params);
                layout.setBackgroundResource(checkTile(gameObject.getTableauGame().get((8*i)+p).getTypeTiles()));
                layout.setOrientation(LinearLayout.HORIZONTAL);

                ligne.addView(layout);
            }
        }


        initializeImageViewGrid(gameObject,gridView);

        Log.i("TEST","size : "+gridView.getmListLayout().size());
        return gridView;
    }

    private void initializeImageViewGrid(GameObject gameObject, GridGameView gridView){

        for(int i=0;i<8*8;i++){

            ImageView imageView = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.setMargins(10,10,10,10);
            imageView.setLayoutParams(lp);

            imageView.setImageResource(checkPion(gameObject.getTableauGame().get(i).getTypeCase()));

            gridView.getmListImageView().add(i,imageView);
            gridView.getmListLayout().get(i).addView(gridView.getmListImageView().get(i));
        }
    }





    // Quand on clique sur une case on update l'UI
    public void updateGridLayoutWithCaseAvaible(CaseAvailable caseAvailable,GridGameView gridGameView,GameObject gameObject){

        if(caseAvailable.getTableauPositionAvailable().size() > 0){
            for(int i = 0; i<caseAvailable.getTableauPositionAvailable().size();i++){
                gridGameView.getmListImageView().get(caseAvailable.tableauPositionAvailable.get(i)).setImageResource(R.drawable.ic_launcher_foreground);
            }

        }else {
            for(int i = 0; i<gridGameView.getmListImageView().size();i++){
                if(gameObject.getTableauGame().get(i).getTypeCase().equals("vide")) {
                    gridGameView.getmListImageView().get(i).setImageResource(0);
                }
            }
        }


    }


    // En fonction du GameObject on crée la tile map
    private int checkPion(String tileNumber) {
        switch (tileNumber) {
            case "vide":
                return 0;

            case "yellow":
                return R.drawable.jelly_yellow;

            case "blue":
                return R.drawable.jelly_blue;

            case "plein":
                return 0;

            default:
                return 0;

        }
    }

    private int checkTile(int tileNumber){
        switch (tileNumber){
            case 0 :
                return R.drawable.grass;

            case 1:
                return R.drawable.grass_black;

            case 2:
                return R.drawable.sand;

            case 3 :
                return R.drawable.water;

            default:
                return R.drawable.grass;
        }
    }




}
