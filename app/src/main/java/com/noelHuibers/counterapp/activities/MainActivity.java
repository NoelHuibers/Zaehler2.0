package com.noelHuibers.counterapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.adapters.CarAdapter;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.MainActivityBinding;
import com.noelHuibers.counterapp.viewmodel.MainActivityViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.MainActivityViewModelFactory;

public class MainActivity extends AppCompatActivity {

    Constant constant;
    MainActivityBinding binding;
    MainActivityViewModel mainActivityViewModel;
    CarAdapter carAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initConstant();
        initViewModel();
        initRecyclerView();
        getCarsLis();
        constant.applyMode();
    }

    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    private void initViewModel() {
        mainActivityViewModel = new ViewModelProvider(this, new MainActivityViewModelFactory(this, constant)).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMainActivityViewModel(mainActivityViewModel);
    }


    private void initRecyclerView() {
        carAdapter = new CarAdapter(constant, this);
        GridView gvCars = binding.gvCars;
        gvCars.setAdapter(carAdapter);
    }

    private void getCarsLis() {
        mainActivityViewModel.getCars().observe(this, results -> {
            if (results != null) {
                carAdapter.refreshList(results);
                Log.i(TAG, "getCarsLis: " + results.toString());
            }
        });
    }
}