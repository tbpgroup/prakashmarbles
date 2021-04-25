package com.crTech.prakashmarble.ui.Activity.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel.ShopResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel.ShopsListModel;
import com.crTech.prakashmarble.ui.Adapters.CartProduct_Adapter;
import com.crTech.prakashmarble.ui.Adapters.ShopsAdpter;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopsFragments extends Fragment {

    RecyclerView recyclerView;
    private SweetAlertDialog loader;
    ArrayList<ShopsListModel> shopsListModels = new ArrayList<ShopsListModel>();
    ShopsAdpter adapter;
    Preferences pref;
    String userID;
    ImageView iv_nodata;
    View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.applocationfrag, container, false);
        getid();
        setListner();
        getcartdata();
        return v;
    }

    private void getid() {
        pref = new Preferences(getContext());
        recyclerView = (RecyclerView)v.findViewById(R.id.rv_shops);
        pref.set(Constants.fragment_position,"1");
        pref.commit();
    }

    private void setListner() {
    }

    private void getcartdata() {

        Call<ShopResponseModel> mycartResponseModelCall = ApiClient.getClient().create(ApiInterface.class).getshopslist();
        mycartResponseModelCall.enqueue(new Callback<ShopResponseModel>() {
            @Override
            public void onResponse(Call<ShopResponseModel> call, Response<ShopResponseModel> response) {
                if (response.isSuccessful()){
                    ShopResponseModel mycartResponseModel = response.body();
                    if (!mycartResponseModel.isError()){
                        shopsListModels.addAll(mycartResponseModel.getData());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                        adapter = new ShopsAdpter(getContext(),shopsListModels);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setVisibility(View.VISIBLE);
                    }else {

                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<ShopResponseModel> call, Throwable t) {
                //loader.cancel();
                // tv_count.setText("0");
            }
        });
    }
}