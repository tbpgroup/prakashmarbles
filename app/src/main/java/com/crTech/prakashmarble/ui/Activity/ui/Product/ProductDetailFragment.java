package com.crTech.prakashmarble.ui.Activity.ui.Product;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.DashBoard;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DeatilsInputModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsResponse;
import com.crTech.prakashmarble.ui.Adapters.Related_Adapter;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiClient;
import com.crTech.prakashmarble.ui.Common.Retrofit.ApiInterface;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailFragment extends Fragment implements View.OnClickListener {
    Preferences pref;
    ImageView iv_nodata;
    private SweetAlertDialog loader;
    DetailsDataModel detailsDataModel;
    ArrayList<DetailsDataModel> relatedinfolist = new ArrayList<DetailsDataModel>();
    SliderLayout mDemoSlider ;
    String[] img = new String[20];
    TextView tv_prodname,tv_stock,tv_price,tv_mrp,tv_save,tv_details;
    ImageView iv_brand;
    boolean isavialable = true;
    Related_Adapter adapter;
    RecyclerView rv_related;
    Button btn;
    View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_product_detail, container, false);
        getid();
        setListner();
        getproducts();
        return v;
    }

    private void getid() {
        tv_prodname = (TextView)v.findViewById(R.id.prodname);
        tv_stock = (TextView)v.findViewById(R.id.prodstock);
        tv_mrp = (TextView)v.findViewById(R.id.prodmrp);
        tv_price = (TextView)v.findViewById(R.id.prodprice);
        tv_save = (TextView)v.findViewById(R.id.prodsave);
        iv_brand = (ImageView)v.findViewById(R.id.prodbrand);
        mDemoSlider = (SliderLayout)v.findViewById(R.id.slider);
        rv_related = (RecyclerView)v.findViewById(R.id.rv_similar);
        tv_details = (TextView)v.findViewById(R.id.prod_descriptio);
        rv_related = (RecyclerView)v.findViewById(R.id.rv_similar);
        btn = (Button)v.findViewById(R.id.addcart);
        pref = new Preferences(getContext());
        pref.set(Constants.fragment_position,"4");
        pref.commit();
    }

    private void setListner() {
        btn.setOnClickListener(this);
    }
//====== get products details =========//
    private void getproducts() {
        loader = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        loader.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        loader.setTitleText("Getting Products..");
        loader.setCancelable(false);
        loader.show();
        String proid = pref
                .get(Constants.prod_id);
        int id = Integer.parseInt(proid);
        DeatilsInputModel deatilsInputModel = new DeatilsInputModel();
        deatilsInputModel.setProdID(id);
        Call<DetailsResponse> detailsResponseCall = ApiClient.getClient().create(ApiInterface.class).productdetails(deatilsInputModel);
        detailsResponseCall.enqueue(new Callback<DetailsResponse>() {
            @Override
            public void onResponse(Call<DetailsResponse> call, Response<DetailsResponse> response) {
                if (response.isSuccessful()){
                    DetailsResponse detailsResponse = response.body();
                    if (!detailsResponse.isError()){
                        relatedinfolist.clear();
                        detailsDataModel = detailsResponse.getData().get(0);
                        relatedinfolist.addAll(detailsResponse.getRelated_products());
                        img[0] =detailsDataModel.getGallery1();
                        img[1] =detailsDataModel.getGallery2();
                        img[2] =detailsDataModel.getGallery3();
                        img[3] =detailsDataModel.getGallery4();

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                        adapter = new Related_Adapter(getContext(),relatedinfolist);
                        rv_related.setLayoutManager(linearLayoutManager);
                        rv_related.setAdapter(adapter);
                       loadData();
                        loader.cancel();
                    }
                    loader.cancel();

                }else{
                    loader.cancel();
                    Snackbar.make(v, response.message(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }

            @Override
            public void onFailure(Call<DetailsResponse> call, Throwable t) {
                loader.cancel();
                Snackbar.make(v, t.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
//======== load Product data ==========//
    private void loadData() {
        String stock = "Available";
        getImg(img,img.length);
        tv_prodname.setText(detailsDataModel.getProd_name());
        if (detailsDataModel.getBrand().equals("AGL")){
            Glide.with(getContext()).load(R.drawable.client1).into(iv_brand);
        }else if(detailsDataModel.getBrand().equals("RAK")){
            Glide.with(getContext()).load(R.drawable.client4).into(iv_brand);
        }else if(detailsDataModel.getBrand().equals("SE")){
            Glide.with(getContext()).load(R.drawable.client5).into(iv_brand);
        }
        tv_stock.setText(detailsDataModel.getStock() +" Units Avaliable");
        int units = Integer.parseInt(detailsDataModel.getStock());
        if (units<10){
            stock = "Available";
            isavialable = true;
            tv_stock.setTextColor(Color.parseColor("#FF0000"));
        }else if (units==0){
            isavialable = false;
            stock = "Out of stock";
            tv_stock.setText("OUT OF STOCK");
            tv_stock.setTextColor(Color.parseColor("#FF0000"));
        }else {
            stock = "Available";
            isavialable = true;
            tv_stock.setTextColor(Color.parseColor("#008000"));
        }

        tv_mrp.setText("₹ "+detailsDataModel.getMrp()+"/-");
        tv_mrp.setPaintFlags(tv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tv_price.setText("₹ "+detailsDataModel.getPrice()+"");
        double save = Integer.parseInt(detailsDataModel.getMrp()) - Integer.parseInt(detailsDataModel.getPrice());
        String data = String.valueOf(save);
        tv_save.setText("Save "+data+" ₹");
        tv_details.setText("• "+detailsDataModel.getProd_description()+"\n• "+detailsDataModel.getProduct_category()+"\n• "+detailsDataModel.getSize()+"\n •"+detailsDataModel.getProd_type()+
                "\n• "+stock+"\n• "+detailsDataModel.getProd_variant());
    }
    //=================== Slider IMages ======================================//
    private void getImg(String[] img, int size) {
        HashMap<String,String> HashMapForLocalRes = new HashMap<String, String>();
        for (int i = 0; i < size; i++) {
            HashMapForLocalRes.put(img[i], img[i]);
        }
        for(String name : HashMapForLocalRes.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .image(HashMapForLocalRes.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addcart:
                ((DashBoard)getContext()).getcartinfo(detailsDataModel,1);
                break;
        }
    }
}