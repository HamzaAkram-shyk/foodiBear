package com.example.foodibear.Adapters;

import android.os.Handler;
import android.view.View;

import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import pl.droidsonroids.gif.GifImageView;

public class Animation {
   List<Product> productList;
   List<Category> categoryList;
    Handler handler;
    Runnable runnable;
    Timer timer;
    DatabaseReference reference;
    public Animation( DatabaseReference reference) {

        this.reference=reference;
    }
                                                                               // if Id is 0 that means we want all products
    public List<Product> getProductsWhileAnimation(final GifImageView imageView,final String Id){
     productList=new ArrayList<>();
        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                timer.cancel();
                imageView.setVisibility(View.GONE);

            }

        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                            Product product=snapshot.getValue(Product.class);
                            if(!Id.equals("0")){
                                if(product.getCategoryId().equals(Id)){
                                    productList.add(product);
                                }

                            }else{
                                productList.add(product);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        productList=null;
                    }
                });
                handler.postDelayed(runnable,1000);
            }
        },2000,2000);


        return  productList;
    }


    public List<Category> getCateoriesWithAnimation(final GifImageView imageView){
        categoryList=new ArrayList<>();
        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                timer.cancel();
                imageView.setVisibility(View.GONE);

            }

        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                            Category category=snapshot.getValue(Category.class);
                            categoryList.add(category);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                handler.postDelayed(runnable,1000);
            }
        },2000,1000);


        return  categoryList;
    }



}
