package com.noelHuibers.counterapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.VehicleModel;

import java.util.ArrayList;
import java.util.List;

public class VehiclesRepository {

    List<VehicleModel> vehicleList = new ArrayList<>();
    MutableLiveData<List<VehicleModel>> vehicleListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    public VehiclesRepository(Constant constant) {
        this.constant = constant;
    }

    public MutableLiveData<List<VehicleModel>> getVehiclesMutableLiveData() {
        vehicleList.clear();
        vehicleList.add(new VehicleModel("Car"));
        vehicleList.add(new VehicleModel("Bus"));
        vehicleList.add(new VehicleModel("Bird"));
        vehicleList.add(new VehicleModel("Frog"));
        vehicleList.add(new VehicleModel("Bike"));
        vehicleListMutableLiveDate.postValue(vehicleList);
        return vehicleListMutableLiveDate;
    }
}
