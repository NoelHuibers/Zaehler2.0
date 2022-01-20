package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.model.CountingObjectModel;
import com.noelHuibers.counterapp.repository.CountingObjectRepository;

import java.util.List;

/**
 * Die Klasse CountingObjectViewModel beschreibt das Viewmodel der CountingObjects Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CountingObjectViewModel extends ViewModel {

    //Class Variables
    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final CountingObjectRepository countingObjectRepository;

    /**
     * Der Konstruktor der Klasse CountingObjectViewModel setzt den context und die constant, außerdem wir das countingObjectRepository initalisiert.
     * @param constant;
     * @param context;
     * @ensures countingObjectRepository = new CountingObjectRepository(constant);
     */
    public CountingObjectViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        countingObjectRepository = new CountingObjectRepository(constant);
    }

    /**
     * Die Methode getVehicles() returned die CountingObjects in der MutableLiveData list.
     * @return countingObjectRepository.getVehiclesMutableLiveData();
     */
    public MutableLiveData<List<CountingObjectModel>> getVehicles() {
        return countingObjectRepository.getVehiclesMutableLiveData();
    }

    /**
     * Die Methode onItemClick() added einen Counter mit den jeweiligen Daten des CountingObjectModels zu dem Speicher und geht zu der Main Seite zurück.
     * @ensures counter.setPosition(position);
     * @ensures StorageCounterModelService.addCounter(context, counter);
     * @ensures constant.moveBack();
     */
    public void onItemClick(CountingObjectModel data) {
        //Bekommt die position des Counters anhand der Arraylänge.
        int position = StorageCounterModelService.getCounter(context).size();
        //Erstellt den neuen Counter anhand des eingegeben Namens und setzt die Position.
        CounterModel counter = new CounterModel(data.getName(),false);
        counter.setPosition(position);
        //Added den Counter und geht zurück.
        StorageCounterModelService.addCounter(context, counter);
        constant.moveBack();
    }

    /**
     * Die Methode onBackClicked lässt den Nutzer zur Main Seite zurück kommen.
     * @ensures constant.moveBack()
     */
    public void onBackClick() {
        constant.moveBack();
    }
}