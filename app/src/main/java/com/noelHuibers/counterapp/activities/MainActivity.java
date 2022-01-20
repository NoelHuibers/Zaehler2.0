package com.noelHuibers.counterapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.GridView;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.adapters.CounterAdapter;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.MainActivityBinding;
import com.noelHuibers.counterapp.viewmodel.MainActivityViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.MainActivityViewModelFactory;

/**
 * Die Klasse MainActivity ist die Activity Klasse für die Main Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class MainActivity extends AppCompatActivity {

    //Class Variables
    Constant constant;
    MainActivityBinding binding;
    MainActivityViewModel mainActivityViewModel;
    CounterAdapter counterAdapter;
    private static final String TAG = "MainActivity";

    /**
     * Die Methode onCreate() beschreibt, was bei dem Erstellen der Main Seite geschehen soll. Hierbei wird das Viewmodel, die Recycler und die CounterList aufgerufen.
     * @param savedInstanceState;
     * @ensures initConstant();
     * @ensures initViewModel();
     * @ensures initRecyclerView();
     * @ensures getCounterList();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initConstant();
        initViewModel();
        initRecyclerView();
        getCounterList();
        constant.applyMode();
    }

    /**
     * Die Methode onResume() beschreibt, was beim zurückkommen auf die Main Seite geschehen soll. Hierbei soll die CounterListe neu bekommen werden, da je nachdem ob ein Counter hinzugefügt oder entfernt wurde die Recyclerview geupdatet werden muss.
     * @ensures getCounterList();
     */
    @Override
    protected void onResume() {
        super.onResume();
        getCounterList();
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
     * Die Methode initViewModel erstellt das Objekt mainActivityViewModel indem die ViewModelFactory ausgeführt wird. Hierbei wird das Binding zwischen der XML und der Java Klasse gesetzt.
     * @ensures binding.setLifecycleOwner(this);
     * @ensures binding.setMainActivityViewModel(mainActivityViewModel);
     */
    private void initViewModel() {
        mainActivityViewModel = new ViewModelProvider(this, new MainActivityViewModelFactory(this, constant)).get(MainActivityViewModel.class);
        //Setzt diese Klasse MainActivity als Android Lifecycleowner.
        binding.setLifecycleOwner(this);
        //Setzt mainActivityViewModel als ViewModel.
        binding.setMainActivityViewModel(mainActivityViewModel);
    }

    /**
     * Die Methode initRecyclerView() initalisiert die Recyclerview. Hierbei wird der CounterAdapter erstellt um die GridView mit der Anzahl der Counter zu verknüpfen.
     * @ensures GridView gvCounter = binding.gvCounter;
     * @ensures gvCounter.setAdapter(counterAdapter);
     */
    private void initRecyclerView() {
        counterAdapter = new CounterAdapter(constant, this);
        GridView gvCounter = binding.gvCounter;
        gvCounter.setAdapter(counterAdapter);
    }

    /**
     * Die Methode getCounterList() dient zum observen der Counter.
     * @ensures mainActivityViewModel.getCounters().observe;
     */
    private void getCounterList() {
        mainActivityViewModel.getCounters().observe(this, results -> {
            if (results != null) {
                counterAdapter.refreshList(results);
            }
        });
    }
}