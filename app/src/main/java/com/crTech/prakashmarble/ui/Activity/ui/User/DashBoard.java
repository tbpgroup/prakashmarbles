package com.crTech.prakashmarble.ui.Activity.ui.User;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.CartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.CartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductDetailFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductFragment;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.home.HomeFragment;
import com.crTech.prakashmarble.ui.Activity.ui.home.ShopsFragments;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.infideap.drawerbehavior.Advance3DDrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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

    RelativeLayout rv_tiles,rv_marbles,rv_granite,rv_sani,rv_cp,rv_home,rv_cart,rv_myorders,rv_locateus;
    DrawerLayout mDrawerLayout;
    Context context = DashBoard.this;
    Preferences pref;
    LinearLayout lv_block;
    CoordinatorLayout coordinatorLayout;
    FloatingActionButton fab_conatact;
    Handler mHandler;
    SweetAlertDialog loader;
    TextView tv_username,tv_count,tv_logout;
    Toolbar toolbar;
    ImageView iv_cart,iv_profile,iv_scanner;
    String userID;
    Button btn_login;
    NavigationView navigationView;
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
        userID = pref.get(Constants.userID);
        if (islogin.equals("1")){
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
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorlv);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rv_home = (RelativeLayout)findViewById(R.id.rl_home);
        drawer = (Advance3DDrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        iv_cart = (ImageView)findViewById(R.id.cart);
        tv_count = (TextView)findViewById(R.id.count);
        tv_count.setText("0");
        rv_tiles = (RelativeLayout)findViewById(R.id.rl_tile);
        rv_marbles = (RelativeLayout)findViewById(R.id.rl_marbles);
        rv_granite = (RelativeLayout)findViewById(R.id.rl_granite);
        rv_sani = (RelativeLayout)findViewById(R.id.rl_sanit);
        rv_cp = (RelativeLayout)findViewById(R.id.rl_cp);
        rv_cart = (RelativeLayout)findViewById(R.id.rl_cart);
        rv_myorders = (RelativeLayout)findViewById(R.id.rl_order);
        rv_locateus = (RelativeLayout)findViewById(R.id.rl_locate);
        btn_login = (Button)findViewById(R.id.loginbtn);
        lv_block = (LinearLayout)findViewById(R.id.userinfo);
        tv_username = (TextView)findViewById(R.id.tv_name);
        iv_profile = (ImageView)findViewById(R.id.imageView);
        tv_logout =  (TextView)findViewById(R.id.tv_logout);
        iv_scanner = (ImageView)findViewById(R.id.scanner);

        String userlog = pref.get(Constants.islogin);
        if (userlog.equals("1")){
            btn_login.setVisibility(View.GONE);
            lv_block.setVisibility(View.VISIBLE);
            tv_logout.setVisibility(View.VISIBLE);
            String username = pref.get(Constants.username);
            tv_username.setText(username);
        }else {
            btn_login.setVisibility(View.VISIBLE);
            lv_block.setVisibility(View.GONE);
            tv_logout.setVisibility(View.GONE);
        }
    }
    private void setlistner() {
        iv_cart.setOnClickListener(this);
        tv_count.setOnClickListener(this);
        rv_tiles.setOnClickListener(this);
        rv_marbles.setOnClickListener(this);
        rv_granite.setOnClickListener(this);
        rv_sani.setOnClickListener(this);
        rv_cp.setOnClickListener(this);
        rv_home.setOnClickListener(this);
        rv_cart.setOnClickListener(this);
        rv_myorders.setOnClickListener(this);
        rv_locateus.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_logout.setOnClickListener(this);
        iv_scanner.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cart:
                displayView(8);
                break;
            case R.id.count:
                displayView(8);
                break;
            case R.id.rl_home:
                drawer.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(intent);
                break;
            case R.id.rl_tile:
                displayView(1);
                break;
            case R.id.rl_marbles:
                displayView(2);
                break;
            case R.id.rl_granite:
                displayView(3);
                break;
            case R.id.rl_sanit:
                displayView(4);
                break;
            case R.id.rl_cp:
                displayView(5);
                break;
            case R.id.rl_cart:
                displayView(8);
                break;
            case R.id.loginbtn:
                drawer.closeDrawer(GravityCompat.START);
                Intent loginintent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(loginintent);
                break;
            case R.id.tv_logout:
                drawer.closeDrawer(GravityCompat.START);
                pref.set(Constants.username,"");
                pref.set(Constants.islogin,"0");
                pref.set(Constants.userID,"0");
                pref.commit();
                Intent logoutintent = new Intent(getApplicationContext(),DashBoard.class);
                startActivity(logoutintent);
                break;
            case R.id.scanner:
                IntentIntegrator integrator = new IntentIntegrator(DashBoard.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
                break;
            case R.id.rl_locate:
                displayView(9);
                break;
            case R.id.rl_order:
                displayView(10);
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                //Log.e("Scan*******", "Cancelled scan");
                Toast.makeText(this, "not sScanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            } else {
                super.onActivityResult(requestCode, resultCode, data);
               // Log.e("Scan", "Scanned");
                    String resut = result.getContents();
                pref.set(Constants.prod_id,resut);
                pref.commit();
                displayView(7);
                //tv_qr_readTxt.setText(result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
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
                drawer.closeDrawer(GravityCompat.START);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 2:
                navigationView.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 3:
                navigationView.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 4:
                navigationView.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 5:
                navigationView.setVisibility(View.GONE);
                drawer.closeDrawer(GravityCompat.START);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CategoryFragment());
                break;
            case 6:
                drawer.closeDrawer(GravityCompat.START);
                navigationView.setVisibility(View.GONE);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new ProductFragment());
                break;
            case 7:
                drawer.closeDrawer(GravityCompat.START);
                navigationView.setVisibility(View.GONE);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new ProductDetailFragment());
                break;
            case 8:
                drawer.closeDrawer(GravityCompat.START);
                navigationView.setVisibility(View.GONE);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new CartFragment());
                break;
            case 9:
                drawer.closeDrawer(GravityCompat.START);
                navigationView.setVisibility(View.GONE);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new ShopsFragments());
                break;
            case 10:
                drawer.closeDrawer(GravityCompat.START);
                navigationView.setVisibility(View.GONE);
                fab_conatact.setVisibility(View.GONE);
                loadFragment(new BookingHistoryFragment());
                break;

        }
    }
