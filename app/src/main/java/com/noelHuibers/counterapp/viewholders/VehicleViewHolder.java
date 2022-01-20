package com.noelHuibers.counterapp.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelHuibers.counterapp.databinding.ItemVehicleBinding;

/**
 * Die Klasse CounterViewHolder initalisiert die Recyclerview der CountingObjects.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class VehicleViewHolder extends RecyclerView.ViewHolder{

    //Class Variables
    public ItemVehicleBinding itemVehicleBinding;

    /**
     * Konstruktor der Klasse VehicleViewHolder.
     * @param itemVehicleBinding;
     * @ensures super(itemVehicleBinding.getRoot())
     * @ensures this.itemVehicleBinding = itemVehicleBinding;
     */
    public VehicleViewHolder(@NonNull ItemVehicleBinding itemVehicleBinding) {
        super(itemVehicleBinding.getRoot());
        this.itemVehicleBinding = itemVehicleBinding;
    }
}