package com.noelHuibers.counterapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.adapters.VehicleAdapter;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.VehicleActivityBinding;
import com.noelHuibers.counterapp.viewmodel.VehiclesViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.VehicleViewModelFactory;

public class VehiclesActivity extends AppCompatActivity {

    VehicleActivityBinding binding;
    VehiclesViewModel vehiclesViewModel;
    VehicleAdapter vehicleAdapter;
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
        vehiclesViewModel = new ViewModelProvider(this, new VehicleViewModelFactory(getApplication(), constant)).get(VehiclesViewModel.class);
        binding.setVehicleViewModel(vehiclesViewModel);
    }

    private void initRecyclerView() {
        vehicleAdapter = new VehicleAdapter(constant);
        RecyclerView rvUser = binding.rvVehicles;
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(vehicleAdapter);
    }

    private void getVehicle() {
        vehiclesViewModel.getVehicles().observe(this, results -> {
            if (results != null) {
                vehicleAdapter.refreshList(results);
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