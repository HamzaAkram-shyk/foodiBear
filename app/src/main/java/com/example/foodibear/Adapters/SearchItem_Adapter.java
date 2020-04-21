package com.example.foodibear.Adapters;

import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodibear.Activities.ProductDetailed;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchItem_Adapter extends RecyclerView.Adapter<SearchItem_Adapter.SearchItem_ViewHolder> implements Filterable {


    private Context context;
    private List<Product> productList;
    private List<Product> filterList;
    private ValueFilter filter;


    public SearchItem_Adapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        filterList=productList;
    }

    public SearchItem_Adapter.SearchItem_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


      View view= LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);


        return new SearchItem_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchItem_Adapter.SearchItem_ViewHolder holder, final int position) {
       holder.searchItemText.setText(productList.get(position).getProductName());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context, ProductDetailed.class);
               intent.putExtra("product",productList.get(position));
               context.startActivity(intent);

           }
       });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public Filter getFilter() {
        if (filter==null){
            filter=new ValueFilter();
        }
        return filter;
    }



    public class SearchItem_ViewHolder extends RecyclerView.ViewHolder {
        private TextView searchItemText;
        public SearchItem_ViewHolder(@NonNull View itemView) {
            super(itemView);
            searchItemText=itemView.findViewById(R.id.searchItemName);
        }
    }


    private class ValueFilter extends Filter{


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            if(constraint!=null&&constraint.length()>0){
                List<Product> dummy=new ArrayList<>();
                for (int i=0;i<filterList.size();i++){
                    if(filterList.get(i).getProductName().toUpperCase().contains(constraint.toString().toUpperCase())){

                        dummy.add(filterList.get(i));

                    }
                }

                results.count=dummy.size();
                results.values=dummy;

            }

            else{
                // recyclerView.setVisibility(View.GONE);
                results.count=filterList.size();
                results.values=filterList;

            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //recyclerView.setVisibility(View.INVISIBLE);
            //list=filterList;
            productList=(ArrayList<Product>) results.values;
            notifyDataSetChanged();
        }
    }







}
