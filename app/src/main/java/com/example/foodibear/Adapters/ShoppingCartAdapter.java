package com.example.foodibear.Adapters;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;
import com.example.foodibear.Room_Database.UserOrder;
import com.example.foodibear.Util.AdapterType;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private List<UserOrder> orderList;
    Product product;
    private boolean layoutType;
    private static final int LAYOUT_SHOPPING_CART= 0;
    private static final int LAYOUT_SALE_ITEMS= 1;
    private boolean isHorizontalRecycle;

    public ShoppingCartAdapter(Context context, List<Product> productList,List<UserOrder> orderList,boolean layoutType,boolean isHorizontalRecycle) {
        this.context = context;
        this.productList=productList;
        this.orderList=orderList;
        this.layoutType=layoutType;
        this.isHorizontalRecycle=isHorizontalRecycle;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder viewHolder;
        if(viewType==LAYOUT_SHOPPING_CART)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_item,parent,false);
          viewHolder = new ViewHolder_0(view,parent.getContext());
          return viewHolder;
        }
        else
        {
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_items,parent,false);
            // Check Recycler view is horizontal or vertical
            if(isHorizontalRecycle){
                //LinearLayout.LayoutParams params= new LinearLayout.LayoutParams((ViewGroup.LayoutParams.MATCH_PARENT/4),ViewGroup.LayoutParams.WRAP_CONTENT);
               // params.width = (parent.getWidth()/4);
//                RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                lp.width=300;
//                lp.height=450;
//                lp.setMargins(5,0,5,0);
//                view.setLayoutParams(lp);
                viewHolder= new ViewHolder_1(view,parent.getContext());

                return viewHolder;
            } else {

                viewHolder= new ViewHolder_1(view,parent.getContext());

                return viewHolder;

            }








        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == LAYOUT_SHOPPING_CART) {
            // Typecast Viewholder
            // Set Viewholder properties
            // Add any click listener if any
            UserOrder order=orderList.get(position);
            final ViewHolder_0 vaultItemHolder = (ViewHolder_0) holder;
            vaultItemHolder.productImage.setImageResource(R.drawable.prsal);
            vaultItemHolder.productName.setText(orderList.get(position).getProductName());
            vaultItemHolder.productPrice.setText(orderList.get(position).getProductPrice());
            vaultItemHolder.productQuantity.setText(orderList.get(position).getProductQuantity());
            vaultItemHolder.elegantNumberButton.setNumber(vaultItemHolder.productQuantity.getText().toString());

          vaultItemHolder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
              @Override
              public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                  Toast.makeText(context," Old: "+oldValue+" New: "+newValue,Toast.LENGTH_LONG).show();
              }
          });



        } else {

            ViewHolder_1 vaultItemHolder = (ViewHolder_1) holder;

            vaultItemHolder.productSale.setText("50% off");





           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Toast.makeText(context,"Giant Sale",Toast.LENGTH_SHORT).show();
               }
           });


        }


    }
    @Override
    public int getItemCount() {
        if(layoutType==AdapterType.SHOPPING_CART){
            return orderList.size();
        } else {
            return productList.size();
        }

    }

    @Override
    public int getItemViewType(int position) {
      if(layoutType== AdapterType.SHOPPING_CART){
         return LAYOUT_SHOPPING_CART;
      }else {
          return LAYOUT_SALE_ITEMS;
      }
    }

    class ViewHolder_0 extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView productImage;
    private TextView productPrice;
    private TextView productName;
    private TextView productQuantity;
    private ImageButton deleteBtn;
    private ElegantNumberButton elegantNumberButton;


    public ViewHolder_0(@NonNull View itemView, Context cnt) {
        super(itemView);
        context=cnt;
        productImage=itemView.findViewById(R.id.img_product);
        productPrice=itemView.findViewById(R.id.text_productPrice);
        productName=itemView.findViewById(R.id.text_productname);
        deleteBtn=itemView.findViewById(R.id.btn_delete);
        elegantNumberButton=itemView.findViewById(R.id.btn_Quntity);
        productQuantity=itemView.findViewById(R.id.txt_showQuantity);

    }


    @Override
    public void onClick(View v) {

    }
}




    class ViewHolder_1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage;
        private TextView productSale;
        private TextView productName;



        public ViewHolder_1(@NonNull View itemView, Context cnt) {
            super(itemView);
            context=cnt;
            productImage=itemView.findViewById(R.id.img_product);
            productSale =itemView.findViewById(R.id.sale_tag);
            productName=itemView.findViewById(R.id.text_productname);
            productName.setGravity(0);



        }


        @Override
        public void onClick(View v) {
            Toast.makeText(context,"Giant Sale",Toast.LENGTH_LONG).show();
        }
    }
}
