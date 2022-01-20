package com.noelHuibers.counterapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.ItemVehicleBinding;
import com.noelHuibers.counterapp.model.CountingObjectModel;
import com.noelHuibers.counterapp.viewholders.VehicleViewHolder;
import com.noelHuibers.counterapp.viewmodel.CountingObjectViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse CountingObjectAdapter ist ein Adapter für die CountingObjectModels und wird für die Auswahl der CountingObjects Seite gebraucht.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CountingObjectAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    //Class Variables
    List<CountingObjectModel> vehicleModelList = new ArrayList<>();
    Constant constant;
    private static final String TAG = "PaymentPlanAdapter";

    /**
     * Das ist der Konstruktor für die Klasse CounterAdapter.
     * @param constant;
     * @ensures this.constant = constant;
     */
    public CountingObjectAdapter(Constant constant) {
        this.constant = constant;
    }

    /**
     * Das ist die Methode onCreateViewHolder, sie erstellt die Recyclerview und erstellt einen Binder zu der XML Datei item_vehicle.
     * @param parent;
     * @param viewType;
     * @return VehicleViewHolder(itemVehicleBinding);
     */
    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVehicleBinding itemVehicleBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_vehicle,
                parent,
                false);
        CountingObjectViewModel presenter = new CountingObjectViewModel(parent.getContext(), constant);
        itemVehicleBinding.setVariable(BR.countingObjectViewModel, presenter);
        return new VehicleViewHolder(itemVehicleBinding);
    }

    /**
     * Das ist die Methode onBindViewHolder setzt die CountingObjects in Form einer Liste (paymentPlanModel) an die Richtige Position.
     * @param holder;
     * @param position;
     * @ensures holder.itemVehicleBinding.setVehicleModel(vehicleModelList.get(position));
     */
    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        CountingObjectModel paymentPlanModel = vehicleModelList.get(position);
        holder.itemVehicleBinding.setVehicleModel(paymentPlanModel);
    }

    /**
     * Die Methode getItemCount() gibt die Anzahl an CountingModels zurück.
     * @return vehicleModelList.size()
     */
    @Override
    public int getItemCount() {
        if (vehicleModelList != null) {
            return vehicleModelList.size();
        } else {
            return 0;
        }
    }

    /**
     * Die Methode refreshList refreshed die Liste der CountingObjectModels wenn sich etwas am Datensatz changed (z.B. ein neues CountingObject hinzugefügt wird).
     * @param refreshUserList;
     * @ensures notifyDataSetChanged();
     */
    @SuppressLint("NotifyDataSetChanged")
    public void refreshList(List<CountingObjectModel> refreshUserList) {
        this.vehicleModelList = refreshUserList;
        notifyDataSetChanged();
    }
}
