package com.crTech.prakashmarble.ui.Activity.ui.User;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.CartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.CartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductDetailFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.home.HomeFragment;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.infideap.drawerbehavior.Advance3DDrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    RelativeLayout rv_password,rv_logout,rv_profile,rv_home,rv_feav,rv_location,rv_wishlist,rv_booking,rv_support;
    DrawerLayout mDrawerLayout;
    RelativeLayout rlSideList;
    Context context = DashBoard.this;
    Preferences pref;
    RecyclerView rv_notification;
    FloatingActionButton fab_conatact;
    Handler mHandler;
    SweetAlertDialog loader;
    RelativeLayout lv_linear;
    TextView tv_username,tv_headder,tv_count;
    Snackbar snackbar;
    Toolbar toolbar;
    ImageView iv_location,iv_search,iv_notify,iv_clear;
    Geocoder geocoder;
    String userID;
    List<Address> addresses;
    NavigationView navigationView;
    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> search_items = new ArrayList<>();
    ArrayList<String> lCategories = new ArrayList<>();
    ArrayList<HashMap<String,String>> test_search = new ArrayList<HashMap<String,String>>();
    ArrayList<HashMap<String,String>> search_data = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String,String>> notification_list = new ArrayList<HashMap<String, String>>();
    private int mMenuState = 0;
    private SweetAlertDialog pDialog;
    private Advance3DDrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getid();
        setlistner();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle  = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        drawer.setViewScale(Gravity.START, 0.96f);
        drawer.setRadius(Gravity.START, 20);
        drawer.setViewElevation(Gravity.START, 8);
        drawer.setViewRotation(Gravity.START, 15);

        loadFragment(new HomeFragment());

        search_data.clear();
        search_items.clear();
        //================ Search Datas ==================================//

        String str_date="13-09-2011";
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = (Date)formatter.parse(str_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String islogin = pref.get(Constants.islogin);
        if (islogin=="1"){
            getcartdata();
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getid() {
        mHandler = new Handler();
        fab_conatact = (FloatingActionButton)findViewById(R.id.fab);
        pref = new Preferences(DashBoard.this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rv_home = (RelativeLayout)findViewById(R.id.rl_home);
        drawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        tv_count = (TextView)findViewById(R.id.count);
        tv_count.setText("0");
    }
    private void setlistner() {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.rl_home:
                finish();
                Intent intent = new Intent(getApplicationContext(),DashBoardActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_password:
                Intent intent1  = new Intent(getApplicationContext(), ChangePassword_Activity.class);
                startActivity(intent1);
                break;
            case R.id.rl_logout:
                pref.set(Constants.login_status,"0");
                pref.commit();
                finish();
                Intent intent2 = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_profile:
                finish();
                Intent intent3 = new Intent(getApplicationContext(), Update_ProfileActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_location:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.END);
                loadFragment(new Location_Fragment());
                break;
            case R.id.rl_feeds:
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.END);
                Intent intent4 = new Intent(getApplicationContext(), Contact_Feedback_Activity.class);
                startActivity(intent4);
                break;
            //============ booking list ==================//
            case R.id.rl_booking:drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.END);
                Intent intent5 = new Intent(getApplicationContext(), User_Booking_List.class);
                startActivity(intent5);
                break;
            //=========== wish list ==========================//
            case R.id.rl_whishlist:
                drawer.closeDrawer(GravityCompat.END);
                Intent intent6 = new Intent(getApplicationContext(), User_Wishlist.class);
                startActivity(intent6);
                break;
            case R.id.search:
                iv_search.setVisibility(View.GONE);
                drawer.closeDrawer(Gravity.START);
                drawer.closeDrawer(Gravity.END);
                loadFragment(new Search_Fragment());
                break;
            case R.id.clear:
                tv_headder.setVisibility(View.VISIBLE);

                iv_notify.setVisibility(View.VISIBLE);
                iv_search.setVisibility(View.VISIBLE);
                iv_clear.setVisibility(View.GONE);
                autoCompleteTextView.setVisibility(View.GONE);
                autoCompleteTextView.setHint("eg: jayanagar");
                break;
            case R.id.notification:
                drawer.openDrawer(Gravity.END);
                break;

            case R.id.rl_favourite:
                tv_headder.setVisibility(View.VISIBLE);
                iv_notify.setVisibility(View.VISIBLE);
                iv_clear.setVisibility(View.GONE);
                autoCompleteTextView.setVisibility(View.GONE);
                tv_headder.setText("My Favorites");
                iv_search.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                drawer.closeDrawer(GravityCompat.END);
                loadFragment(new Fevourites_Fragment());
                break;*/
        }
    }

    public void displayView(int position){
        switch (position){
            case 0:
                navigationView.setVisibility(View.VISIBLE);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                fab_conatact.setVisibility(View.VISIBLE);
                finish();
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                break;
            case 1:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 2:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 3:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 4:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 5:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 6:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new ProductFragment());
                break;
            case 7:
                navigationView.setVisibility(View.GONE);
               
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new ProductDetailFragment());
                break;

        }
    }
