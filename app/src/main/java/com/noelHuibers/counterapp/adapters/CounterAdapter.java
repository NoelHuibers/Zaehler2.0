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
import com.noelHuibers.counterapp.viewmodel.MainActivityViewModel;

import java.util.List;

/**
 * Die Klasse CounterAdapter ist ein Adapter für die CounterModels und wird für die Main Seite gebraucht.
 *
 * @author Noel Huibers
 * @version 2.0.0
 */
public class CounterAdapter extends BaseAdapter {

    //Class Variables
    Constant constant;
    Context context;
    List<CounterModel> counterModelList;
    ItemCounterBinding binding;

    /**
     * Das ist der Konstruktor für die Klasse CounterAdapter.
     * @param constant;
     * @param context;
     * @ensures this.constant = constant;
     * @ensures this.context = context;
     */
    public CounterAdapter(Constant constant, Context context) {
        this.constant = constant;
        this.context = context;
    }

    /**
     * Das ist die Klasse getCount(), welche die Anzahl der CounterModelListe zurück gibt (also wie viele Counter gespeichert sind).
     * @return counterModelList.size();
     */
    @Override
    public int getCount() {
        if (counterModelList != null) {
            return counterModelList.size();
        } else {
            return 0;
        }
    }

    /**
     * Die Methode getItem returned das gebrauchte Objekt aus der counterModelList.
     * @param i;
     * @return counterModelList.get(i + 1);
     */
    @Override
    public Object getItem(int i) {
        return counterModelList.get(i + 1);
    }

    /**
     * Die Methode getItemId returned die ItemId des Adapters.
     * @param i;
     * @return 0;
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Die Methode getView erzeugt die View der Counter in dem Grid Layout.
     * @param pos;
     * @param view;
     * @param parent;
     * @return binding.getRoot();
     */
    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        //Setzt in der Recyclerview das XML Layout item_counter.
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_counter, null);
            binding = DataBindingUtil.bind(view);
            view.setTag(binding);
        } else {
            binding = (ItemCounterBinding) view.getTag();
        }
        // Gibt dem MainActivityViewModel die counterModelList und welche Position mit welchem Counter gefüllt werden soll.
        CounterModel counterModel = counterModelList.get(pos);
        binding.setCounterModel(counterModel);
        MainActivityViewModel presenter = new MainActivityViewModel(parent.getContext(), constant);
        binding.setVariable(BR.mainActivityViewModel, presenter);
        //Zeigt Add Symbole wenn der Counter isAdd = true hat. (Wird für das letzte Element genutzt um neue Counter dazu zu fügen.
        if (counterModel.getIsAdd()){
            binding.ivAddNew.setVisibility(View.VISIBLE);
            binding.tvItem.setVisibility(View.GONE);
        }else {
            binding.ivAddNew.setVisibility(View.GONE);
            binding.tvItem.setVisibility(View.VISIBLE);
        }
        return binding.getRoot();
    }

    /**
     * Die Methode isEnabled ist eine Standartmethode von Android Adaptern und returned false.
     * @param position;
     * @return false;
     */
    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    /**
     * Die Methode refreshList refreshed die Liste der CounterModels wenn sich etwas am Datensatz changed (z.B. ein neuer Counter hinzugefügt wird).
     * @param refreshList;
     * @ensures notifyDataSetChanged();
     */
    public void refreshList(List<CounterModel> refreshList) {
        this.counterModelList = refreshList;
        notifyDataSetChanged();
    }
}