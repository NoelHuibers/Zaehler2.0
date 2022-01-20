package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.Storage.StoragePositionService;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.SettingActivityBinding;
import com.noelHuibers.counterapp.model.CounterModel;

/**
 * Die Klasse SettingViewModel beschreibt das Viewmodel der Setting Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class SettingViewModel extends ViewModel {

    //Class variables
    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    SettingActivityBinding binding;
    int stepCount = CounterModel.stepCount;

    /**
     * Dies ist der Konstruktor der Klasse SettingViewModel.
     * @param binding;
     * @param constant;
     * @param context;
     */
    public SettingViewModel(Context context, Constant constant, SettingActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
    }

    /**
     * Die Methode onBackClicked lässt den Nutzer zur Main Seite zurück kommen und speichert den gesetzten stepcount.
     * @ensures constant.moveBack()
     * @ensures CounterModel.setStepCount(Integer.parseInt(binding.stepcount.getText().toString()));
     */
    public void onBackClick() {
        constant.moveBack();
        CounterModel.setStepCount(Integer.parseInt(binding.stepcount.getText().toString()));
    }

    /**
     * Die Methode deleteAll() löscht alle Counter.
     * @ensures StorageCounterModelService.clearCounter(context);
     */
    public void deleteAll() {
        StorageCounterModelService.clearCounter(context);
        StoragePositionService.clearPosition(context);
    }
}
