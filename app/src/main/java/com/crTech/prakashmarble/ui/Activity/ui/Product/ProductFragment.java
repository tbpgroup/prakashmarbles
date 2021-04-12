package com.crTech.prakashmarble.ui.Activity.ui.Product;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryResponse;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductResponse;
import com.crTech.prakashmarble.ui.Adapters.Category_Adapter;
import com.crTech.prakashmarble.ui.Adapters.ProductListAdapter;
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

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    ImageView iv_nodata;
    Preferences pref;
    private SweetAlertDialog loader;
    ProductListAdapter adapter;
    ArrayList<ProductDataModel> productDataModelArrayList = new ArrayList<ProductDataModel>();
    Spinner spinner,spinner_brand;

    View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_product, container, false);
        getid();
        setListner();
        getproducts();
        return v;
    }

    private void getid() {
        recyclerView = (RecyclerView)v.findViewById(R.id.rv_prod);
        iv_nodata = (ImageView)v.findViewById(R.id.nodata);
        spinner = (Spinner)v.findViewById(R.id.sp_list);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.filter_items, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner_brand = (Spinner)v.findViewById(R.id.sp_brand);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),
                R.array.filter_brands, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner_brand.setAdapter(adapter2);
        pref = new Preferences(getContext());
        pref.set(Constants.fragment_position,"3");
        pref.commit();
    }

    private void setListner() {
    }
//========= get all products list ========//
    private void getproducts() {
        loader = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Getting Products..");
        loader.setCancelable(false);
        loader.show();
        productDataModelArrayList.clear();
        String id = pref.get(Constants.sub_cat_id);
        int subcat_id = Integer.parseInt(id);
        ProductInputModel productInputModel = new ProductInputModel();
        productInputModel.setProductTypeID(subcat_id);
        Call<ProductResponse> productResponseCall = ApiClient.getClient().create(ApiInterface.class).getproductData(productInputModel);
        productResponseCall.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                    ProductResponse categoryResponse = response.body();
                    if (categoryResponse.isError()){
                        loader.cancel();
                        Snackbar.make(v, categoryResponse.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        recyclerView.setVisibility(View.GONE);
                        iv_nodata.setVisibility(View.VISIBLE);
                    }
                    else {
                        productDataModelArrayList.addAll(categoryResponse.getData());
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
                        adapter = new ProductListAdapter(getContext(),productDataModelArrayList);
                        recyclerView.setLayoutManager(gridLayoutManager);
                        recyclerView.setAdapter(adapter);
                        loader.cancel();
                        recyclerView.setVisibility(View.VISIBLE);
                      //  iv_nodata.setVisibility(View.GONE);
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
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                loader.cancel();
                Snackbar.make(v, t.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                recyclerView.setVisibility(View.GONE);
                iv_nodata.setVisibility(View.VISIBLE);
            }
        });

    }
}