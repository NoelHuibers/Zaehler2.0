package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.common.Constant;

public class SettingViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;


    public SettingViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;

    }

    public void onBackClick() {
        constant.moveBack();
    }

}
