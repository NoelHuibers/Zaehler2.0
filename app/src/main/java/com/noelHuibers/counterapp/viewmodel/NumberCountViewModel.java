package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.NumberCountActivityBinding;

public class NumberCountViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    NumberCountActivityBinding binding;

    public NumberCountViewModel(Context context, Constant constant, NumberCountActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
    }


    public void  addClick() {
        int value = Integer.parseInt(binding.tvCount.getText().toString());
        value = value + 1;
        binding.tvCount.setText(String.valueOf(value));
    }

    public void  subClick() {
        int value = Integer.parseInt(binding.tvCount.getText().toString());
        if (value < 1)
            return;
        value = value - 1;
        binding.tvCount.setText(String.valueOf(value));
    }

    public void  backClick() {
        constant.moveBack();
    }

}
