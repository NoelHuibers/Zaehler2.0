package com.noelHuibers.counterapp.model;

public class CounterModel {


    //Class variables
    static int id;
    public int number = 0;
    int stepCount = 1;
    CountingObject name;

    /**
     * Diese ist der Konstruktor der Klasse Zähler. Diese Funktion wird beim erstellen des Objekts ausgeführt und setzt den Namen des Objekts, welches gezählt wird.
     * @param name;
     * @ensures this.name = name;
     */
    public CounterModel(CountingObject name){
        this.name = name;
        id++;
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
    public void setStepCount(int number) {
        this.stepCount=number;
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



    public void setStartValue(int number) {
        this.number = number;
    }
}
