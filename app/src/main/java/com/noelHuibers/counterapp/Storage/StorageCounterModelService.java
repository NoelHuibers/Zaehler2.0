package com.noelHuibers.counterapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.noelHuibers.counterapp.model.CounterModel;

import java.util.ArrayList;

/**
 * Die Klasse StorageCounterModelService speichert die Counter persistent auf dem Gerät in einer Gson file.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class StorageCounterModelService {
    
    //Class Variables
    private static final String STORAGE_COUNTER_MODEL_SERVICE = "storage_counter_model";

    /**
     * Getter Methode um den SharedPrefrences Ordner zu bekommen.
     *
     * @param context;
     * @return SharedPrefrences;
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);

    }
    /**
     * Added einen Counter als Json Objekt zu den lokal gespeicherten Countern.
     *
     * @param context;
     * @param counter;
     * @ensures counter.added();
     */
    public static void addCounter(Context context, CounterModel counter) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();

        // Bekommt die lokal gespeicherten Counter als ArrayList und added den Counter.
        ArrayList<CounterModel> counters = getCounter(context);
        counters.add(counter);

        // Transofrmiert die ArrayList zu Json und commited dies in den lokalen Speicher.
        String json = gson.toJson(counters);
        editor.putString(STORAGE_COUNTER_MODEL_SERVICE, json);
        editor.commit();
    }

    /**
     * Entfernt einen Counter aus dem lokalen Speicher.
     *
     * @param context;
     * @param counter;
     * @ensures counter.removed();
     */
    public static void removeCounter(Context context, CounterModel counter) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        ArrayList<CounterModel> counters = getCounter(context);

        //Checkt ob die lokal gespeicherte ArrayList leer ist.
        if (counters != null && !counters.isEmpty()) {
            for (CounterModel c : counters) {

                //Entfernt den Counter von der Arraylist und bricht die Iterierung ab.
                counters.remove(counter);
                break;
            }
        }
        String json = gson.toJson(counters);
        editor.putString(STORAGE_COUNTER_MODEL_SERVICE, json);
        editor.commit();
    }
    
    /**
     * Bekommt die Counter als ArrayList ausgegeben.
     *
     * @param context;
     * @return ArrayList<CounterModel> counters;
     */
    public static ArrayList<CounterModel> getCounter(Context context) {
        ArrayList<CounterModel> counters = new ArrayList<CounterModel>();

        //Bekommt den Json String aus dem speicher.
        String json = getSharedPreferences(context).getString(STORAGE_COUNTER_MODEL_SERVICE, "");
        Gson gson = new Gson();

        //Macht den String der Json Datei zu einer ArrayList und returned diesen.
        TypeToken<ArrayList<CounterModel>> token = new TypeToken<ArrayList<CounterModel>>() {
        };
        ArrayList<CounterModel> extractedCounters = gson.fromJson(json, token.getType());
        if (extractedCounters != null) {
            counters = extractedCounters;
        }
        return counters;
    }
    
    /**
     * Entfernt die Counter aus dem lokalen Speicher.
     *
     * @param context;
     * @ensures removeAllCounter();
     */
    public static void clearCounter (Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(STORAGE_COUNTER_MODEL_SERVICE);
        editor.commit();
    }
}
