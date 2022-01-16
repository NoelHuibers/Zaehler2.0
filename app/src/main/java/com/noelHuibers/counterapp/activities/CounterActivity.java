package com.noelHuibers.counterapp.activities;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.adapters.CounterAdapter;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.CounterActivityBinding;
import com.noelHuibers.counterapp.viewmodel.CounterViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.CounterViewModelFactory;

public class CounterActivity extends AppCompatActivity {


    Constant constant;
    CounterActivityBinding binding;
    CounterViewModel counterViewModel;
    CounterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter);
        initConstant();
        initViewModel();
        initRecyclerView();
        getCarsLis();
    }

    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    private void initViewModel() {
        counterViewModel = new ViewModelProvider(this, new CounterViewModelFactory(this, constant)).get(CounterViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setCounterViewModel(counterViewModel);
    }


    private void initRecyclerView() {
        adapter = new CounterAdapter(constant, this);
        GridView gvCounter = binding.gvCounter;
        gvCounter.setAdapter(adapter);
    }

    private void getCarsLis() {
        counterViewModel.getCounters().observe(this, results -> {
            if (results != null) {
                adapter.refreshList(results);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.moveBack();
    }
}