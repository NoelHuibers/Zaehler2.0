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
import com.noelHuibers.counterapp.databinding.ItemCounterBinding;
import com.noelHuibers.counterapp.model.CounterModel;
import com.noelHuibers.counterapp.viewmodel.CounterViewModel;

import java.util.List;


public class CounterAdapter extends BaseAdapter {

    Constant constant;
    Context context;
    List<CounterModel> counterModelList;
    ItemCounterBinding binding;


    public CounterAdapter(Constant constant, Context context) {
        this.constant = constant;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (counterModelList != null) {
            return counterModelList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return counterModelList.get(i + 1);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_counter, null);
            binding = DataBindingUtil.bind(view);
            view.setTag(binding);
        } else {
            binding = (ItemCounterBinding) view.getTag();
        }
        CounterModel counterModel = counterModelList.get(pos);
        binding.setCounterModel(counterModel);
        CounterViewModel presenter = new CounterViewModel(parent.getContext(), constant);
        binding.setVariable(BR.counterViewModel, presenter);
        if (counterModel.getIsAdd()){
            binding.ivAddNew.setVisibility(View.VISIBLE);
            binding.tvItem.setVisibility(View.GONE);
        }else {
            binding.ivAddNew.setVisibility(View.GONE);
            binding.tvItem.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();

    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    public void refreshList(List<CounterModel> refreshList) {
        this.counterModelList = refreshList;
        notifyDataSetChanged();
    }
}