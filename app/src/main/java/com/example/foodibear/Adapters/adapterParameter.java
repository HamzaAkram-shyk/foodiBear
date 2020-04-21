package com.example.foodibear.Adapters;

import android.widget.ProgressBar;

import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class adapterParameter {
    private ProgressBar progressBar;
    private List<Category> categoryList;
    private List<Product> productList;
    private boolean isCategory,isClickable;
    public static final String SET_PRODUCT_IN_ADAPTER="P";
    public static final String SET_CATEGORY_IN_ADAPTER="C";
    public static final String SET_PRODUCT_IN_ADAPTER_WITH_DISCOUNT="PS";


    public adapterParameter(){
        categoryList=new ArrayList<>();
        productList=new ArrayList<>();
        this.isCategory=true;
        this.isClickable=true;

    }

    private void setLogicForAdapter(boolean isCategory,boolean isClickable){
        this.isCategory=isCategory;
        this.isClickable=isClickable;
    }

   public void setAdapterType(String Type){
        if(Type.equals(SET_CATEGORY_IN_ADAPTER)){

            setLogicForAdapter(true,true);
        }else if(Type.equals(SET_PRODUCT_IN_ADAPTER)){
            setLogicForAdapter(false,true);
        } else if(Type.equals(SET_PRODUCT_IN_ADAPTER_WITH_DISCOUNT)){
            setLogicForAdapter(false,false);

        }

   }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public boolean getIsCategory() {
        return isCategory;
    }

    public boolean getIsClickable() {
        return isClickable;
    }
}
