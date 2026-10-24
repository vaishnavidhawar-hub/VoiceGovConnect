package com.voicegov.ai.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(Constants.PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(int id, String name, String mobile, String email) {
        editor.putBoolean(Constants.KEY_IS_LOGGED_IN, true);
        editor.putInt(Constants.KEY_USER_ID, id);
        editor.putString(Constants.KEY_USER_NAME, name);
        editor.putString(Constants.KEY_USER_MOBILE, mobile);
        editor.putString(Constants.KEY_USER_EMAIL, email);
        editor.commit();
    }

    public void setLanguage(String language) {
        editor.putString(Constants.KEY_LANGUAGE, language);
        editor.commit();
    }

    public String getLanguage() {
        return pref.getString(Constants.KEY_LANGUAGE, "en");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(Constants.KEY_IS_LOGGED_IN, false);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        user.put(Constants.KEY_USER_NAME, pref.getString(Constants.KEY_USER_NAME, null));
        user.put(Constants.KEY_USER_MOBILE, pref.getString(Constants.KEY_USER_MOBILE, null));
        user.put(Constants.KEY_USER_EMAIL, pref.getString(Constants.KEY_USER_EMAIL, null));
        return user;
    }

    public int getUserId() {
        return pref.getInt(Constants.KEY_USER_ID, -1);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
    }
}