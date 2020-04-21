package com.example.foodibear.Logic;

import android.content.Context;
import android.view.View;

import com.example.foodibear.Adapters.SearchItem_Adapter;
import com.example.foodibear.Model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

public class SearchEngine {
    private DatabaseReference reference;
    private List<Product> productList;
    private RecyclerView searchingRecyclerview;
    private SearchView searchView;
    private Context mcontext;
    private SearchItem_Adapter searchAdapter;

    public SearchEngine(Context mcontext) {
        this.reference = FirebaseDatabase.getInstance().getReference("Product");
        this.mcontext = mcontext;
        loadItems();
    }


   private void loadItems(){
        productList=new ArrayList<>();
//       reference.addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               for (DataSnapshot snapshot:dataSnapshot.getChildren()){
//                   Product product=snapshot.getValue(Product.class);
//
//                           productList.add(product);
//               }
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//               productList=null;
//           }
//       });
       Product product=new Product();
       product.setProductName("Pizza");
       product.setProductId("5");
       product.setProductPrice("200");
       product.setProductDetailed("Its Chicken Pizza with Chezi");
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       productList.add(product);
       searchAdapter=new SearchItem_Adapter(mcontext,productList);

   }

    public RecyclerView geRecyclerview() {
        return searchingRecyclerview;
    }

    public void setRecyclerview(RecyclerView searchingRecyclerview) {
        this.searchingRecyclerview = searchingRecyclerview;
    }

    public SearchView getSearchView() {
        return searchView;
    }

    public void setSearchView(SearchView search) {
        searchView = search;
        searchView.setActivated(false);
        searchView.setQueryHint("Product Name");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setIconifiedByDefault(false);
    }
    public void displayItems(){
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
}










