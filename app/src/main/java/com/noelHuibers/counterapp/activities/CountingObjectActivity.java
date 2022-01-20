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

/**
 * Die Klasse CountingObjectActivity ist die Activity Klasse der Seite CountingObject. Sie ist verbunden mit dem XML Dokument und beschreibt die Vorgänge nach Android Lebenszyklus einer Seite. Es werden also Methoden, wie onCreate() und onResume() erstellt und die Seite initialisiert.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CountingObjectActivity extends AppCompatActivity {

    //Class Variables
    VehicleActivityBinding binding;
    CountingObjectViewModel countingObjectViewModel;
    CountingObjectAdapter countingObjectAdapter;
    Constant constant;
    private static final String TAG = "VehiclesActivity";
    
    /**
     * Dies ist die Methode onCreate(). Sie ist Teil des Android Seiten Lebenszyklus und in ihr wird festgelegt, was beim erstellen der Seite geschehen soll. In diesem Fall, werden die Recyclerview, das Viewmodel und die CountingObjects initalisiert.
     * @param savedInstanceState;
     * @ensures initializePage();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vehicles);
        initConstant();
        initRecyclerView();
        initUserViewModel();
        getCountingObjects();
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
     * Die Methode initUserViewModel erstellt das Objekt countingObjectViewModel indem die ViewModelFactory ausgeführt wird. Hierbei wird das Binding zwischen der XML und der Java Klasse gesetzt.
     * @ensures binding.setLifecycleOwner(this);
     * @ensures binding.setVehicleViewModel(countingObjectViewModel);
     */
    private void initUserViewModel() {
        countingObjectViewModel = new ViewModelProvider(this, new CountingObjectModelFactory(getApplication(), constant)).get(CountingObjectViewModel.class);
        //Setzt diese Klasse CountingObjectActivity als Android Lifecycleowner.
        binding.setLifecycleOwner(this);
        //Setzt countingObjectViewModel als ViewModel.
        binding.setVehicleViewModel(countingObjectViewModel);
    }

    /**
     * Die Methode initRecyclerView() initalisiert die Recyclerview. Hierbei wird der countingObjectAdapter erstellt um die Recyclerview mit der Anzahl der CountingObjects zu verknüpfen.
     * @ensures rvUser.setLayoutManager(new LinearLayoutManager(this));
     * @ensures rvUser.setAdapter(countingObjectAdapter);
     */
    private void initRecyclerView() {
        countingObjectAdapter = new CountingObjectAdapter(constant);
        RecyclerView rvUser = binding.rvVehicles;
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(countingObjectAdapter);
    }

    /**
     * Die Methode getCountingObjects() dient zum observen der countingObjects.
     * @ensures countingObjectViewModel.getVehicles().observe;
     */
    private void getCountingObjects() {
        countingObjectViewModel.getVehicles().observe(this, results -> {
            if (results != null) {
                countingObjectAdapter.refreshList(results);
                Log.i(TAG, "initUserViewModel: User list obtain " + results.size());
            }
        });
    }

    /**
     * Die Methode onBackPressed() beschreibt, dass beim zurück drücken man auf die Main Seite zurück kommt. 
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.backActivityAnim();
    }
}
