package com.noelHuibers.counterapp.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.common.Constant;

import java.util.ArrayList;
import java.util.List;
import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.model.CounterModel;

public class CarRepository {


    MutableLiveData<List<CounterModel>> carListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    public CarRepository(Constant constant) {
        this.constant = constant;
    }

    public MutableLiveData<List<CounterModel>> getCarsMutableLiveData(Context context) {
        ArrayList<CounterModel> counters = StorageCounterModelService.getCounter(context);
        carListMutableLiveDate.postValue(counters);
        return carListMutableLiveDate;
/*        carModelList.add(new CarModel(R.drawable.ic_cars,12, false));
        carModelList.add(new CarModel(R.drawable.ic_bus, 12,true));
        carModelList.add(new CarModel(R.drawable.ic_cars,12, false));
        carModelList.add(new CarModel(R.drawable.ic_bus,12, false));
        carModelList.add(new CarModel(R.drawable.ic_cars, 12,false));
        Collections.sort(carModelList, (abc1, abc2) -> Boolean.compare(abc1.isAdd(),abc2.isAdd()));
        carListMutableLiveDate.postValue(carModelList);
        return carListMutableLiveDate;*/
    }

}
