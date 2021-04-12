package com.crTech.prakashmarble.ui.Activity.ui.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.ForgotPasswordInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.ForgotPasswordResponseModel;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText ed_mail;
    Button btn_login;
    Preferences pref;
    SweetAlertDialog loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        getid();
        setListner();
    }

    private void getid() {
        pref = new Preferences(getApplicationContext());
        ed_mail = (TextInputEditText)findViewById(R.id.eduser);
        btn_login = (Button)findViewById(R.id.login_button);
    }

    private void setListner() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                if (!ed_mail.getText().toString().equals("")){
                    hitapi(ed_mail.getText().toString());
                }else {
                    ed_mail.setError("Email is required");
                }
                break;
        }
    }

    private void hitapi(String email) {
        loader = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Sending Mail request..");
        loader.setCancelable(false);
        loader.show();
        ForgotPasswordInputModel forgotPasswordInputModel = new ForgotPasswordInputModel();
        forgotPasswordInputModel.setEmail(email);
        Call<ForgotPasswordResponseModel> fortgotPasswordResponseMOdelCall = ApiClient.getClient().create(ApiInterface.class).forgotpass(forgotPasswordInputModel);
        fortgotPasswordResponseMOdelCall.enqueue(new Callback<ForgotPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponseModel> call, Response<ForgotPasswordResponseModel> response) {
                if (response.isSuccessful()){
                    ForgotPasswordResponseModel forgotPasswordResponseModel = response.body();
                    if (!forgotPasswordResponseModel.isError()){
                        loader.cancel();
                        Toast.makeText(ForgotPassActivity.this, "Reset link have been sent to your mailID", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                    }else {
                        loader.cancel();
                        Toast.makeText(ForgotPassActivity.this, forgotPasswordResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    loader.cancel();
                    Toast.makeText(ForgotPassActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordResponseModel> call, Throwable t) {
                loader.cancel();
                Toast.makeText(ForgotPassActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}