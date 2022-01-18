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

/**
 * Die Klasse CounterActivity ist die Activity Klasse für Counter. Sie beschreibt die Initalisierung der Seite, sowie die Recyclerview und das zurückgehen von der Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CounterActivity extends AppCompatActivity {

    //Class Variables
    Constant constant;
    CounterActivityBinding binding;
    CounterViewModel counterViewModel;
    CounterAdapter adapter;

    /**
     * Die Methode onCreate() beschreibt, was bei dem Erstellen der Seite geschehen soll. Hierbei wird das Viewmodel, die Recycler und die CounterList aufgerufen.
     * @param savedInstanceState;
     * @ensures initConstant();
     * @ensures initViewModel();
     * @ensures initRecyclerView();
     * @ensures getCounterList();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_counter);
        initConstant();
        initViewModel();
        initRecyclerView();
        getCounterList();
    }

    /**
     * Die Methode initConstant() setzt die Statusbar.
     * @ensures constant.setContext();
     * @ensures constant.setStatusBar();
     */
    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    /**
     * Die Methode initViewModel() setzt das Viewmodel des Counters.
     * @ensures binding.setCounterViewModel(counterViewModel);
     */
    private void initViewModel() {
        counterViewModel = new ViewModelProvider(this, new CounterViewModelFactory(this, constant)).get(CounterViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setCounterViewModel(counterViewModel);
    }

    /**
     * Die Methode initRecyclerView() initalisiert die Recyclerview, also das Gridlayout auf der Mainpage.
     * @ensures gvCounter.setAdapter(adapter);
     */
    private void initRecyclerView() {
        adapter = new CounterAdapter(constant, this);
        GridView gvCounter = binding.gvCounter;
        gvCounter.setAdapter(adapter);
    }

    /**
     * Die Methode getCounterList() bekommt die Anzahl an Countern, also wie viele Counter zurzeit das Gridlayout füllen.
     * @ensures counterViewModel.getCounters().observe()
     */
    private void getCounterList() {
        //Observed wie viele Counter da sind.
        counterViewModel.getCounters().observe(this, results -> {
            //Wenn keiner da ist wird die Liste refreshed.
            if (results != null) {
                adapter.refreshList(results);
            }
        });
    }

    /**
     * Die Methode onBackPressed() beschreibt, dass es die Seite verlassen soll, wenn zurück gedrückt wird.
     * @ensures constant.moveBack();
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.moveBack();
    }
}