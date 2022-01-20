package com.noelHuibers.counterapp.Storage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Die Klasse SharedPrefManager speichert die Nutzereinstellungen des genutzen Android Gerätes heraus und stellt die App dementsprechend standartmäßig auf Dark- oder Whitemode.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class SharedPrefManager {

    //Class Variables
    private static final String SHARED_PREF_NAME = "my_shared_preff";
    @SuppressLint("StaticFieldLeak")
    public static SharedPrefManager mInstance;
    @SuppressLint("StaticFieldLeak")
    private static Context mCtx;

    /**
     * Diese ist der Konstruktor der Klasse SharedPrefManager. Diese Funktion wird beim erstellen des Objekts ausgeführt und setzt die Contextvariable des Nutzers.
     * @param mCtx;
     * @ensures this.name = name;
     */
    private SharedPrefManager(Context mCtx) {
        SharedPrefManager.mCtx = mCtx;
    }

    /**
     * Diese Methode synchronisiert die Instanz des Objekts.
     * @param mCtx;
     * @ensures mInstance = new SharedPrefManager(mCtx)
     * @return mInstance;
     */
    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    /**
     * Diese Methode checkt ob der Nutzer eingeloggt ist und die Einstellung damit abrufbar
     * @return sharedPrefrences.getString();
     */
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("name",null) != null;
    }

    /**
     * Diese Methode cleared die gespeicherten sharedPrefrences.
     * @ensures SharedPrefrences.editor.clear()
     * @ensures SharedPrefrences.editor.apply()
     */
    public void clear() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    
   /**
    * Diese Methode speichert den Startmodus der App zu jeweils Dark- oder Whitemode je nach Präferenz in den Nutzereinstellungen.
    * @param darkMode;
    * @ensures SharedPrefrences.editor.putBoolean("Mode", darkmode)
    * @ensures SharedPrefrences.editor.apply()
    */
    public void saveMode(boolean darkMode) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Mode", darkMode);
        editor.apply();
    }

    /**
     * Diese Methode ist die getterMethode um den bevorzugten Anzeigemodus zu bekommen
     * @return sharedPrefrences.getBoolean("Mode", true);
     */
    public boolean getMode() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("Mode", true);
    }
}
