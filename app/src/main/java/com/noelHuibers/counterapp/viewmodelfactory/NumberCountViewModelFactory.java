package com.noelHuibers.counterapp.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.NumberCountActivityBinding;
import com.noelHuibers.counterapp.viewmodel.NumberCountViewModel;

/**
 * Die Klasse NumberCountViewModelFactory ist die ViewModelFactory für die Klasse NumberCountViewModel.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class NumberCountViewModelFactory implements ViewModelProvider.Factory {

    //Class Variables
    private final Context context;
    private final Constant constant;
    private final NumberCountActivityBinding binding;

    /**
     * Konstruktor der Klasse NumberCountViewModelFactory.
     * @param context;
     * @param constant;
     * @param binding;
     */
    public NumberCountViewModelFactory(Context context, Constant constant, NumberCountActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NumberCountViewModel(context, constant, binding);
    }
}