package com.noelHuibers.counterapp.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.SettingActivityBinding;
import com.noelHuibers.counterapp.viewmodel.SettingViewModel;

/**
 * Die Klasse SettingViewModelFactory ist die ViewModelFactory für die Klasse SettingViewModel.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class SettingViewModelFactory implements ViewModelProvider.Factory {

    //Class Variables
    private final Context context;
    private final Constant constant;
    private final SettingActivityBinding binding;

    /**
     * Konstruktor der Klasse SettingViewModelFactory.
     * @param context;
     * @param constant;
     * @param binding;
     */
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