//======= get cart info details and check user logged in  ===========//
    public void getcartinfo(DetailsDataModel detailsDataModel,String qty) {
        String userlogg = pref.get(Constants.islogin);
        if (userlogg.equals("1")){
            addtocard(detailsDataModel,qty);
        }else {
            getalertbox();
        }

//        try {
//
//        }catch (Exception e){
//            getalertbox();
//        }
    }
//========= adding product for cart ========//
    public void addtocard(DetailsDataModel detailsDataModel,String i) {

        CartInputModel cartInputModel = new CartInputModel();
        cartInputModel.setProdID(Integer.parseInt(detailsDataModel.getProdID()));
        cartInputModel.setQuantity(i);
        cartInputModel.setUserID(Integer.parseInt(userID));
        Call<CartResponseModel> cartResponseModelCall = ApiClient.getClient().create(ApiInterface.class).additem(cartInputModel);
        cartResponseModelCall.enqueue(new Callback<CartResponseModel>() {
            @Override
            public void onResponse(Call<CartResponseModel> call, Response<CartResponseModel> response) {
                if (response.isSuccessful()){
                    CartResponseModel cartResponseModel = response.body();
                    if (!cartResponseModel.isError()){
                       // loader.cancel();

                        Snackbar.make(coordinatorLayout, "Added to cart", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        getcartdata();
                    }
                    else {
                        Toast.makeText(context, cartResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponseModel> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
//                loader.cancel();
            }
        });
    }
    //====== get cart data =======//
    private void getcartdata() {
//        loader = new SweetAlertDialog(DashBoard.this, SweetAlertDialog.PROGRESS_TYPE);
//        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        loader.setTitleText("Fetching your data..");
//        loader.setCancelable(false);
//        loader.show();
        userID = pref.get(Constants.userID);
        Toast.makeText(getApplicationContext(), userID, Toast.LENGTH_SHORT).show();
        MycartInputModel mycartInputModel = new MycartInputModel();
        mycartInputModel.setUserID(Integer.parseInt(userID));
        Call<MycartResponseModel> mycartResponseModelCall = ApiClient.getClient().create(ApiInterface.class).getcart(mycartInputModel);
        mycartResponseModelCall.enqueue(new Callback<MycartResponseModel>() {
            @Override
            public void onResponse(Call<MycartResponseModel> call, Response<MycartResponseModel> response) {
                if (response.isSuccessful()){
                    MycartResponseModel mycartResponseModel = response.body();
                    if (!mycartResponseModel.isError()){
                     //   loader.cancel();
                        int count = mycartResponseModel.getData().size();
                        String data = String.valueOf(count);
                        tv_count.setText(data);
                    }else {
                       // loader.cancel();
                        tv_count.setText("0");
                    }
                }else {
                   // loader.cancel();
                    tv_count.setText("0");
                }
            }

            @Override
            public void onFailure(Call<MycartResponseModel> call, Throwable t) {
                //loader.cancel();
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
