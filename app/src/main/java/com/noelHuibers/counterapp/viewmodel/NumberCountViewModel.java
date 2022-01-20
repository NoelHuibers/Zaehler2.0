package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StoragePositionService;
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
    int position;
    String name;
    int imgId;

    public NumberCountViewModel(Context context, Constant constant, NumberCountActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
        counterModels = StorageCounterModelService.getCounter(context);
        this.position = StoragePositionService.getPosition(context).get(0);
        this.counter = counterModels.get(position);
        int value = counter.getNumber();
        this.name = counter.getName();
        this.imgId = counter.getImgId();
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

    public void removeCounter() {
        constant.moveBack();
        counterModels.remove(position);
        StorageCounterModelService.update(context,counterModels);
    }

    public String getName(){
        return name;
    }

    public int getImgId() { return imgId; }

}
