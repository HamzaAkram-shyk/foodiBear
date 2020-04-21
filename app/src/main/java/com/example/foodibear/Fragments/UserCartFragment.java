package com.example.foodibear.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.foodibear.Activities.MainActivity;
import com.example.foodibear.Activities.ProductDetailed;
import com.example.foodibear.Adapters.OrderShipping_Adapter;
import com.example.foodibear.Adapters.ShoppingCartAdapter;
import com.example.foodibear.Adapters.gridItems_Adapter;
import com.example.foodibear.Constant.constant;
import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;
import com.example.foodibear.Room_Database.UserOrder;
import com.example.foodibear.Util.AdapterType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserCartFragment extends Fragment {


    private FirebaseDatabase database;
    private ShoppingCartAdapter adapter;
    private  List<Product> productList;
    RecyclerView recyclerView;
    DatabaseReference Table;
    private List<UserOrder> orderList;
    private OrderShipping_Adapter shippingAdapter;
    private  int Index;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mainView  = inflater.inflate(R.layout.fragment_user_cart, container, false);
        recyclerView=mainView.findViewById(R.id.recycle);
           getActivity().setTitle(" ");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        database=FirebaseDatabase.getInstance();
        productList=new ArrayList<>();
        setIndex(getArguments().getInt("Index"));


        // Remove Bottom Navigation

        ((MainActivity) getActivity()).getBottomNav().setVisibility(View.GONE);

        //
        orderList= MainActivity.cartDatabase.myDao().getOrders();


        shippingAdapter=new OrderShipping_Adapter(getContext(),orderList);
        recyclerView.setAdapter(shippingAdapter);
//        Table= FirebaseDatabase.getInstance().getReference("Product");
//
//        Table.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    Product product =snapshot.getValue(Product.class);
//                    productList.add(product);
//                }
////                productList.add(new Product("02","IceCream","default","hhd","340","drf","fefhue"));
////                productList.add(new Product("02","Drinks","default","hhd","50","drf","fefhue"));
//                adapter=new ShoppingCartAdapter(getContext(),productList,orderList, AdapterType.SHOPPING_CART,true);
//
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (dy > 0 && bottomNavigationView.isShown()) {
//                    bottomNavigationView.setVisibility(View.GONE);
//                } else if (dy < 0 ) {
//                    bottomNavigationView.setVisibility(View.VISIBLE);
//
//                }
//            }
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });






 return mainView;
}
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {

            menu.findItem(R.id.checkout).setVisible(true);
            menu.findItem(R.id.action_settings).setVisible(false);
        }
       // inflater.inflate(R.menu.main,menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        switch (id) {
            case R.id.checkout:
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.Checkout_fragment);
                constant.ORDER_LIST=orderList;
                break;

            case R.id.action_settings:

                break;

        }
       return super.onOptionsItemSelected(item);
    }


    private void Checkout(){
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(getContext());
        View view=getLayoutInflater().inflate(R.layout.shipping_details,null);
        alertdialog.setView(view);
        final Dialog dialog=alertdialog.create();
        dialog.show();

//        Button Btn_confirmOrder=view.findViewById(R.id.btn_OrderConfirm);
//
//        Btn_confirmOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
    }

    public void setIndex(int Index){
        this.Index=Index;
    }
}
