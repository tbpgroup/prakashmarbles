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
import com.crTech.prakashmarble.ui.Activity.ui.User.DashBoard;
import com.crTech.prakashmarble.ui.Activity.ui.Product.ProductModel.ProductDataModel;
import com.crTech.prakashmarble.ui.Common.Constants;
import com.crTech.prakashmarble.ui.Common.Preferences;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    Context context;
    ArrayList<ProductDataModel> alist;
    private final int limit = 4;
    private int gallery_image_id;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        RelativeLayout lv_block;
        CardView cd_bloc;
        ImageView iv_image,iv_more;
        TextView tv_name,tv_view,tv_mrp,tv_price,tv_brand;

        public MyViewHolder(View itemView) {
            super(itemView);
            lv_block = (RelativeLayout) itemView.findViewById(R.id.block);
            tv_name = (TextView) itemView.findViewById(R.id.venue_id);
            cd_bloc = (CardView)itemView.findViewById(R.id.cdblock);
            iv_image = (ImageView)itemView.findViewById(R.id.venue_image);
            tv_view = (TextView)itemView.findViewById(R.id.tv_size);
            tv_mrp = (TextView)itemView.findViewById(R.id.mrptext);
            tv_price = (TextView)itemView.findViewById(R.id.price);
            tv_brand = (TextView)itemView.findViewById(R.id.product);
        }
    }

    public ProductListAdapter(Context context, ArrayList<ProductDataModel> alist) {
        this.context = context;
        this.alist = alist;
    }

    @Override
    public ProductListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.productlist_adapter, viewGroup, false);

        return new ProductListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_name.setText(alist.get(i).getProd_name());
        Glide.with(context).load(alist.get(i).getImage()).placeholder(R.drawable.no_image).into(myViewHolder.iv_image);
        myViewHolder.tv_view.setText(alist.get(i).getCategory_name());
        myViewHolder.tv_brand.setText(alist.get(i).getBrand());
        myViewHolder.tv_mrp.setText(alist.get(i).getMrp());
        myViewHolder.tv_mrp.setPaintFlags(myViewHolder.tv_mrp.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.tv_price.setText(alist.get(i).getPrice());
        myViewHolder.lv_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences pref = new Preferences(context);
                pref.set(Constants.prod_id,alist.get(i).getProdID());
                pref.commit();
                ((DashBoard)context).displayView(7);
            }
        });
    }
    @Override
    public int getItemCount() {
            return alist.size();
    }
}