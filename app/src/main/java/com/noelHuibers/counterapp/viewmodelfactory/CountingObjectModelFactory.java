package com.noelHuibers.counterapp.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.viewmodel.CountingObjectViewModel;

/**
 * Die Klasse CountingObjectModelFactory ist die ViewModelFactory für die Klasse ConutingObjectViewModel.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CountingObjectModelFactory implements ViewModelProvider.Factory {

    //Class variables
    private final Context context;
    private final Constant constant;

    /**
     * Konstruktor der Klasse CountingObjectModelFactory.
     * @param context;
     * @param constant;
     */
    public CountingObjectModelFactory(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new CountingObjectViewModel(context, constant);
    }
}
