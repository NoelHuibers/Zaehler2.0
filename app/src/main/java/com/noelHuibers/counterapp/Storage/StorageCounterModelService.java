package com.noelHuibers.counterapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class StorageCounterModelService {
    private static final String STORAGE_COUNTER_MODEL_SERVICE = "storage_counter_model";

    /**
     * Get the shared preferences
     *
     * @param context Current context
     * @return Shared preferences of the app
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
        
    }
}
