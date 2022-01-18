package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.SettingActivityBinding;
import com.noelHuibers.counterapp.model.CounterModel;

public class SettingViewModel extends ViewModel {


    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    SettingActivityBinding binding;
    int stepCount = CounterModel.stepCount;

    public SettingViewModel(Context context, Constant constant, SettingActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
    }

    public void onBackClick() {
        constant.moveBack();
        CounterModel.setStepCount(Integer.parseInt(binding.stepcount.getText().toString()));
    }
}
