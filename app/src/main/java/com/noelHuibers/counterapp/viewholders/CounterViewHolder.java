package com.noelHuibers.counterapp.viewholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noelHuibers.counterapp.databinding.ItemCounterBinding;

/**
 * Die Klasse CounterViewHolder initalisiert die Recyclerview der Counter.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CounterViewHolder extends RecyclerView.ViewHolder {

    //Class Variables
    public ItemCounterBinding itemCounterBinding;

    /**
     * Konstruktor der Klasse CounterViewHolder.
     * @param itemCounterBinding;
     * @ensures super(itemCounterBinding.getRoot ());
     * @ensures this.itemCounterBinding = itemCounterBinding;
     */
    public CounterViewHolder(@NonNull ItemCounterBinding itemCounterBinding) {
        super(itemCounterBinding.getRoot());
        this.itemCounterBinding = itemCounterBinding;
    }
}
