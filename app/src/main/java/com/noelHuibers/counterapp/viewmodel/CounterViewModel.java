package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.activities.NumberCountActivity;
import com.noelHuibers.counterapp.activities.SettingActivity;
import com.noelHuibers.counterapp.activities.VehiclesActivity;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.repository.CounterRepository;

import java.util.List;


public class CounterViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final CounterRepository counterRepository;

    public CounterViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        counterRepository = new CounterRepository(constant);
    }

    public MutableLiveData<List<CounterModel>> getCounters() {
        return counterRepository.getCountersMutableLiveData(context);
    }

    public void  addNewClick(CounterModel data) {
        constant.startActivityIntent(context, VehiclesActivity.class);
    }

    public void  showItemClick(CounterModel data) {
        constant.startActivityIntent(context, NumberCountActivity.class);
    }
    public void  settingClick() {
        constant.startActivityIntent(context, SettingActivity.class);
    }



}