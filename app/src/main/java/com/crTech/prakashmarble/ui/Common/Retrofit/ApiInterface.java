package com.crTech.prakashmarble.ui.Common.Retrofit;

import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.CartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.CartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryInput;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryResponse;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DeatilsInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsResponse;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductResponse;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.BookingHistoryResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.ChangePasswordInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.ChangePasswordResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.ForgotPasswordInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.ForgotPasswordResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.LoginInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.LoginResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.RegisterInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel.ShopResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    /* Get Sub-Cat List */
    @Headers({"Accept: application/json"})
    @POST("getProductTypes")
    Call<CategoryResponse> getcategoryData(@Body CategoryInput categoryInputModel);
    /* Get Product List */
    @Headers({"Accept: application/json"})
    @POST("productList")
    Call<ProductResponse> getproductData(@Body ProductInputModel productInputModel);
    /* Get Product Details */
    @Headers({"Accept: application/json"})
    @POST("productDetails")
    Call<DetailsResponse> productdetails(@Body DeatilsInputModel deatilsInputModel);
    /*Login Api*/
    @Headers({"Accept: application/json"})
    @POST("login")
    Call<LoginResponseModel> login(@Body LoginInputModel loginInputModel);
    /*Sign Up*/
    @Headers({"Accept: application/json"})
    @POST("signup")
    Call<LoginResponseModel> register(@Body RegisterInputModel registerInputModel);
    /*Change Password api*/
    @Headers({"Accept: application/json"})
    @POST("resetPassword")
    Call<ChangePasswordResponseModel> changepassword(@Body ChangePasswordInputModel changePasswordInputModel);
    /*Forgot password api*/
    @Headers({"Accept: application/json"})
    @POST("forgotPassword")
    Call<ForgotPasswordResponseModel> forgotpass(@Body ForgotPasswordInputModel forgotPasswordInputModel);
    /*Add item to cart api*/
    @Headers({"Accept: application/json"})
    @POST("addToCart")
    Call<CartResponseModel> additem(@Body CartInputModel cartInputModel);
    /*Get Cart Data*/
    @Headers({"Accept: application/json"})
    @POST("getUserCart")
    Call<MycartResponseModel> getcart(@Body MycartInputModel mycartInputModel);
    /*Get Shop list*/
    @Headers({"Accept: application/json"})
    @POST("getStoresList")
    Call<ShopResponseModel> getshopslist();
    /*Get booking history*/
    @Headers({"Accept: application/json"})
    @POST("getUserOrder")
    Call<BookingHistoryResponseModel> getbookinghistory(@Body MycartInputModel mycartInputModel);
}



