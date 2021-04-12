package com.crTech.prakashmarble.ui.Activity.ui.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.LoginInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.LoginResponseModel;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText ed_user,ed_password;
    TextView tv_register,tv_forgot;
    Button btn_login;
    CheckBox ch_show;
    Preferences pref;
    SweetAlertDialog loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getid();
        setListner();
    }

    private void getid() {
        pref = new Preferences(getApplicationContext());
        ed_user = (TextInputEditText)findViewById(R.id.eduser);
        ed_password = (TextInputEditText)findViewById(R.id.edpass);
        btn_login = (Button)findViewById(R.id.login_button);
        ch_show = (CheckBox)findViewById(R.id.showpassword);
        tv_register = (TextView)findViewById(R.id.register);
        tv_forgot = (TextView)findViewById(R.id.forgotpassword);
    }

    private void setListner() {
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);
        ch_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                checkdata();
                break;
            case R.id.register:
                Intent regsiterint = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(regsiterint);
                break;
            case R.id.forgotpassword:
                Intent forgotint = new Intent(getApplicationContext(),ForgotPassActivity.class);
                startActivity(forgotint);
                break;
            case R.id.showpassword:
                if (ch_show.isChecked()){
                    ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }
//========= check login fields ========//
    private void checkdata() {
        if (!ed_user.getText().toString().equals("") ){

          if (!ed_password.getText().toString().equals("")){
              loginapi(ed_user.getText().toString(),ed_password.getText().toString());
          }else {
              ed_password.setError("Password Required");
          }
        }else {
            ed_user.setError("User Name Required");

        }
    }
//======== Login Api =============//
    private void loginapi(String username, String password) {
        loader = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Authenticating..");
        loader.setCancelable(false);
        loader.show();
        LoginInputModel loginInputModel = new LoginInputModel();
        loginInputModel.setUsername(username);
        loginInputModel.setPassword(password);
        Call<LoginResponseModel> loginResponseModelCall = ApiClient.getClient().create(ApiInterface.class).login(loginInputModel);
        loginResponseModelCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()){
                    LoginResponseModel loginResponseModel = response.body();
                    if (!loginResponseModel.isError()){
                        loader.cancel();
                        pref.set(Constants.islogin,"1");
                        pref.set(Constants.username,loginResponseModel.getData().get(0).getUsername());
                        pref.set(Constants.user_mail,loginResponseModel.getData().get(0).getEmail());
                        pref.set(Constants.phonenumber,loginResponseModel.getData().get(0).getPhone());
                        pref.set(Constants.userID,loginResponseModel.getData().get(0).getUserID());
                        pref.commit();
                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),DashBoard.class);
                        startActivity(intent);
                    }else {
                        loader.cancel();
                        Toast.makeText(LoginActivity.this, loginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    loader.cancel();
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                loader.cancel();
                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}