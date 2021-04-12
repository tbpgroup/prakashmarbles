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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.LoginResponseModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.RegisterInputModel;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputEditText ed_user,ed_password,ed_mail,ed_phone;
    TextView tv_register;
    Button btn_login;
    CheckBox ch_show;
    Preferences pref;
    SweetAlertDialog loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getid();
        setListner();
    }

    private void getid() {
        pref = new Preferences(getApplicationContext());
        ed_user = (TextInputEditText) findViewById(R.id.eduser);
        ed_mail = (TextInputEditText)findViewById(R.id.edmailID);
        ed_phone = (TextInputEditText)findViewById(R.id.edmobile);
        ed_password = (TextInputEditText)findViewById(R.id.edpass);
        btn_login = (Button)findViewById(R.id.login_button);
        tv_register = (TextView)findViewById(R.id.register);
        ch_show = (CheckBox)findViewById(R.id.showpassword);
    }

    private void setListner() {
        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        ch_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                checkdata();
                break;
            case R.id.register:
                finish();
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
//====== check for data =========//
    private void checkdata() {
        if (!ed_user.getText().toString().equals("") ){
            if (!ed_mail.getText().toString().equals("")){
                if (!ed_phone.getText().toString().equals("")){
                    if (!ed_password.getText().toString().equals("")){
                        registerapi(ed_user.getText().toString(),ed_password.getText().toString(),ed_mail.getText().toString(),ed_phone.getText().toString());
                    }else {
                        ed_password.setError("Password Required");
                    }
                }else {
                    ed_phone.setError("Phone Number Required");
                }
            }else {
                ed_mail.setError("Email Id Required");
            }
        }else {
            ed_user.setError("User Name Required");
        }
    }

    private void registerapi(String user, String password, String mail, String phone) {
        loader = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Registering..");
        loader.setCancelable(false);
        loader.show();
        RegisterInputModel registerInputModel = new RegisterInputModel();
        registerInputModel.setEmail(mail);
        registerInputModel.setUsername(user);
        registerInputModel.setPassword(password);
        registerInputModel.setPhone(phone);
        Call<LoginResponseModel> loginResponseModelCall = ApiClient.getClient().create(ApiInterface.class).register(registerInputModel);
        loginResponseModelCall.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()){
                    LoginResponseModel loginResponseModel = response.body();
                    if (!loginResponseModel.isError()){
                        loader.cancel();
                        Intent loginint = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(loginint);
                    }else {
                        loader.cancel();
                        Toast.makeText(RegisterActivity.this, loginResponseModel.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    loader.cancel();
                    Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                loader.cancel();
                Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}