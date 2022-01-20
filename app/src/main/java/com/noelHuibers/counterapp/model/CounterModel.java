package com.noelHuibers.counterapp.model;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.noelHuibers.counterapp.R;

/**
 * Die Klasse CounterModel beschreibt einen Zählermodel, in welchem man Schrittzahl und zu zählendes Objekt auswählen kann.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CounterModel {

    //Class variables
    static int id;
    public int number = 0;
    public static int stepCount = 1;
    boolean isAdd;
    String name;
    int imgId;
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Diese ist der Konstruktor der Klasse Zähler. Diese Funktion wird beim erstellen des Objekts ausgeführt und setzt den Namen des Objekts, welches gezählt wird.
     * @param name;
     * @ensures this.name = name;
     */
    public CounterModel(String name, boolean isAdd){
        this.isAdd = isAdd;
        this.name = name;
        id++;
        findImgId(name);
    }

    /**
     * Diese Funktion dient zum hochzählen des Zählers. Es wird von der Variable "number" die Schrittzahl hinzugerechnet.
     * @requires stepCount!=0;
     * @ensures this.number=+stepCount;
     */
    public void countUp() {
        this.number+=stepCount;
    }

    /**
     * Diese Funktion dient zum runterzählen des Zählers. Es wird von der Variable "number" die Schrittzahl abgezogen.
     * @requires stepCount!=0;
     * @ensures this.number=-stepCount;
     */
    public void countDown() {
        this.number-=stepCount;
    }

    /**
     * Diese Funktion dient zum resetten des Zählers. Die Variable "number" wird also auf 0 gesetzt.
     * @ensures this.number=0;
     */
    public void reset() {
        this.number=0;
    }


    /**
     * Diese Funktion ist die getter-Methode der Klassenvariable "stepCount". Dies gibt die zurzeitige Schrittzahl des Zählers zurück.
     * @return stepCount;
     */
    public int getStepCount() {
        return stepCount;
    }

    /**
     * Diese Funktion ist die setter-Methode der Klassenvariable "stepCount". Dies setzt den Schrittzähler des Zählers.
     * @param number;
     * @requires number!=0;
     * @ensures this.stepCount=number;
     */
    public static void setStepCount(int number) {
        stepCount=number;
    }

    /**
     * Diese Funktion ist die getter-Methode der Klassenvariable "number". Dies gibt die zurzeitige Zahl des Zählers zurück.
     * @return number;
     */
    public int getNumber() {
        return number;
    }

    /**
     * Diese Funktion ist die setter-Methode der Klassenvariable "number". Dies setzt die Zahl des Zählers auf einen beliebigen Wert.
     * @param number;
     * @ensures this.number=number;
     */
    public void setNumber(int number) {
        this.number=number;
    }

    /**
     * Diese Funktion ist zum Laden des Bildes des ausgewählten Zählerobjektes.
     * @param imageView;
     * @param imgId;
     * @ensures imageView.setImageResource(imgId);
     */
    @BindingAdapter({ "imgId" })
    public static void loadImage(ImageView imageView, int imgId) {
        imageView.setImageResource(imgId);
        /* Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView);*/
    }
    
    /**
     * Diese Funktion ist zum herausfinden, ob das Objekt der Addbutton ist.
     * @return isAdd;
     */
    public boolean getIsAdd() {
        return isAdd;
    }

    /**
     * Diese Funktion ist zum setzen des Objektes zum addButton
     * @param add;
     * @ensures this.isAdd = add;
     */
    public void setAdd(boolean add) {
        this.isAdd = add;
    }
    
    /**
     * Diese Funktion ist die getter Methode der Image Id.
     * @return imgId;
     */
    public int getImgId() {
        return imgId;
    }

    /**
     * Diese Funktion ist die setter Methode der Image Id.
     * @param imgId;
     * @ensures this.imgId = imgId;
     */
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void findImgId(String name){
        String images = name;
        int id;
        switch (images) {
            case "Car":  this.imgId = R.drawable.ic_cars;
                break;
            case "Bus":  this.imgId = R.drawable.ic_bus;
                break;
            default: this.imgId = R.drawable.ic_add;
                break;
        }
    }
}
