package com.noelHuibers.counterapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CarModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarRepository {

    List<CarModel> carModelList = new ArrayList<>();
    MutableLiveData<List<CarModel>> carListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    public CarRepository(Constant constant) {
        this.constant = constant;
    }

    public MutableLiveData<List<CarModel>> getCarsMutableLiveData() {
        carModelList.clear();
        carModelList.add(new CarModel(R.drawable.ic_cars,12, false));
        carModelList.add(new CarModel(R.drawable.ic_bus, 12,true));
        carModelList.add(new CarModel(R.drawable.ic_cars,12, false));
        carModelList.add(new CarModel(R.drawable.ic_bus,12, false));
        carModelList.add(new CarModel(R.drawable.ic_cars, 12,false));
        Collections.sort(carModelList, (abc1, abc2) -> Boolean.compare(abc1.isAdd(),abc2.isAdd()));
        carListMutableLiveDate.postValue(carModelList);
        return carListMutableLiveDate;
    }

}
