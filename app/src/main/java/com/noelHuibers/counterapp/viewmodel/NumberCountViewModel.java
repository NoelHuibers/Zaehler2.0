package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.NumberCountActivityBinding;
import com.noelHuibers.counterapp.model.CounterModel;

import java.util.ArrayList;

public class NumberCountViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    NumberCountActivityBinding binding;
    CounterModel counter;
    ArrayList<CounterModel> counterModels;

    public NumberCountViewModel(Context context, Constant constant, NumberCountActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
        counterModels = StorageCounterModelService.getCounter(context);
        this.counter = counterModels.get(0);
        int value = counter.getNumber();
        binding.tvCount.setText(String.valueOf(value));
    }

    public void  addClick() {
        counter.countUp();
        int value = counter.getNumber();
        binding.tvCount.setText(String.valueOf(value));
    }

    public void  subClick() {
        counter.countDown();
        int value = counter.getNumber();
        binding.tvCount.setText(String.valueOf(value));
    }

    public void  backClick() {
        constant.moveBack();
        StorageCounterModelService.update(context, counterModels);
    }

}
