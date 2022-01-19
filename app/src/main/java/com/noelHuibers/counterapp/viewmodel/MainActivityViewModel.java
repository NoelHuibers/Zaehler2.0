package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StoragePositionService;
import com.noelHuibers.counterapp.activities.NumberCountActivity;
import com.noelHuibers.counterapp.activities.SettingActivity;
import com.noelHuibers.counterapp.activities.CountingObjectActivity;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.repository.CounterRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    public ObservableBoolean isCarVisible = new ObservableBoolean();
    public ObservableBoolean isCounterVisible = new ObservableBoolean();
    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    private final CounterRepository counterRepository;

    public MainActivityViewModel(Context context, Constant constant) {
        this.context = context;
        this.constant = constant;
        counterRepository = new CounterRepository(constant);
        isCarVisible.set(true);
        isCounterVisible.set(false);
    }


    public MutableLiveData<List<CounterModel>> getCounters() {
        return counterRepository.getCountersMutableLiveData(context);
    }

    public void addNewClick(CounterModel data) {
        constant.startActivityIntent(context, CountingObjectActivity.class);
    }

    public void showItemClick(CounterModel data) {
        int position = data.getPosition();
        StoragePositionService.clearPosition(context);
        StoragePositionService.addPosition(context, position);
        constant.startActivityIntent(context, NumberCountActivity.class);
        //constant.startActivityIntent(context, NumberCountActivity.class);
        //isCarVisible.set(false);
        //isCounterVisible.set(true);
    }

    public void showCountItemClick(CounterModel data) {
        int position = data.getPosition();
        StoragePositionService.clearPosition(context);
        StoragePositionService.addPosition(context, position);
        constant.startActivityIntent(context, NumberCountActivity.class);
    }

    public boolean onLongClick(View view,CounterModel counterModel) {
        constant.startActivityIntent(context, NumberCountActivity.class);
        return true;
    }

    public void settingClick() {
        constant.startActivityIntent(context, SettingActivity.class);
    }


}
