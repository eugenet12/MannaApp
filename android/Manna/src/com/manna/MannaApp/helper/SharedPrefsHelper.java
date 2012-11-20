package com.manna.MannaApp.helper;

import android.content.Context;
import android.content.SharedPreferences;
import com.manna.MannaApp.R;

public class SharedPrefsHelper {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String KEY_FIRST = "first";

    private static final String KEY_NAME = "name";
    private static final String KEY_YEAR = "year";
    private static final String KEY_EMAIL = "email";

    public SharedPrefsHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveName(String name) {
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    public String getName() {
        return sharedPreferences.getString(KEY_NAME, "");
    }

    public void saveYear(String year) {
        editor.putString(KEY_YEAR, year);
        editor.commit();
    }

    public String getYear() {
        return sharedPreferences.getString(KEY_YEAR, "");
    }

    public void saveEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    public void setFirst() {
        editor.putBoolean(KEY_FIRST, true);
        editor.commit();
    }

    public boolean getFirst() {
        return sharedPreferences.getBoolean(KEY_FIRST, false);
    }
}
