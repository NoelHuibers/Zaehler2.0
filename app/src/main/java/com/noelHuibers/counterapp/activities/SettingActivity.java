package com.noelHuibers.counterapp.activities;

import android.annotation.SuppressLint;
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

/**
 * Die Klasse SettingActivity ist die Activity Klasse für die Setting Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class SettingActivity extends AppCompatActivity {

    //Class Variables
    Constant constant;
    SettingActivityBinding binding;
    SettingViewModel settingViewModel;
    int stepCount;

    /**
     * Die Methode onCreate() beschreibt, was bei dem Erstellen der SettingActivity Seite geschehen soll. Hierbei wird das Viewmodel initalisiert. Und der Adapter zu Constant gesetzt um Änderungen in White-/Darkmodus oder Sprache sofort anzupassen.
     * @param savedInstanceState;
     * @ensures initConstant();
     * @ensures initViewModel();
     * @ensures binding.toggleBtn.setOnCheckedChangeListener((toggleButton, isChecked);
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Erstellt ein Binding zu Constant.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        initConstant();
        initViewModel();
        binding.toggleBtn.setChecked(constant.isNightMode());
        //Setzt den ToggleButton auf checked, wenn der Darkmode an ist, oder auf nicht checked wenn er aus ist.
        binding.toggleBtn.setOnCheckedChangeListener((toggleButton, isChecked) ->
                callUpdateNightMode(isChecked)
        );
        //Setzt den Stepcount der Counter und initalisiert ihn in der Ansicht.
        this.stepCount = CounterModel.stepCount;
        setStepCountInView();
    }

    /**
     * @param isNightMode;
     * @ensures SharedPrefManager.getInstance(this).saveMode(isNightMode);
     * @ensures if isNightMode = AppCompatDelegate.MODE_NIGHT_YES
     * @ensures else AppCompatDelegate.MODE_NIGHT_NO
     */
    private void callUpdateNightMode(boolean isNightMode) {
        SharedPrefManager.getInstance(this).saveMode(isNightMode);
        //Checks if its Nightmode and Sets the AppCompatDelegate
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }

    /**
     * Die Methode initConstant() initalisiert die Konstanen. Diese setzt den Kontext und die Statusbar. Also werden für die Komponenten initalisiert ob diese im Dark/Whitemode angezeigt werden sollen und in welcher Sprache.
     * @ensures constant.setContext(Constant.getConstant(constant));
     * @ensures constant.setStatusBar(Constant.getConstant(constant));
     */
    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    /**
     * Die Methode initViewModel erstellt das Objekt SettingViewModel indem die ViewModelFactory ausgeführt wird. Hierbei wird das Binding zwischen der XML und der Java Klasse gesetzt.
     * @ensures binding.setLifecycleOwner(this);
     * @ensures binding.setSettingViewModel(settingViewModel);
     */
    private void initViewModel() {
        settingViewModel = new ViewModelProvider(this, new SettingViewModelFactory(this, constant, binding)).get(SettingViewModel.class);
        //Setzt diese SettingActivity als Android Lifecycleowner.
        binding.setLifecycleOwner(this);
        //Setzt settingViewModel als ViewModel.
        binding.setSettingViewModel(settingViewModel);
    }

    /**
     * Die Methode onBackPressed() beschreibt, dass beim zurück drücken man auf die Main Seite zurück kommt.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.moveBack();
    }

    /**
     * Die Methode setStepCountInView() setzt den stepCount in den Settings auf den stepCount des CounterModels.
     * @ensures binding.stepcount.setText(String.valueOf(stepCount));
     */
    @SuppressLint("SetTextI18n")
    private void setStepCountInView(){
        //Setzt stepCount in der Ansicht auf die CounterModel stepCount Zahl. Falls die Zahl unter 10 ist macht es eine 0 davor da es besser aussieht.
        if (stepCount <= 9) {
            binding.stepcount.setText(0 + String.valueOf(stepCount));
        }
        else{
            binding.stepcount.setText(String.valueOf(stepCount));
        }
    }
}