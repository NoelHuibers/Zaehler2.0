package com.noelHuibers.counterapp.model;

/**
 * Die Klasse VehicleModel beschreibt die Auswahlmöglichkeiten der verschiedenen Counter.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class VehicleModel {

    //Class Variables
    String name;
    
    /**
     * Diese ist der Konstruktor der Klasse VehicleModel. Diese Funktion wird beim erstellen des Objekts ausgeführt und setzt den Namen des Objekts, welches gezählt wird.
     * @param name;
     * @ensures this.name = name;
     */
    public VehicleModel(String name) {
        this.name = name;
    }

    /**
     * Diese ist die getter Methode des Attributes "name".
     * @return name;
     */
    public String getName() {
        return name;
    }

    /**
     * Diese ist die setter Methode des Attributes "name".
     * @param name;
     * @ensures this.name = name;
     */
    public void setName(String name) {
        this.name = name;
    }
}
