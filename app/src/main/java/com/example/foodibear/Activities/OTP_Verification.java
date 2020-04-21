package com.example.foodibear.Activities;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodibear.Constant.constant;
import com.example.foodibear.R;
import com.example.foodibear.Room_Database.Database;
import com.example.foodibear.Room_Database.UserOrder;
import com.example.foodibear.order_history_database.HistoryDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

public class OTP_Verification extends AppCompatActivity {
  private Button verifybutton;
  private ProgressBar verifying_bar;
  private EditText otpField;
  private Bundle bundle;
  private String MobileNumber;
  @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_verification);
        final int check=getResources().getInteger(R.integer.check);
        verifybutton=findViewById(R.id.verifyBtn);
        otpField=findViewById(R.id.otpCode_field);
        bundle=getIntent().getExtras();
         MobileNumber=bundle.getString("mobile_number");
         otpField.setText(MobileNumber);
        verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               verifying_bar.setVisibility(View.VISIBLE);
//                verifybutton.setText("Verifying......");
//               verifybutton.setEnabled(false);
//               otpField.setEnabled(false);
                CalculateBill();
                Toast.makeText(getApplicationContext(),"Total: "+constant.TOTAL_AMOUNT+" Items: "+constant.TOTAL_ITEMS,Toast.LENGTH_LONG).show();
            }
        });
//        verifying_bar=findViewById(R.id.verifying_bar);
//        verifying_bar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#072344"), PorterDuff.Mode.SRC_IN);
    }



    @Override
    public void onBackPressed() {
        Warning_Alert();


    }

    public void showProgress(){
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.loading_dialog,null);
        alertdialog.setView(view);
        final Dialog dialog=alertdialog.create();
        dialog.show();


    }

    private void Warning_Alert(){
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.warning_dialog,null);
        alertdialog.setView(view);
        final Dialog dialog=alertdialog.create();
        dialog.show();

        Button cancelBtn=view.findViewById(R.id.cancelbtn);
        Button oKBtn=view.findViewById(R.id.okbtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        oKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }


    private  void CalculateBill(){
      int amount=0;

      for(UserOrder order: constant.ORDER_LIST){
          //amount+=getInt(order.getProductQuantity())*Integer.parseInt(order.getProductPrice());
          amount++;
      }
      constant.TOTAL_AMOUNT=amount;
      constant.TOTAL_ITEMS=constant.ORDER_LIST.size();
    }

  private int getInt(String value){
      return Integer.parseInt(value);
  }
}
