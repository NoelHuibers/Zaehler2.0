package com.noelHuibers.counterapp.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelHuibers.counterapp.databinding.ItemCounterBinding;

public class CounterViewHolder extends RecyclerView.ViewHolder {

    public ItemCounterBinding itemCounterBinding;

    public CounterViewHolder(@NonNull ItemCounterBinding itemCounterBinding) {
        super(itemCounterBinding.getRoot());
        this.itemCounterBinding = itemCounterBinding;
    }

}
