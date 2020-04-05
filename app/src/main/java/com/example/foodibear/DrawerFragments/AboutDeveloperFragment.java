package com.example.foodibear.DrawerFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodibear.Activities.MainActivity;
import com.example.foodibear.R;


public class AboutDeveloperFragment extends Fragment {





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        ((MainActivity) getActivity()).getBottomNav().setVisibility(View.GONE);
//        ((MainActivity) getActivity()).drawer.setVisibility(View.INVISIBLE);
            getActivity().setTitle(R.string.menu_about);
            //((MainActivity) getActivity()).drawer.closeDrawers();



        return inflater.inflate(R.layout.otp_verification, container, false);
    }
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {

            menu.findItem(R.id.checkout).setVisible(true);
            menu.findItem(R.id.action_settings).setVisible(false);
        }
        // inflater.inflate(R.menu.main,menu);



    }

}
