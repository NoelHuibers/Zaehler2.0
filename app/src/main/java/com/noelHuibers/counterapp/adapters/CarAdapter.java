package com.noelHuibers.counterapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;

import com.noelHuibers.counterapp.R;
import com.noelHuibers.counterapp.common.Constant;
import com.noelHuibers.counterapp.databinding.ItemCarsBinding;
import com.noelHuibers.counterapp.model.CarModel;
import com.noelHuibers.counterapp.viewmodel.MainActivityViewModel;

import java.util.List;


public class CarAdapter extends BaseAdapter {

    Constant constant;
    Context context;
    List<CarModel> carModelList;
    ItemCarsBinding itemCarsBinding;

    public CarAdapter(Constant constant, Context context) {
        this.constant = constant;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (carModelList != null) {
            return carModelList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return carModelList.get(i+1);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_cars, null);
            itemCarsBinding = DataBindingUtil.bind(view);
            view.setTag(itemCarsBinding);
        } else {
            itemCarsBinding = (ItemCarsBinding) view.getTag();
        }



        CarModel carModel = carModelList.get(pos);
        itemCarsBinding.setCarModel(carModel);

        MainActivityViewModel presenter = new MainActivityViewModel(parent.getContext(), constant);
        itemCarsBinding.setVariable(BR.mainActivityViewModel, presenter);

        if (carModel.isAdd()){
            itemCarsBinding.ivAddNew.setVisibility(View.VISIBLE);
        }else {
            itemCarsBinding.ivAddNew.setVisibility(View.GONE);
        }

        return itemCarsBinding.getRoot();

    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public void refreshList(List<CarModel> refreshList) {
        this.carModelList = refreshList;
        notifyDataSetChanged();
    }

}
