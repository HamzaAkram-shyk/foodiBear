package com.example.foodibear.Activities;

import android.os.Bundle;

import com.example.foodibear.Fragments.CheckoutFragment;
import com.example.foodibear.R;
import com.example.foodibear.Room_Database.Database;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.Menu;
import android.widget.Toast;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

public class MainActivity extends AppCompatActivity implements CheckoutFragment.DrawerLocker  {

   public   AppBarConfiguration mAppBarConfiguration;
    public NavigationView navigationView;
    public static  DrawerLayout drawer;
   public NavController navController;
     BottomNavigationView bottomNavigationView;
    public static Database cartDatabase;
    private  Menu mMenu;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cartDatabase= Room.databaseBuilder(getApplicationContext(),Database.class,"Orders").allowMainThreadQueries().build();
         drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        bottomNavigationView=findViewById(R.id.bottom_NavigationBar);
        bottomNavigationView.setVisibility(View.VISIBLE);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                new int[]{R.id.nav_Items, R.id.nav_drinks, R.id.nav_userOrders,
                        R.id.nav_userAccount, R.id.nav_aboutDeveloper, R.id.nav_send,
                        R.id.bottom_nav_Discount,
                        R.id.bottom_nav_cart,R.id.bottom_nav_userlocation,
                R.id.Checkout_fragment
                }
                )
                .setDrawerLayout(drawer)
                .build();

        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,Gravity.LEFT);
         navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

       // navController.navigate(R.id.bottom_nav_cart,null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.mMenu=menu;
        //menu.findItem(R.id.checkout).setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            // do action
            return false;
        } else if (item.getItemId() == R.id.checkout) {
            // do action
            return false;
        }

        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();

    }


    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }


    }

   public void OFF(){

   }

    public BottomNavigationView getBottomNav() {
        return bottomNavigationView ;
    }

    @Override
    public void setDrawerLocked(boolean enabled) {
        if(enabled){
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        }else{
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}
