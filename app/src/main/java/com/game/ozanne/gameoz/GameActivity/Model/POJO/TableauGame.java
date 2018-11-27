package com.game.ozanne.gameoz.GameActivity.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableauGame {

    @SerializedName("typeCase")
    @Expose
    private String typeCase;
    @SerializedName("typeTiles")
    @Expose
    private Integer typeTiles;

    public TableauGame() {
    }

    public String getTypeCase() {
        return typeCase;
    }

    public void setTypeCase(String typeCase) {
        this.typeCase = typeCase;
    }

    public Integer getTypeTiles() {
        return typeTiles;
    }

    public void setTypeTiles(Integer typeTiles) {
        this.typeTiles = typeTiles;
    }
}
