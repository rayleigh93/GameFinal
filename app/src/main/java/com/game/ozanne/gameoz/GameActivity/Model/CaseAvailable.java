package com.game.ozanne.gameoz.GameActivity.Model;

import java.util.ArrayList;
import java.util.List;

public class CaseAvailable {


    List<Integer> tableauPositionAvailable;


    public CaseAvailable() {
        this.tableauPositionAvailable = new ArrayList<>();
    }


    public List<Integer> getTableauPositionAvailable() {
        return tableauPositionAvailable;
    }

    public void setTableauPositionAvailable(List<Integer> tableauPositionAvailable) {
        this.tableauPositionAvailable = tableauPositionAvailable;
    }
}
