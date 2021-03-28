package com.crTech.prakashmarble.ui.Common.Retrofit;

import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DeatilsInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsResponse;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    /* Get Sub-Cat List */
    @Headers({"Accept: application/json"})
    @POST("getProductTypes")
    Call<ProductResponse> getcategoryData(@Body ProductInputModel categoryInputModel);
    /* Get Product List */
    @Headers({"Accept: application/json"})
    @POST("productList")
    Call<ProductResponse> getproductData(@Body ProductInputModel productInputModel);
    /* Get Product Details */
    @Headers({"Accept: application/json"})
    @POST("productDetails")
    Call<DetailsResponse> productdetails(@Body DeatilsInputModel deatilsInputModel);

}



