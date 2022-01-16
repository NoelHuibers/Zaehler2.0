package com.noelHuibers.counterapp.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.common.Constant;

import java.util.ArrayList;
import java.util.List;


public class CounterRepository {

    List<CounterModel> counterModelList = new ArrayList<>();
    MutableLiveData<List<CounterModel>> counterListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    public CounterRepository(Constant constant) {
        this.constant = constant;
    }

    public MutableLiveData<List<CounterModel>> getCountersMutableLiveData(Context context) {
        ArrayList<CounterModel> counterModelList = StorageCounterModelService.getCounter(context);
        CounterModel counterModel = new CounterModel("AddButton",true);
        counterModelList.add(counterModel);
        counterListMutableLiveDate.postValue(counterModelList);
        return counterListMutableLiveDate;
    }

}
