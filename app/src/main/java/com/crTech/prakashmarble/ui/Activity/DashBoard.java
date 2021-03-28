package com.crTech.prakashmarble.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.crTech.prakashmarble.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DashBoard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
////        if (id == R.id.nav_profile) {
////
////            int status = prefs.getInt("Pref_Status", 0);
////
////            Intent profileIntent = new Intent(AffiliateActivity.this, EditProfileActivity.class);
////            profileIntent.putExtra("ProfileStatus", status);
////            startActivity(profileIntent);
////
////        }
////        else if (id == R.id.nav_logout) {
////            logout();
////        }
////
////
////        else if (id == R.id.nav_home) {
////            /*Intent rechargeIntent = new Intent(AffiliateActivity.this, OffersActivity.class);
////            startActivity(rechargeIntent);
////            */
////            Intent intent = new Intent();
////            setResult(RESULT_OK, intent);
////            finish();
////
////
////
////        }
////
////
////
////
////        else if (id == R.id.nav_recharge) {
////            Intent rechargeIntent = new Intent(AffiliateActivity.this, EocWalletRechargeActivity.class);
////            startActivity(rechargeIntent);
////        }
////        else if (id == R.id.nav_help) {
////
////            callAction();
////
////        }
////        else if (id == R.id.nav_reward) {
////            pointDetail();
////        }
////
////        else if (id == R.id.nav_share) {
////            shareApp();
////        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}