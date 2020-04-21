package com.example.foodibear.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import com.example.foodibear.R;
import com.example.foodibear.Room_Database.UserOrder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderShipping_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<UserOrder> orderlist;
    private static final int CART_ITEM_LAYOUT=0;
    private static final int PRICE_DETAILS_LAYOUT=1;
    UserOrder order=new UserOrder();


    public OrderShipping_Adapter(Context context, List<UserOrder> orderlist) {
        this.context = context;
        this.orderlist = orderlist;
        // because last layout must be Price Detailed Layout
        order.setLayoutType(1);
        this.orderlist.add(order);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType){
            case CART_ITEM_LAYOUT:
                View CartItem_View= LayoutInflater.from(context).inflate(R.layout.shoppingcart_item,parent,false);
                return new CartItem(CartItem_View);
            case PRICE_DETAILS_LAYOUT:
                View PriceDetail_View=LayoutInflater.from(context).inflate(R.layout.order_total_amount,parent,false);

               return new PriceDetail(PriceDetail_View);
          default:
              return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          switch (orderlist.get(position).getLayoutType()){
              case CART_ITEM_LAYOUT:
                  //orderlist.get(position).setProductQuantity("2");
                  final UserOrder order=orderlist.get(position);
                  final CartItem cartReference=(CartItem) holder;
                  cartReference.productQuantity.setText(order.getProductQuantity());
                  cartReference.productName.setText(order.getProductName());
                  cartReference.productPrice.setText(order.getProductPrice());
                  cartReference.productImage.setImageResource(R.drawable.prsal);
                  cartReference.elegantNumberButton.setNumber(order.getProductQuantity());
                  cartReference.elegantNumberButton.setOnValueChangeListener(
                          new ElegantNumberButton.OnValueChangeListener() {
                      @Override
                      public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {

                          order.setProductQuantity(String.valueOf(newValue));
                          cartReference.productQuantity.setText(order.getProductQuantity());

                      }
                  });
 break;


              case PRICE_DETAILS_LAYOUT:


                  ((PriceDetail)holder).deliveryCharges.setText("Free");
                  ((PriceDetail)holder).totalItems.setText((orderlist.size()-1)+"");
                  ((PriceDetail)holder).totalAmount.setText("$ 94837483");
                  break;
          }
    }

    @Override
    public int getItemCount() {

        return orderlist.size();
    }

    @Override
    public int getItemViewType(int position) {
       switch (orderlist.get(position).getLayoutType()){
           case 0:
               return CART_ITEM_LAYOUT;

           case 1:
               return PRICE_DETAILS_LAYOUT;
               default:
                   return -1;

       }
    }




    class PriceDetail extends RecyclerView.ViewHolder{
       private TextView totalAmount,totalItems,deliveryCharges;
        public PriceDetail(@NonNull View itemView) {
            super(itemView);
            totalAmount=itemView.findViewById(R.id.totalamountNo);
            totalItems=itemView.findViewById(R.id.totalItems);
            deliveryCharges=itemView.findViewById(R.id.deliveryCharges_Price);
        }
    }

//=================================================================================================================
    class CartItem extends RecyclerView.ViewHolder{

    private ImageView productImage;
    private TextView productPrice;
    private TextView productName;
    private TextView productQuantity;
    private ImageButton deleteBtn,increment,decrement;
    private ElegantNumberButton elegantNumberButton;
   private  TextView quantitylabel;

        public CartItem(@NonNull View itemView) {
            super(itemView);
            productImage=itemView.findViewById(R.id.img_product);
            productPrice=itemView.findViewById(R.id.text_productPrice);
            productName=itemView.findViewById(R.id.text_productname);
            deleteBtn=itemView.findViewById(R.id.btn_delete);
            elegantNumberButton=itemView.findViewById(R.id.btn_Quntity);
            productQuantity=itemView.findViewById(R.id.txt_showQuantity);
            quantitylabel=itemView.findViewById(R.id.quntity_label);
            increment=itemView.findViewById(R.id.plusbtn);
            decrement=itemView.findViewById(R.id.minusbtn);
        }
    }


}
