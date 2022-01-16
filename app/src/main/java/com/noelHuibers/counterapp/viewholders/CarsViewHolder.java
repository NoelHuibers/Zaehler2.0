package com.noelHuibers.counterapp.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelHuibers.counterapp.databinding.ItemCarsBinding;

public class CarsViewHolder extends RecyclerView.ViewHolder {

    public ItemCarsBinding itemCarsBinding;

    public CarsViewHolder(@NonNull ItemCarsBinding itemCarsBinding) {
        super(itemCarsBinding.getRoot());
        this.itemCarsBinding = itemCarsBinding;
    }

}
