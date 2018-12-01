package com.game.ozanne.gameoz.GameActivity.Model.POJO;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class GameObject {
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

    @SerializedName("color")
    @Expose
    private String colorPlayer;

    public GameObject() {
    }

    public String getColorPlayer() {
        return colorPlayer;
    }

    public void setColorPlayer(String colorPlayer) {
        this.colorPlayer = colorPlayer;
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



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Objects.equals(playerTurn, that.playerTurn) &&
                Objects.equals(tableauGame, that.tableauGame) &&
                Objects.equals(userNameOne, that.userNameOne) &&
                Objects.equals(userNameTwo, that.userNameTwo) &&
                Objects.equals(colorPlayer, that.colorPlayer);
    }


}
