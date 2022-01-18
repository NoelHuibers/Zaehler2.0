package com.noelHuibers.counterapp.adapters;

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
import com.noelHuibers.counterapp.viewmodel.VehiclesViewModel;

import java.util.ArrayList;
import java.util.List;


public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

    List<CountingObjectModel> vehicleModelList = new ArrayList<>();
    Constant constant;
    private static final String TAG = "PaymentPlanAdapter";

    public VehicleAdapter(Constant constant) {
        this.constant = constant;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemVehicleBinding itemVehicleBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_vehicle,
                parent,
                false);

        VehiclesViewModel presenter = new VehiclesViewModel(parent.getContext(), constant);
        itemVehicleBinding.setVariable(BR.vehiclesViewModel, presenter);
        return new VehicleViewHolder(itemVehicleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        CountingObjectModel paymentPlanModel = vehicleModelList.get(position);
        holder.itemVehicleBinding.setVehicleModel(paymentPlanModel);
    }

    @Override
    public int getItemCount() {
        if (vehicleModelList != null) {
            return vehicleModelList.size();
        } else {
            return 0;
        }
    }

    public void refreshList(List<CountingObjectModel> refreshUserList) {
        this.vehicleModelList = refreshUserList;
        notifyDataSetChanged();
    }
}
