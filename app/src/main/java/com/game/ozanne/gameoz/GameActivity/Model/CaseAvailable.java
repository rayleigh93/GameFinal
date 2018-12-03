package com.game.ozanne.gameoz.GameActivity.Model;

import java.util.ArrayList;
import java.util.List;

public class CaseAvailable {


    List<Integer> tableauPositionAvailable;
    int positionCaseBase;


    public CaseAvailable() {
        this.tableauPositionAvailable = new ArrayList<>();
        this.positionCaseBase = -1;
    }


    public List<Integer> getTableauPositionAvailable() {
        return tableauPositionAvailable;
    }

    public void setTableauPositionAvailable(List<Integer> tableauPositionAvailable) {
        this.tableauPositionAvailable = tableauPositionAvailable;
    }


    public int getPositionCaseBase() {
        return positionCaseBase;
    }

    public void setPositionCaseBase(int positionCaseBase) {
        this.positionCaseBase = positionCaseBase;
    }
}
