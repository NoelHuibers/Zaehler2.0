package com.noelHuibers.counterapp.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.viewmodel.CounterViewModel;


public class CounterViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;
    private final Constant constant;


    public CounterViewModelFactory(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CounterViewModel(context, constant);
    }
}
