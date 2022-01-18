package com.noelHuibers.counterapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.NumberCountActivityBinding;
import com.noelHuibers.counterapp.viewmodel.NumberCountViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.NumberCountViewModelFactory;

public class NumberCountActivity extends AppCompatActivity {

    Constant constant;
    NumberCountActivityBinding binding;
    NumberCountViewModel numberCountViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_number_count);
        initConstant();
        initViewModel();
    }

    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    private void initViewModel() {
        numberCountViewModel = new ViewModelProvider(this, new NumberCountViewModelFactory(this, constant, binding)).get(NumberCountViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setNumberCountViewModel(numberCountViewModel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.moveBack();
    }
}