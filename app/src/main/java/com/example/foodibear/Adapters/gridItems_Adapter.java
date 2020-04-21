package com.example.foodibear.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.foodibear.Activities.ProductDetailed;
import com.example.foodibear.Activities.ProductsActivity;
import com.example.foodibear.Model.Category;
import com.example.foodibear.Model.Product;
import com.example.foodibear.R;
import com.facebook.appevents.codeless.internal.Constants;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import pl.droidsonroids.gif.GifImageView;

public class gridItems_Adapter extends RecyclerView.Adapter<gridItems_Adapter.ViewHolder> {


    private Context context;
    private List<Category> category;
    private List<Product> product;
    private boolean isCategory;


                               // isClickable  when the user select item firsttime in the recycerlview It,s click funtion works and then it will off because Loading will Started and due to loading user can,t select other item.
    public gridItems_Adapter(Context context, List<Category> category, List<Product> product, boolean isCategory) {
        this.context=context;
        this.category=category;
        this.product=product;
        this.isCategory=isCategory;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(isCategory ?R.layout.items_grid_list:R.layout.product_item,viewGroup,false);



//        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
//         lp.height = viewGroup.getWidth()/3;
//         view.setLayoutParams(lp);
//         view.setLayoutParams(lp);

        return new ViewHolder(view,context);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        if(isCategory){
            viewHolder.productImage.setImageResource(R.drawable.breakfast_image);
            viewHolder.productDescrip.setText(category.get(position).getCategoryName());
//            Bitmap mbitmap=((BitmapDrawable) context.getResources().getDrawable(R.drawable.breakfast_image)).getBitmap();
//            Bitmap imageRounded=Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
//            Canvas canvas=new Canvas(imageRounded);
//            Paint mpaint=new Paint();
//            mpaint.setAntiAlias(true);
//            mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//            canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 10, 10, mpaint); // Round Image Corner 100 100 100 100
//            viewHolder.productImage.setImageBitmap(imageRounded);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        //Toast.makeText(context,category.get(position).getCategoryId(),Toast.LENGTH_SHORT).show();
                        //isClickable=false;

                        String categoryId=category.get(position).getCategoryId();
                        Intent intent=new Intent(context,ProductsActivity.class);
                        intent.putExtra("categoryId",categoryId);
                        context.startActivity(intent);





                }
            });
        } else {
//             viewHolder.productImage.setClipToOutline(true);
            viewHolder.productImage.setImageResource(R.drawable.burger);
            viewHolder.productDescrip.setText(product.get(position).getProductName());
            viewHolder.priceTag.setVisibility(View.VISIBLE);


            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product item=product.get(position);
                     Intent productDetailedIntent=new Intent(context, ProductDetailed.class);
                     productDetailedIntent.putExtra("product",item);
                     context.startActivity(productDetailedIntent);
                }
            });

        }



    }

    @Override
    public int getItemCount() {
        if(isCategory){
            return category.size();
        }else{
            return product.size();
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productImage;
        private TextView productDescrip;
        private TextView priceTag;




        public ViewHolder(@NonNull View itemView,Context cnt) {
            super(itemView);
            context=cnt;
            productImage=itemView.findViewById(R.id.product_imageview);
            productDescrip=itemView.findViewById(R.id.productDescrip_textview);
            priceTag=itemView.findViewById(R.id.pricetag);

        }

        @Override
        public void onClick(View v) {

        }
    }





}
