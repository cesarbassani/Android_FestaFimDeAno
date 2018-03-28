package com.cesarbassani.festafimdeano.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cesarbassani on 28/03/18.
 */

public class SecurityPreferences {

    private final SharedPreferences mSharedPreferences;

    public SecurityPreferences(Context context) {
        this.mSharedPreferences = context.getSharedPreferences("FimDeAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getStoredString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }
 }
