package com.example.foodibear.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import com.example.foodibear.R;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn_SignUp_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__sign_up__screen);
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
     //   ft.replace(R.id.fragment_place,new SignIn_fragment());
        ft.commit();

    }
    public void Logout(){
        FirebaseAuth.getInstance().signOut();

    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment_place);
//        fragment.onActivityResult(requestCode, resultCode, data);
//    }

}
