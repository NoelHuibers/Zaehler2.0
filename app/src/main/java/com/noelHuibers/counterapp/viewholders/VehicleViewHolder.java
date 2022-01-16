package com.noelHuibers.counterapp.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelHuibers.counterapp.databinding.ItemVehicleBinding;


public class VehicleViewHolder extends RecyclerView.ViewHolder{

    public ItemVehicleBinding itemVehicleBinding;

    public VehicleViewHolder(@NonNull ItemVehicleBinding itemVehicleBinding) {
        super(itemVehicleBinding.getRoot());
        this.itemVehicleBinding = itemVehicleBinding;
    }
}