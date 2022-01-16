package com.noelHuibers.counterapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.model.CountingObject;
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

    public MutableLiveData<List<CounterModel>> getCountersMutableLiveData() {
        counterModelList.clear();
        counterModelList.add(new CounterModel(CountingObject.CAR));
        counterModelList.add(new CounterModel(CountingObject.BUS));
        counterModelList.add(new CounterModel(CountingObject.BIRD));
        counterListMutableLiveDate.postValue(counterModelList);
        return counterListMutableLiveDate;
    }

}
