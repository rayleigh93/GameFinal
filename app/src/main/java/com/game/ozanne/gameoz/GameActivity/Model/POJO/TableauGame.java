package com.game.ozanne.gameoz.GameActivity.Model.POJO;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableauGame that = (TableauGame) o;
        return Objects.equals(typeCase, that.typeCase) &&
                Objects.equals(typeTiles, that.typeTiles);
    }


}
