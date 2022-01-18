package com.noelHuibers.counterapp.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.SettingActivityBinding;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.viewmodel.SettingViewModel;


public class SettingViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;
    private final Constant constant;
    private final SettingActivityBinding binding;


    public SettingViewModelFactory(Context context, Constant constant, SettingActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SettingViewModel(context, constant, binding);
    }
}
