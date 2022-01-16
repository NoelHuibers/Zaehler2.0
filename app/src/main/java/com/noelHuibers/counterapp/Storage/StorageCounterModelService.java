package com.noelHuibers.counterapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.noelHuibers.counterapp.model.CounterModel;

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
    /**
     * Add a report as json object to the not synced local stored reports
     *
     * @param context Current context
     * @param counter  Report which is to be stored
     */
    public static void addCounter(Context context, CounterModel counter) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();

        // Get the local store reports as array list and add the report
        ArrayList<CounterModel> counters = getCounter(context);
        counters.add(counter);

        // Transform array list to json
        String json = gson.toJson(counters);

        // Put json in the local storage
        editor.putString(STORAGE_COUNTER_MODEL_SERVICE, json);
        editor.commit();
    }

    /**
     * Remove not synced local stored report from storage
     *
     * @param context Current context
     * @param counter  Report which is to be deleted
     */
    public static void removeCounter(Context context, CounterModel counter) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();

        // Get the local store reports as array list and add the report
        ArrayList<CounterModel> counters = getCounter(context);

        // Check if the local stored reports are empty
        if (counters != null && !counters.isEmpty()) {

            // Iterate over the reports
            for (CounterModel c : counters) {

                // Remove report from array list
                counters.remove(counter);
                break;
            }
        }
        // Transform the array list to json
        String json = gson.toJson(counters);

        // Put json to local storage
        editor.putString(STORAGE_COUNTER_MODEL_SERVICE, json);
        editor.commit();
    }
    /**
     * Get the not synced local stored reports from storage
     *
     * @param context Current context
     * @return Local store reports as array list
     */
    public static ArrayList<CounterModel> getCounter(Context context) {
        ArrayList<CounterModel> counters = new ArrayList<CounterModel>();

        // Get not synced stored reports from logged in user as json string
        String json = getSharedPreferences(context).getString(STORAGE_COUNTER_MODEL_SERVICE, "");
        Gson gson = new Gson();

        // Transform json string to array list
        TypeToken<ArrayList<CounterModel>> token = new TypeToken<ArrayList<CounterModel>>() {
        };
        ArrayList<CounterModel> extractedCounters = gson.fromJson(json, token.getType());
        if (extractedCounters != null) {
            counters = extractedCounters;
        }
        return counters;
    }
    /**
     * Delete the not synced report local storage of the logged in user
     *
     * @param context Current context
     */
    public static void clearCounter (Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(STORAGE_COUNTER_MODEL_SERVICE);
        editor.commit();
    }
}
