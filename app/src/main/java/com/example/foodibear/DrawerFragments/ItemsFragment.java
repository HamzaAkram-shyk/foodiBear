package com.example.foodibear.DrawerFragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.droidsonroids.gif.GifImageView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodibear.Activities.MainActivity;
import com.example.foodibear.Activities.ProductDetailed;
import com.example.foodibear.Adapters.Animation;
import com.example.foodibear.Adapters.ShoppingCartAdapter;
import com.example.foodibear.Adapters.adapterParameter;
import com.example.foodibear.Adapters.gridItems_Adapter;
import com.example.foodibear.Fragments.UserCartFragment;
import com.example.foodibear.Logic.SearchEngine;
import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Item;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;
import com.example.foodibear.Room_Database.UserOrder;
import com.example.foodibear.Util.AdapterType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.navigation.fragment.NavHostFragment.findNavController;


public class ItemsFragment extends Fragment {
     private FirebaseDatabase database;
    private gridItems_Adapter adapter;
    private static List<Category> categoryList;
    RecyclerView recyclerView;
    RecyclerView horizontalRecycleView;
    GifImageView loadingImage;
    Handler handler;
    Runnable runnable;
    Timer timer;
    DatabaseReference categoryTable;
    private ProgressBar loadingbar;
    private adapterParameter parameter;
    List<Integer> image;
    List<Product> productList;
    private Animation animation;
    private RecyclerView searchingRecyclerview;
    private SearchView searchView;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_items, container, false);
        recyclerView=mainView.findViewById(R.id.recycle);
        horizontalRecycleView=mainView.findViewById(R.id.horizontal_recyclerView);
       // setHasOptionsMenu(true);
        productList=new ArrayList<>();
        image= new ArrayList<>();
        Product product=new Product();
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);
        productList.add(product);

        if(getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getBottomNav().setVisibility(View.VISIBLE);
        }
//        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
//        //navController.navigate(R.id.bottom_nav_cart,null);


        recyclerView.setLayoutManager(new  GridLayoutManager(getContext(),2));
        horizontalRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

       // database=FirebaseDatabase.getInstance();
        loadingImage=mainView.findViewById(R.id.loadingImageview);
        categoryTable= FirebaseDatabase.getInstance().getReference("Category");
        animation=new Animation(categoryTable);

        categoryList=animation.getCateoriesWithAnimation(loadingImage);
        adapter=new gridItems_Adapter(getContext(), categoryList,productList,true);
        recyclerView.setAdapter(adapter);


        List<UserOrder> orders = MainActivity.cartDatabase.myDao().getOrders();
//        //loadingbar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
//
//        ShoppingCartAdapter shoppingCartAdapter=new ShoppingCartAdapter(getContext(),productList,image,1,false);
//        //horizontalRecycleView.setAdapter(shoppingCartAdapter);
//           recyclerView.setAdapter(shoppingCartAdapter);
          ShoppingCartAdapter horizontal=new ShoppingCartAdapter(getContext(),productList,orders, AdapterType.Sale_products,true);
           horizontalRecycleView.setAdapter(horizontal);

//        LinearLayout linear = (LinearLayout) findViewById(R.id.ll);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        linear.setLayoutParams(params);
//        As you can see, you can set Integer values for the LinearLayout.LayoutParams() constructor, like this:
//
//        LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(0, 100);


      //  fetchingCategories();
        searchingRecyclerview=mainView.findViewById(R.id.searchItemRecyclerview);
        searchView=mainView.findViewById(R.id.search_bar);
        searchingRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        SearchEngine searchEngine=new SearchEngine(getContext());
        searchEngine.setRecyclerview(searchingRecyclerview);
        searchEngine.setSearchView(searchView);
        searchEngine.displayItems();


        return mainView;

    }


    private void fetchingCategories(){
        loadingImage.setVisibility(View.VISIBLE);
        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                loadingImage.setVisibility(View.GONE);
                timer.cancel();



            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                categoryTable.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                            Category category =snapshot.getValue(Category.class);
                            categoryList.add(category);
                        }
                        adapter=new gridItems_Adapter(getContext(), categoryList,productList,true);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                handler.postDelayed(runnable,1000);
            }
        },3000,2000);

    }



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {

            menu.findItem(R.id.checkout).setVisible(false);
            menu.findItem(R.id.action_settings).setVisible(true);
        }
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putInt("Index",10);
                navController.navigate(R.id.bottom_nav_cart,bundle);
                Toast.makeText(getContext(),"Going To Card",Toast.LENGTH_LONG).show();
                break;
            case R.id.checkout:

                // Do Fragment menu item stuff here
                break;

        }

        return  super.onOptionsItemSelected(item);
    }



}


