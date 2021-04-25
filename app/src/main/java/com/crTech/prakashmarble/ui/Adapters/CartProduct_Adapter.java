package com.crTech.prakashmarble.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.Cart.CartModel.MycartDataModel;

import java.util.ArrayList;

public class CartProduct_Adapter extends RecyclerView.Adapter<CartProduct_Adapter.MyViewHolder> {

    Context context;
    ArrayList<MycartDataModel> alist;
    private int gallery_image_id;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cd_bloc;
        ImageView iv_image,iv_delete;
        TextView tv_name,tv_dim,tv_price,tv_total;
        ElegantNumberButton btn_qty;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.prd_name);
            cd_bloc = (CardView)itemView.findViewById(R.id.cdblock);
            iv_image = (ImageView)itemView.findViewById(R.id.img_prod);
            iv_delete = (ImageView)itemView.findViewById(R.id.prd_delet);
            btn_qty = (ElegantNumberButton)itemView.findViewById(R.id.qty);
            tv_dim = (TextView)itemView.findViewById(R.id.prd_dim);
            tv_price = (TextView)itemView.findViewById(R.id.prd_mrp);
            tv_total = (TextView)itemView.findViewById(R.id.tv_resultprice);

        }
    }

    public CartProduct_Adapter(Context context, ArrayList<MycartDataModel> alist) {
        this.context = context;
        this.alist = alist;
    }

    @Override
    public CartProduct_Adapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartadapter, viewGroup, false);

        return new CartProduct_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartProduct_Adapter.MyViewHolder myViewHolder, final int i) {

        myViewHolder.tv_name.setText(alist.get(i).getProd_name());
        Glide.with(context).load(alist.get(i).getImage()).placeholder(R.drawable.no_image).into(myViewHolder.iv_image);
        myViewHolder.tv_price.setText("1 X ₹"+alist.get(i).getPrice());
        myViewHolder.btn_qty.setNumber(alist.get(i).getQuantity());
        myViewHolder.tv_total.setText("₹ "+alist.get(i).getTotal_price());
        myViewHolder.btn_qty.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = myViewHolder.btn_qty.getNumber();
                int res = Integer.parseInt(num) * Integer.parseInt(alist.get(i).getPrice());
                String data = String.valueOf(res);
                myViewHolder.tv_total.setText("₹ "+data);

            }
        });
        myViewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {


        return alist.size();

    }
}