package com.example.foodibear.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Product implements Parcelable {

   private  String productId,productName,productImage,productDetailed,
    productPrice,productDiscount,categoryId;
   // private transient String middleName;

    DatabaseReference productTable;
    private  List<Product> productList;

    public Product() {
   productList=new ArrayList<>();
   productTable= FirebaseDatabase.getInstance().getReference("Product");
    }

    public Product(String productId, String productName, String productImage, String productDetailed, String productPrice, String productDiscount,String categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productDetailed = productDetailed;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.categoryId=categoryId;
    }

    protected Product(Parcel in) {
        productList = in.createTypedArrayList(Product.CREATOR);
        productId=in.readString();
        productName=in.readString();
        productImage=in.readString();
        productDetailed=in.readString();
        productPrice=in.readString();
        productDiscount=in.readString();
        categoryId=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(productList);
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productImage);
        dest.writeString(productDetailed);
        dest.writeString(productPrice);
        dest.writeString(productDiscount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDetailed() {
        return productDetailed;
    }

    public void setProductDetailed(String productDetailed) {
        this.productDetailed = productDetailed;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Product> getSelectedProuctList(final String categoryId){
        productTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Product product =snapshot.getValue(Product.class);
                    if(product.getCategoryId().equals(categoryId)){
                        productList.add(product);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return productList;

    }
    public List<Product> getProuctList(){
        DatabaseReference productTable= FirebaseDatabase.getInstance().getReference("Product");
        final List<Product> productList=new ArrayList<>();
        productTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Product product =snapshot.getValue(Product.class);

                    productList.add(product);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return productList;

    }



}
