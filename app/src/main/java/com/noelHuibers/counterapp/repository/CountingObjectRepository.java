package com.noelHuibers.counterapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CountingObjectModel;

import java.util.ArrayList;
import java.util.List;

public class CountingObjectRepository {

    List<CountingObjectModel> vehicleList = new ArrayList<>();
    MutableLiveData<List<CountingObjectModel>> vehicleListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    public CountingObjectRepository(Constant constant) {
        this.constant = constant;
    }

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
