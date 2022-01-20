package com.noelHuibers.counterapp.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.common.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse CounterRepository ist ein Repository das die default Counter erstellt.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CounterRepository {

    //Class Variables
    List<CounterModel> counterModelList = new ArrayList<>();
    MutableLiveData<List<CounterModel>> counterListMutableLiveDate = new MutableLiveData<>();
    Constant constant;

    /**
     * Konstruktor der Klasse CounterRepository. Setzt constant.
     * @param constant;
     * @ensures this.constant = constant;
     */
    public CounterRepository(Constant constant) {
        this.constant = constant;
    }

    /**
     * Die Methode getCountersMutableLiveData() erstellt die CounterModelList. Sie added den Addbutton, welcher immer in der App zusehen ist und gibt die counterModelList als MutableLiveData zurück.
     * @param context;
     * @return counterListMutableLiveData;
     */
    public MutableLiveData<List<CounterModel>> getCountersMutableLiveData(Context context) {
        ArrayList<CounterModel> counterModelList = StorageCounterModelService.getCounter(context);
        CounterModel counterModel = new CounterModel("AddButton",true);
        counterModelList.add(counterModel);
        counterListMutableLiveDate.postValue(counterModelList);
        return counterListMutableLiveDate;
    }
}
