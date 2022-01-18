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

public class CountingObjectViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final CountingObjectRepository countingObjectRepository;

    public CountingObjectViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        countingObjectRepository = new CountingObjectRepository(constant);
    }

    public MutableLiveData<List<CountingObjectModel>> getVehicles() {
        return countingObjectRepository.getVehiclesMutableLiveData();
    }

    public void onItemClick(CountingObjectModel data) {
        StorageCounterModelService.addCounter(context, new CounterModel(data.getName(),false));
        constant.moveBack();
    }

    public void onBackClick() {
        constant.moveBack();
    }
}
