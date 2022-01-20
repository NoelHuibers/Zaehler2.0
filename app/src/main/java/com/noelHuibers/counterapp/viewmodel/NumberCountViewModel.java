package com.noelHuibers.counterapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.noelHuibers.counterapp.Storage.StoragePositionService;
import com.noelHuibers.counterapp.Storage.StorageCounterModelService;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.NumberCountActivityBinding;
import com.noelHuibers.counterapp.model.CounterModel;

import java.util.ArrayList;

/**
 * Die Klasse NumberCountViewModel beschreibt das Viewmodel der NumberCount Seite.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class NumberCountViewModel extends ViewModel {

    //Class Variables
    @SuppressLint("StaticFieldLeak")
    Context context;
    Constant constant;
    NumberCountActivityBinding binding;
    CounterModel counter;
    ArrayList<CounterModel> counterModels;
    int position;
    String name;
    int imgId;

    /**
     * Der Konstrutkor der Klasse initalisiert den angezeigten Counter.
     * @param binding;
     * @param constant;
     * @param context;
     * @ensures binding.tvCount.setText(String.valueOf(value));
     */
    public NumberCountViewModel(Context context, Constant constant, NumberCountActivityBinding binding) {
        this.context = context;
        this.constant = constant;
        this.binding = binding;
        counterModels = StorageCounterModelService.getCounter(context);
        this.position = StoragePositionService.getPosition(context).get(0);
        this.counter = counterModels.get(position);
        int value = counter.getNumber();
        this.name = counter.getName();
        this.imgId = counter.getImgId();
        binding.tvCount.setText(String.valueOf(value));
    }

    /**
     * Addiert den stepcount auf den Counter auf.
     * @ensures counter.countUp();
     */
    public void addClick() {
        counter.countUp();
        int value = counter.getNumber();
        binding.tvCount.setText(String.valueOf(value));
    }

    /**
     * Subtrahiert den stepcount von dem Counter.
     * @ensures counter.countDown();
     */
    public void subClick() {
        counter.countDown();
        int value = counter.getNumber();
        binding.tvCount.setText(String.valueOf(value));
    }

    /**
     * Die Methode onBackClicked lässt den Nutzer zur Main Seite zurück kommen und speichert die Änderungen am Counter.
     * @ensures constant.moveBack()
     * @ensures StorageCounterModelService.update(context, counterModels);
     */
    public void backClick() {
        constant.moveBack();
        StorageCounterModelService.update(context, counterModels);
    }

    /**
     * Die Methode removeCounter() entfernt den derzeitig sichtbaren Counter und führt zurück zur Hauptseite.
     * @ensures constant.moveBack();
     * @ensures counterModels.remove(position);
     * @ensures StorageCounterModelService.update(context,counterModels);
     */
    public void removeCounter() {
        constant.moveBack();
        counterModels.remove(position);
        StorageCounterModelService.update(context,counterModels);
    }

    /**
     * Gibt den Namen des Counters zurück für die XML Datei.
     * @return name;
     */
    public String getName(){
        return name;
    }

    /**
     * Gibt die ImageId des Counters zurück für die XML Datei.
     * @return imgId;
     */
    public int getImgId() { return imgId; }

}
