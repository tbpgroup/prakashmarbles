package com.crTech.prakashmarble.ui.Activity.ui.User;

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
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.BookingHistoryModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.BookingHistoryResponseModel;
import com.crTech.prakashmarble.ui.Adapters.CartProduct_Adapter;
import com.crTech.prakashmarble.ui.Adapters.HistoryAdapter;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    private SweetAlertDialog loader;
    ArrayList<BookingHistoryModel> mycartDataModels = new ArrayList<BookingHistoryModel>();
    HistoryAdapter adapter;
    Preferences pref;
    String userID;
    ImageView iv_nodata;
    View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_bookinghistory, container, false);
        getid();
        setListner();
        getcartdata();
        return v;
    }

    private void getid() {
        pref = new Preferences(getContext());
        recyclerView = (RecyclerView)v.findViewById(R.id.rv_book);
        pref.set(Constants.fragment_position,"1");
        pref.commit();
    }

    private void setListner() {
    }

    private void getcartdata() {
        userID = pref.get(Constants.userID);
        MycartInputModel mycartInputModel = new MycartInputModel();
        mycartInputModel.setUserID(Integer.parseInt(userID));
        Call<BookingHistoryResponseModel> mycartResponseModelCall = ApiClient.getClient().create(ApiInterface.class).getbookinghistory(mycartInputModel);
        mycartResponseModelCall.enqueue(new Callback<BookingHistoryResponseModel>() {
            @Override
            public void onResponse(Call<BookingHistoryResponseModel> call, Response<BookingHistoryResponseModel> response) {
                if (response.isSuccessful()){
                    BookingHistoryResponseModel mycartResponseModel = response.body();
                    if (!mycartResponseModel.isError()){
                        mycartDataModels.addAll(mycartResponseModel.getData());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                        adapter = new HistoryAdapter(getContext(),mycartDataModels);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setVisibility(View.VISIBLE);
                    }else {

                    }
                }else {

                }
            }

            @Override
            public void onFailure(Call<BookingHistoryResponseModel> call, Throwable t) {
                //loader.cancel();
                // tv_count.setText("0");
            }
        });
    }
}