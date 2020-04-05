package com.example.foodibear.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;
import com.example.foodibear.Room_Database.Database;
import com.example.foodibear.Room_Database.UserOrder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;

public class ProductDetailed extends AppCompatActivity implements Serializable {
   Toolbar toolbar;
   private Bundle extras;
    Intent intent;
   private TextView prdutName,prdctPrice,prdutDetailed;
   private ElegantNumberButton countrButton;
   private FloatingActionButton btnCart;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detailed);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prdutName=findViewById(R.id.product_name);
        prdctPrice=findViewById(R.id.product_price);
        prdutDetailed=findViewById(R.id.product_description);
        countrButton=findViewById(R.id.btn_counter);
        intent=getIntent();
        if(intent!=null){
            product = intent.getParcelableExtra("product");
        }

        btnCart=findViewById(R.id.btn_addcart);
        btnCart.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#ffffff")));
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserOrder order=new UserOrder();
                 order.setProductId(product.getProductId());
                 order.setProductName(product.getProductName());
                 order.setProductPrice(product.getProductPrice());
                 order.setProductQuantity(countrButton.getNumber());
                 order.setLayoutType(0);
                 MainActivity.cartDatabase.myDao().addOrder(order);

                 Toast.makeText(getApplicationContext(),"Add to cart....... "+countrButton.getNumber()+" "+product.getProductName(),Toast.LENGTH_SHORT).show();
            }
        });

          prdutName.setText(product.getProductName());
          prdctPrice.setText(product.getProductPrice());
          prdutDetailed.setText(product.getProductDetailed());
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                finish();
//            }
//        });
    }
}
