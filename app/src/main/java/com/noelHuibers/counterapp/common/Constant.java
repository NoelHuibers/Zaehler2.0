package com.noelHuibers.counterapp.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.Storage.SharedPrefManager;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;

/**
 * Die Klasse Constant sind die Konstant gesetzten Eigenschaften der App Instanz und kümmert sich um Error Meldungen, sowie hidekeyboard activitys, den Dark & Lightmode, uvm..
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class Constant {

    //Class Variables.
    public Context context;
    Toast toast;
    private static final String TAG = "Constant";

    /**
     * Die Methode getConstant returned die Instanz der App.
     * @param mInstance;
     * @return mInstance;
     */
    public static synchronized Constant getConstant(Constant mInstance) {
        if (mInstance == null) {
            mInstance = new Constant();
        }
        return mInstance;
    }

    /**
     * Die ist die setterMethode des Attributes context;
     * @param context;
     * @ensures this.context = context;
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Die Methode setStatusBar setzt die Statusbar in jeweils Dark/Whitemode.
     * @param context;
     */
    public void setStatusBar(Context context) {
        Window window = ((Activity) context).getWindow();
        View decorView = window.getDecorView();
        //Statusbar wird in Lightmode gesetzt.
         if (!isNightMode()) {
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                 ((Activity) context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.white, context.getTheme()));
                 ((Activity) context).getWindow().setNavigationBarColor(context.getResources().getColor(R.color.white, context.getTheme()));
                 WindowInsetsController wic = decorView.getWindowInsetsController();
                 wic.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);
             } else {
                 decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                 ((Activity) context).getWindow().setStatusBarColor(ActivityCompat.getColor(context, R.color.white));
                 ((Activity) context).getWindow().setNavigationBarColor(ActivityCompat.getColor(context, R.color.white));
             }

             ((Activity) context).getWindow().setNavigationBarColor(ContextCompat.getColor(context, R.color.white));
        } else {
        //Statusbar wird in Lightmode gesetzt.
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                 ((Activity) context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black, context.getTheme()));
                 ((Activity) context).getWindow().setNavigationBarColor(context.getResources().getColor(R.color.black, context.getTheme()));
                 WindowInsetsController wic = decorView.getWindowInsetsController();
             } else {
                 ((Activity) context).getWindow().setStatusBarColor(ActivityCompat.getColor(context, R.color.black));
                 ((Activity) context).getWindow().setNavigationBarColor(ActivityCompat.getColor(context, R.color.black));
             }

        }

    }

    /**
     * Die Methode hideKeyboard() lässt das Keyboard verschwinden auf der Ansicht nach Eintragen von zum Beispiel dem Stepcount.
     */
    public void hideKeyboard() {
        //Versucht das Keyboard zu verstecken
        try {
            InputMethodManager imm
                    = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = ((Activity) context).getCurrentFocus();
            if (view == null) {
                view = new View(context);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception exception) {
            Log.i(TAG, "hideKeyboard: Exception " + exception.getMessage());
        }
    }

    /**
     * Die Methode startAcitvityIntent() startet eine neue Activity. (Ermöglicht den Wechsel auf eine neue Seite auf Android)
     * @param packageContext;
     * @param cls;
     * @ensures context.startActivity(intent);
     * @ensures nextActivityAnim();
     */
    public void startActivityIntent(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        context.startActivity(intent);
        nextActivityAnim();
    }

    /**
     * Die Methode nextActivityAnim hided das Keyboard falls noch nicht getan bei Seitenwechsel und überschreibt alle zurzeitigen Transitions (Eingabe von Stepcount wird z.B. abgebrochen, wenn nicht beendet).
     * @ensures ((Activity) context).overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
     * @ensures hideKeyboard();
     */
    public void nextActivityAnim() {
        ((Activity) context).overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        hideKeyboard();
    }

    /**
     * Die Methode backActivityAnim() überrschreibt die zurzeitige Activity indem es die letzte überschreibt und exited und hided das Keyboard. Beispiel: Wechsel von der Stepcount Eingabe zum DeleteCounters Button.
     * @ensures ((Activity) context).overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
     * @ensures hideKeyboard()
     */
    public void backActivityAnim() {
        try {
            ((Activity) context).overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            hideKeyboard();
        } catch (Exception ignored) {

        }
    }

    /**
     * Die Methode moveBack() finsihed die Activity und führ backActivityAnim() aus, also schließt die letzte und entfernt das Keyboard um zur Seite davor wieder zu gelangen.
     */
    public void moveBack() {
        ((Activity) context).finish();
        backActivityAnim();
    }

    /**
     * Die Methode displayToast displayed das Toast mit der Meldung.
     * @param message;
     */
    public void displayToast(String message) {
        if (toast != null)
            toast.cancel();
        hideKeyboard();
        setToastLayout(message, R.layout.toast_view_normal);
    }

    /**
     * Die Methode displaySuccessToast displayed ein successToast mit der Meldung.
     * @param message;
     * @ensures hideKeyboard();
     * @ensures setToastLayout(message, R.layout.toast_view_successh);
     */
    public void displaySuccessToast(String message) {
        if (toast != null)
            toast.cancel();
        hideKeyboard();
        setToastLayout(message, R.layout.toast_view_successh);
    }

    /**
     * Die Methode displaySuccessToast displayed ein ErrorToast mit der Fehlermeldung.
     * @param message;
     * @ensures hideKeyboard();
     * @ensures setToastLayout(message, R.layout.toast_view_error);
     */
    public void displayErrorToast(String message) {
        if (toast != null)
            toast.cancel();
        hideKeyboard();
        setToastLayout(message, R.layout.toast_view_error);
    }

    /**
     * Die Methode setToastLayout setzt das Layout des Toasts für Meldungen.
     * @param message;
     * @param toastLayout;
     */
    private void setToastLayout(String message, int toastLayout) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(toastLayout, ((Activity) context).findViewById(R.id.toast_layout_root));
        TextView text = layout.findViewById(R.id.text);
        text.setText(message);
        toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    /**
     * Die Methode applyMode() applyed Dark oder LightMode je nachdem was vorher gesetzt war.
     * @ensures isNightMode ? setDarkMode() : setLightMode();
     */
    public void applyMode() {
        if (isNightMode()) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    /**
     * Die Methode isNightMode() bekommt den zurzeitigen Modus aus dem SharedPrefManager Ordner.
     * @return SharedPrefManager.getInstance(context).getMode();
     */
    public Boolean isNightMode() {
        return SharedPrefManager.getInstance(context).getMode();
    }

    /**
     * Die Methode setDarkMode() setzt den Modus auf DarkMode.
     * @ensures AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
     */
    public void setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    /**
     * Die Methode setDarkMode() setzt den Modus auf LightMode.
     * @ensures AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
     */
    public void setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}