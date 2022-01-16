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

public class Constant {

    public Context context;
    Toast toast;
    private static final String TAG = "Constant";

    public static synchronized Constant getConstant(Constant mInstance) {
        if (mInstance == null) {
            mInstance = new Constant();
        }
        return mInstance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setStatusBar(Context context) {
        Window window = ((Activity) context).getWindow();
        View decorView = window.getDecorView();
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

             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                 ((Activity) context).getWindow().setStatusBarColor(context.getResources().getColor(R.color.black, context.getTheme()));
                 ((Activity) context).getWindow().setNavigationBarColor(context.getResources().getColor(R.color.black, context.getTheme()));
                 WindowInsetsController wic = decorView.getWindowInsetsController();
                 //wic.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);
             } else {
                 //decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                 ((Activity) context).getWindow().setStatusBarColor(ActivityCompat.getColor(context, R.color.black));
                 ((Activity) context).getWindow().setNavigationBarColor(ActivityCompat.getColor(context, R.color.black));
             }

        }

    }


    public void hideKeyboard() {
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

    public void startActivityIntent(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        context.startActivity(intent);
        nextActivityAnim();
    }

    public void nextActivityAnim() {
        ((Activity) context).overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        hideKeyboard();
    }

    public void backActivityAnim() {
        try {
            ((Activity) context).overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
            hideKeyboard();
        } catch (Exception ignored) {

        }
    }

    public void moveBack() {
        ((Activity) context).finish();
        backActivityAnim();
    }


    public void displayToast(String message) {
        if (toast != null)
            toast.cancel();
        hideKeyboard();
        setToastLayout(message, R.layout.toast_view_normal);
    }

    public void displaySuccessToast(String message) {
        if (toast != null)
            toast.cancel();
        hideKeyboard();
        setToastLayout(message, R.layout.toast_view_successh);
    }

    public void displayErrorToast(String message) {
        if (toast != null)
            toast.cancel();
        hideKeyboard();
        setToastLayout(message, R.layout.toast_view_error);
    }


    private void setToastLayout(String message, int toastLayout) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(toastLayout,
                ((Activity) context).findViewById(R.id.toast_layout_root));
        TextView text = layout.findViewById(R.id.text);
        text.setText(message);
        toast = new Toast(context.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


    public void applyMode() {
        if (isNightMode()) {
            setDarkMode();
        } else {
            setLightMode();
        }
    }

    public Boolean isNightMode() {
        return SharedPrefManager.getInstance(context).getMode();
    }

    public void setDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void setLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}
