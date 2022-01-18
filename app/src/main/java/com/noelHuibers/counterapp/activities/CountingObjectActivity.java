package com.noelHuibers.counterapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.adapters.CountingObjectAdapter;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.VehicleActivityBinding;
import com.noelHuibers.counterapp.viewmodel.CountingObjectViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.CountingObjectModelFactory;

public class CountingObjectActivity extends AppCompatActivity {

    VehicleActivityBinding binding;
    CountingObjectViewModel countingObjectViewModel;
    CountingObjectAdapter countingObjectAdapter;
    Constant constant;
    private static final String TAG = "VehiclesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicles);
        initConstant();
        initRecyclerView();
        initUserViewModel();
        getVehicle();
    }

    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    private void initUserViewModel() {
        countingObjectViewModel = new ViewModelProvider(this, new CountingObjectModelFactory(getApplication(), constant)).get(CountingObjectViewModel.class);
        binding.setVehicleViewModel(countingObjectViewModel);
    }

    private void initRecyclerView() {
        countingObjectAdapter = new CountingObjectAdapter(constant);
        RecyclerView rvUser = binding.rvVehicles;
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(countingObjectAdapter);
    }

    private void getVehicle() {
        countingObjectViewModel.getVehicles().observe(this, results -> {
            if (results != null) {
                countingObjectAdapter.refreshList(results);
                Log.i(TAG, "initUserViewModel: User list obtain " + results.size());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.backActivityAnim();
    }
}