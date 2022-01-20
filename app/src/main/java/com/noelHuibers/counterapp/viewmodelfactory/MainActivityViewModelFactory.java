package com.noelHuibers.counterapp.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.viewmodel.MainActivityViewModel;

/**
 * Die Klasse MainActivityViewModelFactory ist die ViewModelFactory für die Klasse MainActivityViewModel.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class MainActivityViewModelFactory implements ViewModelProvider.Factory {

    //Class Variables
    private final Context context;
    private final Constant constant;

    /**
     * Konstruktor der Klasse MainActivityViewModelFactory.
     * @param context;
     * @param constant;
     */
    public MainActivityViewModelFactory(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(context, constant);
    }
}
