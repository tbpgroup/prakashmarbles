package com.crTech.prakashmarble.ui.Activity.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.DashBoard;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class HomeFragment extends Fragment implements View.OnClickListener {

    //Category_Adapter category_adapter;
    //Trending_Adapter trending_adapter;
    //Offers_Adapter offers_adapter;

    SliderLayout mDemoSlider ;
    Preferences pref;
    CardView cdtiles,cdmarbles,cdgranite,cdsanitary,cdcp;
    //Snackbar snackbar;
    private SweetAlertDialog loader;
    LinearLayout lv_layout,lv_button;
    TextView tv_more_offers;
    String token;
    String[] img = new String[20];
    RecyclerView rv_category,rv_offers,rv_trending;
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
      cdtiles = (CardView)v.findViewById(R.id.cd_tiles);
      cdmarbles = (CardView)v.findViewById(R.id.cd_marbles);
      cdgranite = (CardView)v.findViewById(R.id.cd_granite);
      cdsanitary = (CardView)v.findViewById(R.id.cd_sanitary);
      cdcp = (CardView)v.findViewById(R.id.cd_cp);
    }

    private void setListner() {
        cdtiles.setOnClickListener(this);
        cdgranite.setOnClickListener(this);
        cdmarbles.setOnClickListener(this);
        cdsanitary.setOnClickListener(this);
        cdcp.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.cd_tiles:
                pref.set(Constants.cat_id,"1");
                pref.commit();
                ((DashBoard)getActivity()).displayView(1);
                break;
            case R.id.cd_granite:
                pref.set(Constants.cat_id,"2");
                pref.commit();
                ((DashBoard)getContext()).displayView(2);
                break;
            case R.id.cd_marbles:
                pref.set(Constants.cat_id,"3");
                pref.commit();
                ((DashBoard)getContext()).displayView(3);
                break;
            case R.id.cd_sanitary:
                pref.set(Constants.cat_id,"4");
                pref.commit();
                ((DashBoard)getContext()).displayView(4);
                break;
            case R.id.cd_cp:
                pref.set(Constants.cat_id,"5");
                pref.commit();
                ((DashBoard)getContext()).displayView(5);
                break;
        }

    }
}