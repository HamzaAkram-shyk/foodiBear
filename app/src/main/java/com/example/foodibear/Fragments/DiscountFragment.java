package com.example.foodibear.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodibear.Activities.MainActivity;
import com.example.foodibear.R;


public class DiscountFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getBottomNav().setVisibility(View.GONE);


        }

        return inflater.inflate(R.layout.fragment_discount, container, false);
    }


}
