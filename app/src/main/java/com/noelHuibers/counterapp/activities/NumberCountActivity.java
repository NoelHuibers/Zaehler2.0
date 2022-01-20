package com.noelHuibers.counterapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.NumberCountActivityBinding;
import com.noelHuibers.counterapp.viewmodel.NumberCountViewModel;
import com.noelHuibers.counterapp.viewmodelfactory.NumberCountViewModelFactory;

/**
 * Die Klasse NumberCountActivity ist die Activity Klasse für die NumberCount Seite (also Seite wo man hoch und runterzählen kann).
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class NumberCountActivity extends AppCompatActivity {

    //Class Variables
    Constant constant;
    NumberCountActivityBinding binding;
    NumberCountViewModel numberCountViewModel;
    public int position;

    /**
     * Die Methode onCreate() beschreibt, was bei dem Erstellen der NumberCountActivity Seite geschehen soll. Hierbei wird das Viewmodel initalisiert.
     * @param savedInstanceState;
     * @ensures initConstant();
     * @ensures initViewModel();
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_number_count);
        initConstant();
        initViewModel();
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
     * Die Methode initViewModel erstellt das Objekt numberCountViewModel indem die ViewModelFactory ausgeführt wird. Hierbei wird das Binding zwischen der XML und der Java Klasse gesetzt.
     * @ensures binding.setLifecycleOwner(this);
     * @ensures binding.setNumberCountViewModel(numberCountViewModel);
     */
    private void initViewModel() {
        numberCountViewModel = new ViewModelProvider(this, new NumberCountViewModelFactory(this, constant, binding)).get(NumberCountViewModel.class);
        //Setzt diese NumberCountActivity als Android Lifecycleowner.
        binding.setLifecycleOwner(this);
        //Setzt numberCountViewModel als ViewModel.
        binding.setNumberCountViewModel(numberCountViewModel);
    }

    /**
     * Die Methode onBackPressed() beschreibt, dass beim zurück drücken man auf die Main Seite zurück kommt.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        constant.moveBack();
    }
}