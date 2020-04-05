package com.example.foodibear.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.droidsonroids.gif.GifImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.foodibear.Adapters.Animation;
import com.example.foodibear.Adapters.SearchItem_Adapter;
import com.example.foodibear.Adapters.gridItems_Adapter;
import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity  implements Serializable {
   private Toolbar toolbar;
   private String Title="Items";
   private RecyclerView recyclerView;
   private static  List<Product> productList;
   private List<Category> categoryList;
   private ProgressBar progressBar;// because adapter needs progress bar that,s why here we have to pass progressbar.
    private  Bundle extras;
    private String categoryId;
    private gridItems_Adapter adapter;
    DatabaseReference productTable;
    GifImageView gifImageView;
    private Menu mMenu;
    private RecyclerView searchingRecyclerview;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recycler);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        productTable= FirebaseDatabase.getInstance().getReference("Product");
        gifImageView=findViewById(R.id.bear_animation);
        extras=getIntent().getExtras();
        categoryId = extras.getString("categoryId");
        Animation animation=new Animation(productTable);
        productList=new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


//        productTable.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    Product product =snapshot.getValue(Product.class);
//                    if(product.getCategoryId().equals(categoryId)){
//                        productList.add(product);
//                    }
//
//                }
//
//                adapter = new gridItems_Adapter(getApplicationContext(), categoryList,productList,progressBar,false,true);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        searchingRecyclerview=findViewById(R.id.searchItemRecyclerview);
        searchView=findViewById(R.id.search_bar);
        searchingRecyclerview.setLayoutManager(new LinearLayoutManager(this));


        productList=animation.getProductsWhileAnimation(gifImageView,categoryId);
        adapter = new gridItems_Adapter(ProductsActivity.this, categoryList,productList,false);
        recyclerView.setAdapter(adapter);
        final SearchItem_Adapter searchAdapter=new SearchItem_Adapter(this,productList);
        searchView.setActivated(false);
        searchView.setQueryHint("Product Name");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setIconifiedByDefault(false);



        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override

            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String NewText) {
                //recyclerView.setVisibility(View.VISIBLE);
                if(NewText.equals("")){
                    searchingRecyclerview.setVisibility(View.GONE);
                } else {
                    searchAdapter.getFilter().filter(NewText);
                    searchingRecyclerview.setAdapter(searchAdapter);
                    searchingRecyclerview.setVisibility(View.VISIBLE);
                }

                // recyclerView.setAdapter(adapter);
                return false;
            }
        });














    }



//    private void getProucts(){
//        FirebaseDatabase database=FirebaseDatabase.getInstance();
//        DatabaseReference productTable=database.getReference("Product");
//        productTable.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    Product product=snapshot.getValue(Product.class);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//    }





}
