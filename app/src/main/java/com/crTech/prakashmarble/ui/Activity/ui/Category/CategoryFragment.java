package com.crTech.prakashmarble.ui.Activity.ui.Category;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryInput;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryResponse;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductResponse;
import com.crTech.prakashmarble.ui.Adapters.Category_Adapter;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    RecyclerView recyclerView;
    private SweetAlertDialog loader;
    ArrayList<CategoryDataModel> categorylist = new ArrayList<CategoryDataModel>();
    Category_Adapter adapter;
    Preferences pref;
    ImageView iv_nodata;
    View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_category, container, false);
        getid();
        setListner();
        getcategoryData();
        return v;
    }
//======= get category list data ==========//
    private void getcategoryData() {
        loader = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Loading");
        loader.setCancelable(false);
        loader.show();
        categorylist.clear();
        String id = pref.get(Constants.cat_id);
        int cat_id = Integer.parseInt(id);
        CategoryInput categoryInput = new CategoryInput();
        categoryInput.setCatID(cat_id);
        Call<CategoryResponse> productResponseCall = ApiClient.getClient().create(ApiInterface.class).getcategoryData(categoryInput);
        productResponseCall.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                    if (response.isSuccessful()){
                        CategoryResponse categoryResponse = response.body();
                        if (categoryResponse.isError()){
                            loader.cancel();
                            Snackbar.make(v, categoryResponse.getMessage(), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            recyclerView.setVisibility(View.GONE);
                            iv_nodata.setVisibility(View.VISIBLE);
                        }
                        else {
                            categorylist.addAll(categoryResponse.getData());
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                            adapter = new Category_Adapter(getContext(),categorylist);
                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            recyclerView.setAdapter(adapter);
                            loader.cancel();
                            recyclerView.setVisibility(View.VISIBLE);
                            iv_nodata.setVisibility(View.GONE);
                        }
                    }else {
                        loader.cancel();
                        Snackbar.make(v, response.message(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        recyclerView.setVisibility(View.GONE);
                        iv_nodata.setVisibility(View.VISIBLE);
                    }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                loader.cancel();
                Snackbar.make(v, t.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                recyclerView.setVisibility(View.GONE);
                iv_nodata.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setListner() {
    }

    private void getid() {
        recyclerView = (RecyclerView)v.findViewById(R.id.rvcate);
        pref = new Preferences(getContext());
        iv_nodata = (ImageView)v.findViewById(R.id.nodata);
        pref.set(Constants.fragment_position,"2");
        pref.commit();
    }
}