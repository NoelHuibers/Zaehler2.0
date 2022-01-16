package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.VehicleModel;
import com.noelHuibers.counterapp.repository.VehiclesRepository;

import java.util.List;

public class VehiclesViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final VehiclesRepository vehiclesRepository;

    public VehiclesViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        vehiclesRepository = new VehiclesRepository(constant);
    }

    public MutableLiveData<List<VehicleModel>> getVehicles() {
        return vehiclesRepository.getVehiclesMutableLiveData();
    }

    public void onItemClick(VehicleModel data) {
        constant.moveBack();
    }

    public void onBackClick() {
        constant.moveBack();
    }
}
