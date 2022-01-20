package com.noelHuibers.counterapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CountingObjectModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse CountingObjectRepository ist ein Repository das die default CountingObjects erstellt.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CountingObjectRepository {

    //Class Variables
    List<CountingObjectModel> vehicleList = new ArrayList<>();
    MutableLiveData<List<CountingObjectModel>> vehicleListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    /**
     * Konstruktor der Klasse CountingObjectRepository. Setzt constant.
     * @param constant;
     * @ensures this.constant = constant;
     */
    public CountingObjectRepository(Constant constant) {
        this.constant = constant;
    }

    /**
     * Die Methode getVehiclesMutableLiveData() erstellt die CountingObjectModelList. Sie added die Optionen und gibt diese als countingObjectModelList als MutableLiveData zurück.
     * @return counterListMutableLiveData;
     */
    public MutableLiveData<List<CountingObjectModel>> getVehiclesMutableLiveData() {
        vehicleList.clear();
        vehicleList.add(new CountingObjectModel("Car"));
        vehicleList.add(new CountingObjectModel("Bus"));
        vehicleList.add(new CountingObjectModel("Bird"));
        vehicleList.add(new CountingObjectModel("Frog"));
        vehicleList.add(new CountingObjectModel("Bike"));
        vehicleListMutableLiveDate.postValue(vehicleList);
        return vehicleListMutableLiveDate;
    }
}
