package com.noelHuibers.counterapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Die Klasse StoragePositionService speichert die position persistent auf dem Gerät in einer Gson file.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class StoragePositionService {

    //Class Variables
    private static final String STORAGE_POSITION_MODEL_SERVICE = "storage_position_model";

    /**
     * Getter Methode um den SharedPrefrences Ordner zu bekommen.
     * @param context;
     * @return SharedPrefrences;
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);

    }
    /**
     * Added eine Position als Json Objekt zu den lokal gespeicherten Positions.
     * @param context;
     * @param position;
     * @ensures position.added();
     */
    public static void addPosition(Context context, int position) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        //Bekommt die lokal gespeicherten Positions als ArrayList und added die Position.
        ArrayList<Integer> positions = getPosition(context);
        positions.add(position);
        //Transformiert die ArrayList zu Json und commited dies in den lokalen Speicher.
        String json = gson.toJson(positions);
        editor.putString(STORAGE_POSITION_MODEL_SERVICE, json);
        editor.apply();
    }

    /**
     * Updatet die Json im Speicher, indem die alte Json gelöscht und eine neue JSON mit den neuen positions gespeichert wird.
     * @param context;
     * @param positions;
     * @ensures editor.putString(STORAGE_COUNTER_MODEL_SERVICE, gson.toJson(positions));
     */
    public static void update(Context context, ArrayList<Integer> positions){
        clearPosition(context);
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(positions);
        editor.putString(STORAGE_POSITION_MODEL_SERVICE, json);
        editor.apply();
    }

    /**
     * Entfernt eine Position aus dem lokalen Speicher.
     * @param context;
     * @param position;
     * @ensures poistion.removed();
     */
    public static void removePosition(Context context, int position) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        Gson gson = new Gson();
        ArrayList<Integer> positions = getPosition(context);
        //Checkt ob die lokal gespeicherte ArrayList leer ist.
        if (positions != null && !positions.isEmpty()) {
            for (int p : positions) {
                //Entfernt die position von der Arraylist und bricht die Iterierung ab.
                positions.remove(position);
                break;
            }
        }
        String json = gson.toJson(positions);
        editor.putString(STORAGE_POSITION_MODEL_SERVICE, json);
        editor.apply();
    }

    /**
     * Bekommt die Positions als ArrayList ausgegeben.
     * @param context;
     * @return ArrayList<Integer> positions;
     */
    public static ArrayList<Integer> getPosition(Context context) {
        ArrayList<Integer> positions = new ArrayList<>();
        //Bekommt den Json String aus dem speicher.
        String json = getSharedPreferences(context).getString(STORAGE_POSITION_MODEL_SERVICE, "");
        Gson gson = new Gson();
        //Macht den String der Json Datei zu einer ArrayList und returned diesen.
        TypeToken<ArrayList<Integer>> token = new TypeToken<ArrayList<Integer>>() {
        };
        ArrayList<Integer> extractedPositions = gson.fromJson(json, token.getType());
        if (extractedPositions != null) {
            positions = extractedPositions;
        }
        return positions;
    }

    /**
     * Entfernt die Position aus dem lokalen Speicher.
     * @param context;
     * @ensures removeAllPositions();
     */
    public static void clearPosition (Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(STORAGE_POSITION_MODEL_SERVICE);
        editor.apply();
    }
}
