package com.crTech.prakashmarble.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.DashBoard;
import com.crTech.prakashmarble.ui.Activity.ui.Category.CategoryModel.CategoryDataModel;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.MyViewHolder> {

    Context context;
    ArrayList<CategoryDataModel> alist;
    private int gallery_image_id;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        RelativeLayout lv_block;
        CardView cd_bloc;
        ImageView iv_image,iv_more;
        TextView tv_name,tv_view;

        public MyViewHolder(View itemView) {
            super(itemView);
            lv_block = (RelativeLayout) itemView.findViewById(R.id.block);
            tv_name = (TextView) itemView.findViewById(R.id.venue_id);
            cd_bloc = (CardView)itemView.findViewById(R.id.cdblock);
            iv_image = (ImageView)itemView.findViewById(R.id.venue_image);
        }
    }

    public Category_Adapter(Context context, ArrayList<CategoryDataModel> alist) {
        this.context = context;
        this.alist = alist;
    }

    @Override
    public Category_Adapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_adapter, viewGroup, false);

        return new Category_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Category_Adapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_name.setText(alist.get(i).getProduct_type_name());
        Glide.with(context).load(R.drawable.no_image).placeholder(R.drawable.no_image).into(myViewHolder.iv_image);

        myViewHolder.lv_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences preferences  = new Preferences(context);
                preferences.set(Constants.sub_cat_id,alist.get(i).getProductTypeID());
                preferences.commit();
                ((DashBoard)context).displayView(6);
            }
        });
    }
    @Override
    public int getItemCount() {


            return alist.size();

    }
}