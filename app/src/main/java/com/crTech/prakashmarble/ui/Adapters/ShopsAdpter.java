package com.crTech.prakashmarble.ui.Adapters;

import android.content.Context;
import android.graphics.Paint;
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
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.DetailsDataModel;
import com.crTech.prakashmarble.ui.Activity.ui.User.DashBoard;
import com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel.ShopsListModel;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;

import java.util.ArrayList;

public class ShopsAdpter extends RecyclerView.Adapter<ShopsAdpter.MyViewHolder> {

    Context context;
    ArrayList<ShopsListModel> alist;
    private final int limit = 4;
    private int gallery_image_id;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv_name,tv_view;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.shopname);
            tv_view = (TextView)itemView.findViewById(R.id.shopaddress);
        }
    }

    public ShopsAdpter(Context context, ArrayList<ShopsListModel> alist) {
        this.context = context;
        this.alist = alist;
    }

    @Override
    public ShopsAdpter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shoplocation_adapter, viewGroup, false);

        return new ShopsAdpter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ShopsAdpter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_name.setText(alist.get(i).getStore_name());
        myViewHolder.tv_view.setText(alist.get(i).getStore_address());
    }
    @Override
    public int getItemCount() {
        return alist.size();
    }
}