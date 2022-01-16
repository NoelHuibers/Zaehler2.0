package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.activities.NumberCountActivity;
import com.noelHuibers.counterapp.activities.SettingActivity;
import com.noelHuibers.counterapp.activities.VehiclesActivity;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CarModel;
import com.noelHuibers.counterapp.repository.CarRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    public ObservableBoolean isCarVisible = new ObservableBoolean();
    public ObservableBoolean isCounterVisible = new ObservableBoolean();
    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final CarRepository carRepository;

    public MainActivityViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        carRepository = new CarRepository(constant);
        isCarVisible.set(true);
        isCounterVisible.set(false);
    }


    public MutableLiveData<List<CarModel>> getCars() {
        return carRepository.getCarsMutableLiveData();
    }

    public void addNewClick(CarModel data) {
        constant.startActivityIntent(context, VehiclesActivity.class);
    }

    public void showItemClick(CarModel data) {
        //constant.startActivityIntent(context, CounterActivity.class);
        isCarVisible.set(false);
        isCounterVisible.set(true);
    }

    public void showCountItemClick(CarModel data) {
        isCarVisible.set(true);
        isCounterVisible.set(false);
        //constant.startActivityIntent(context, CounterActivity.class);
    }

    public boolean onLongClick(View view,CarModel carModel) {
        constant.startActivityIntent(context, NumberCountActivity.class);
        return true;
    }

    public void settingClick() {
        constant.startActivityIntent(context, SettingActivity.class);
    }


}
