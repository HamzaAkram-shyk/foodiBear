package com.example.foodibear.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodibear.Activities.MainActivity;
import com.example.foodibear.Activities.OTP_Verification;
import com.example.foodibear.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.NavigationUI;

public class CheckoutFragment extends Fragment {

    private EditText Mobnumber,Address;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.checkout_fragments,container,false);
        ((MainActivity)getActivity()).drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        Mobnumber=rootview.findViewById(R.id.Mobnum_field);
        Address=rootview.findViewById(R.id.addr_field);
       // ((DrawerLocker)getActivity()).setDrawerLocked(true);

        return rootview;
    }







    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {

            menu.findItem(R.id.checkout).setVisible(true);
            menu.findItem(R.id.action_settings).setVisible(false);
        }
        // inflater.inflate(R.menu.main,menu);



    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        switch (id) {
            case R.id.checkout:
                int size=Mobnumber.getText().length();
              if(size>9&&Address.getText().length()>10){
                 Intent intent=new Intent(getContext(), OTP_Verification.class);
                 intent.putExtra("mobile_number",Mobnumber.getText().toString().trim());
                  getContext().startActivity(intent);
                  getActivity().finish();
              } else{
                  Toast.makeText(getContext(),"Fill correct informtaion "+size,Toast.LENGTH_SHORT).show();
              }

                break;

            case R.id.action_settings:

                break;

        }
        return super.onOptionsItemSelected(item);
    }


  public interface DrawerLocker{
        public void setDrawerLocked(boolean enabled);
    }

}
