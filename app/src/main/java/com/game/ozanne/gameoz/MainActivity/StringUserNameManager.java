package com.game.ozanne.gameoz.MainActivity;

import android.widget.EditText;

import com.game.ozanne.gameoz.serviceSocketIO.EventService;
import com.game.ozanne.gameoz.serviceSocketIO.EventServiceImpl;

public class StringUserNameManager {

    public static final int SIZE_MIN_FOR_USERNAME = 6;





    public StringUserNameManager() {
    }

    public static boolean check(EditText editText){
           StringUserNameManager stringUserNameManager = new StringUserNameManager();

           if(editText.getText().toString().trim().length() > SIZE_MIN_FOR_USERNAME)
               return true;
           else
               return false;
    }




}
