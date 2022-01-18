package com.noelHuibers.counterapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.Storage.SharedPrefManager;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.SettingActivityBinding;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.viewmodel.SettingViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.SettingViewModelFactory;

public class SettingActivity extends AppCompatActivity {

    Constant constant;
    SettingActivityBinding binding;
    SettingViewModel settingViewModel;
    int stepCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        initConstant();
        initViewModel();
        binding.toggleBtn.setChecked(constant.isNightMode());
        binding.toggleBtn.setOnCheckedChangeListener((toggleButton, isChecked) ->
                callUpdateNightMode(isChecked)
        );
        this.stepCount = CounterModel.stepCount;
        setStepCountInView();
    }


    private void callUpdateNightMode(boolean isNightMode) {
        SharedPrefManager.getInstance(this).saveMode(isNightMode);
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }

    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    private void initViewModel() {
        settingViewModel = new ViewModelProvider(this, new SettingViewModelFactory(this, constant, binding)).get(SettingViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSettingViewModel(settingViewModel);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.moveBack();
    }

    private void setStepCountInView(){
        if (stepCount <= 9) {
            binding.stepcount.setText(0 + String.valueOf(stepCount));
        }
        else{
            binding.stepcount.setText(String.valueOf(stepCount));
        }
    }
}