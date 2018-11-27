package com.game.ozanne.gameoz.GameActivity.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameObjectGrid {
    @SerializedName("playerTurn")
    @Expose
    private Boolean playerTurn;
    @SerializedName("tableauGame")
    @Expose
    private List<TableauGame> tableauGame = null;
    @SerializedName("userNameOne")
    @Expose
    private String userNameOne;
    @SerializedName("userNameTwo")
    @Expose
    private String userNameTwo;

    public GameObjectGrid() {
    }

    public Boolean getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public List<TableauGame> getTableauGame() {
        return tableauGame;
    }

    public void setTableauGame(List<TableauGame> tableauGame) {
        this.tableauGame = tableauGame;
    }

    public String getUserNameOne() {
        return userNameOne;
    }

    public void setUserNameOne(String userNameOne) {
        this.userNameOne = userNameOne;
    }

    public String getUserNameTwo() {
        return userNameTwo;
    }

    public void setUserNameTwo(String userNameTwo) {
        this.userNameTwo = userNameTwo;
    }
}