//======= get cart info details and check user logged in  ===========//
    public void getcartinfo(DetailsDataModel detailsDataModel,int i) {
        String userlog = pref.get(Constants.islogin);
        try {
            if (userlog.equals("1")){
                addtocard(detailsDataModel,i);
            }else {
                getalertbox();
            }
        }catch (Exception e){
            getalertbox();
        }
    }
//========= adding product for cart ========//
    public void addtocard(DetailsDataModel detailsDataModel,int i) {

        CartInputModel cartInputModel = new CartInputModel();
        cartInputModel.setProdID(Integer.parseInt(detailsDataModel.getProdID()));
        cartInputModel.setQuantity(String.valueOf(i));
        cartInputModel.setUserID(Integer.parseInt(userID));
        Call<CartResponseModel> cartResponseModelCall = ApiClient.getClient().create(ApiInterface.class).additem(cartInputModel);
        cartResponseModelCall.enqueue(new Callback<CartResponseModel>() {
            @Override
            public void onResponse(Call<CartResponseModel> call, Response<CartResponseModel> response) {
                if (response.isSuccessful()){
                    CartResponseModel cartResponseModel = response.body();
                    if (!cartResponseModel.isError()){
                        loader.cancel();
                        Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
                        getcartdata();
                    }
                }else {
                    loader.cancel();
                }
            }

            @Override
            public void onFailure(Call<CartResponseModel> call, Throwable t) {
                loader.cancel();
            }
        });
    }
    //====== get cart data =======//
    private void getcartdata() {
        loader = new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Fetching your data..");
        loader.setCancelable(false);
        loader.show();
        userID = pref.get(Constants.userID);
        MycartInputModel mycartInputModel = new MycartInputModel();
        mycartInputModel.setUserID(userID);
        Call<MycartResponseModel> mycartResponseModelCall = ApiClient.getClient().create(ApiInterface.class).getcart(mycartInputModel);
        mycartResponseModelCall.enqueue(new Callback<MycartResponseModel>() {
            @Override
            public void onResponse(Call<MycartResponseModel> call, Response<MycartResponseModel> response) {
                if (response.isSuccessful()){
                    MycartResponseModel mycartResponseModel = response.body();
                    if (!mycartResponseModel.isError()){
                        loader.cancel();
                        int count = mycartResponseModel.getData().size();
                        String data = String.valueOf(count);
                        tv_count.setText("data");
                    }else {
                        loader.cancel();
                        tv_count.setText("0");
                    }
                }else {
                    loader.cancel();
                    tv_count.setText("0");
                }
            }

            @Override
            public void onFailure(Call<MycartResponseModel> call, Throwable t) {
                loader.cancel();
                tv_count.setText("0");
            }
        });
    }


    //========= login alert box ==========//
    private void getalertbox() {
        loader = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Please login to your Account to continue");
        loader.setCancelable(true);
        loader.setConfirmText("Login Now");
        loader.setCancelText("Later");
        loader.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        loader.show();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)|| drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.START);
            drawer.closeDrawer(GravityCompat.END);
        } else {
            String position = pref.get(Constants.fragment_position);
            if (position.equals("0")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoard.this);
                builder.setTitle("Please confirm");
                builder.setMessage("Do you want to exit the app ?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        startActivity(a);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (position.equals("2")){
                displayView(0);
            }else if (position.equals("3")){
                displayView(2);
            }else if(position.equals("4")){
                displayView(6);
            }
        }
    }
    //================Fragment Loader =============================//
    private void loadFragment(Fragment frag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.nav_host_fragment, frag);
        fragmentTransaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
