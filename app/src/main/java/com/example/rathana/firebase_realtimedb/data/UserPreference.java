package com.example.rathana.firebase_realtimedb.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import com.example.rathana.firebase_realtimedb.model.User;

import javax.microedition.khronos.egl.EGLDisplay;

public class UserPreference {
    public static  final String UID="UID";
    public static  final String NAME="NAME";
    public static  final String GENDER="GENDER";
    public static  final String PASSWORD="PASSWORD";

    public static  final String USER_PREF="user_pref";

    public  static  void save(Context context, User user){
        SharedPreferences.Editor editor= context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE).edit();
        editor.putString(UID,user.getUid());
        editor.putString(NAME,user.getName());
        editor.putString(GENDER,user.getGender());
        editor.putString(PASSWORD,user.getPassword());
        editor.commit();
    }

    public static User read(Context context){
        SharedPreferences pref=context.getSharedPreferences(USER_PREF,Context.MODE_PRIVATE);
        return new User(pref.getString(UID,""),
                pref.getString(NAME,""),
                pref.getString(GENDER,""),
                pref.getString(PASSWORD,""));
    }

}
