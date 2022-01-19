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
        getVehicle();
    }

    /**
     * Die Methode initConstat() dient zur initalisierung der Constanten, also der Statusbar und des Context.
     * @ensures constantIsInit();
     */
    private void initConstant() {
        constant = Constant.getConstant(constant);
        constant.setContext(this);
        constant.setStatusBar(this);
    }

    /**
     * Die Methode initUserViewModel() dient zur initalisierung des Viewmodels.
     * @ensures viewmodelIsInit();
     */
    private void initUserViewModel() {
        countingObjectViewModel = new ViewModelProvider(this, new CountingObjectModelFactory(getApplication(), constant)).get(CountingObjectViewModel.class);
        binding.setVehicleViewModel(countingObjectViewModel);
    }
    
    /**
     * Die Methode initRecyclerView() dient zur initalisierung der Recyclerview.
     * @ensures  recyclerview.setAdapter(countingObjectAdapter);
     */
    private void initRecyclerView() {
        countingObjectAdapter = new CountingObjectAdapter(constant);
        RecyclerView rvUser = binding.rvVehicles;
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        rvUser.setAdapter(countingObjectAdapter);
    }

    /**
     * Die Methode getVehicle() dient zum observen der countingObjects.
     * @ensures countingObjectViewModel.getVehicles().observe;
     */
    private void getVehicle() {
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
