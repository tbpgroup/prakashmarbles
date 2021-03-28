package com.crTech.prakashmarble.ui.Activity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class HomeFragment extends Fragment implements View.OnClickListener {

    //Category_Adapter category_adapter;
    //Trending_Adapter trending_adapter;
    //Offers_Adapter offers_adapter;

    SliderLayout mDemoSlider ;
    Preferences pref;
    //Snackbar snackbar;
    private SweetAlertDialog loader;
    LinearLayout lv_layout,lv_button;
    TextView tv_more_offers;
    String token;
    String[] img = new String[20];
    RecyclerView rv_category,rv_offers,rv_trending;
//    ArrayList<HashMap<String,String>> offers_list = new ArrayList<HashMap<String, String>>();
//    ArrayList<HashMap<String,String>> trending_list = new ArrayList<HashMap<String, String>>();
//    ArrayList<HashMap<String,String>> category_list = new ArrayList<HashMap<String, String>>();
//    ArrayList<String> search_items = new ArrayList<>();
    String lat,lng;
    //GetLocation location;
    View v;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        getid();
        setListner();

        getImg(4);
        return v;
    }

    private void getid() {
        pref = new Preferences(getContext());

        //location = new GetLocation(getContext());
        mDemoSlider = (SliderLayout)v.findViewById(R.id.slider);
        lv_layout = (LinearLayout)v.findViewById(R.id.linear);
       // rv_offers = (RecyclerView) v.findViewById(R.id.offers_rv);
        //rv_trending = (RecyclerView) v.findViewById(R.id.trending_rv);
//        rv_category = (RecyclerView)v.findViewById(R.id.category_recycler);
        //tv_more_offers = (TextView)v.findViewById(R.id.offers_all);
    }

    private void setListner() {
    }

    //=================== Slider IMages ======================================//
    private void getImg(int size) {
       // HashMap<String,String> HashMapForLocalRes = new HashMap<String, String>();
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("AGL",R.drawable.slider1);
        file_maps.put("Kajaria",R.drawable.slider2);
        file_maps.put("Simpolo",R.drawable.slider3);
        file_maps.put("RAK", R.drawable.slider4);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    @Override
    public void onClick(View v) {

    }
}