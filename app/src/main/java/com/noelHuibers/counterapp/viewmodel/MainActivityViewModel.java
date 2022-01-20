package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StoragePositionService;
import com.noelHuibers.counterapp.activities.NumberCountActivity;
import com.noelHuibers.counterapp.activities.SettingActivity;
import com.noelHuibers.counterapp.activities.CountingObjectActivity;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.repository.CounterRepository;

import java.util.List;

/**
 * Die Klasse MainActivityViewModel beschreibt das Viewmodel der Main Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class MainActivityViewModel extends ViewModel {

    //Class variables
    public ObservableBoolean isCarVisible = new ObservableBoolean();
    public ObservableBoolean isCounterVisible = new ObservableBoolean();
    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final CounterRepository counterRepository;

    /**
     * Der Konstrutkor der Klasse initalisiert das counterRepository.
     * @param constant;
     * @param context;
     * @ensures counterRepository = new CounterRepository(constant);
     */
    public MainActivityViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        counterRepository = new CounterRepository(constant);
        isCarVisible.set(false);
        isCounterVisible.set(true);
    }

    /**
     * Die Methode getCounters() returned das CounterRepository mit den Countern.
     * @return counterRepository.getCountersMutableLiveData(context);
     */
    public MutableLiveData<List<CounterModel>> getCounters() {
        return counterRepository.getCountersMutableLiveData(context);
    }

    /**
     * Die Methode addNewClick() ruft die CountingObjectActivity Seite auf.
     * @param data;
     * @ensures constant.startActivityIntent(context, CountingObjectActivity.class);
     */
    public void addNewClick(CounterModel data) {
        constant.startActivityIntent(context, CountingObjectActivity.class);
    }

    /**
     * Die Methode showItemClick() ruft die NumberCountActivity Seite auf.
     * @param data;
     * @ensures constant.startActivityIntent(context, NumberCountActivity.class);
     */
    public void showItemClick(CounterModel data) {
        int position = data.getPosition();
        StoragePositionService.clearPosition(context);
        StoragePositionService.addPosition(context, position);
        constant.startActivityIntent(context, NumberCountActivity.class);
    }

    /**
     * Die Methode showCountItemClick() ruft die NumberCountActivity Seite auf.
     * @param data;
     * @ensures constant.startActivityIntent(context, NumberCountActivity.class);
     */
    public void showCountItemClick(CounterModel data) {
        int position = data.getPosition();
        StoragePositionService.clearPosition(context);
        StoragePositionService.addPosition(context, position);
        constant.startActivityIntent(context, NumberCountActivity.class);
    }

    //Quellcode für ein Update, funktioniert bereits, jedoch wird die imgId noch nicht weiter gegeben. Schnell in Version 2.1 umsetzbar. XML Datei dafür ist item_cars.
    /*
    public boolean onLongClick(View view,CounterModel counterModel) {
         isCarVisible.set(false);
         isCounterVisible.set(true);
    }*/

    /**
     * Die Methode settingClick() ruft die Seite Settings auf.
     * @ensures constant.startActivityIntent(context, SettingActivity.class);
     */
    public void settingClick() {
        constant.startActivityIntent(context, SettingActivity.class);
    }
}