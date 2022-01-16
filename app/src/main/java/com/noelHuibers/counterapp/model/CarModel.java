package com.noelHuibers.counterapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class CarModel {


    int imgId;
    int number;
    boolean isAdd;

    public CarModel(int imgId, int number, boolean isAdd) {
        this.imgId = imgId;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }


    @BindingAdapter({ "imgId" })
    public static void loadImage(ImageView imageView, int imgId) {
        imageView.setImageResource(imgId);
       /* Glide.with(imageView.getContext())
                .setDefaultRequestOptions(new RequestOptions()
                        .circleCrop())
                .load(imageURL)
                .placeholder(R.drawable.loading)
                .into(imageView);*/
    }
}
