package com.crTech.prakashmarble.ui.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.crTech.prakashmarble.R;
import com.crTech.prakashmarble.ui.Activity.ui.User.UserModel.BookingHistoryModel;
import com.crTech.prakashmarble.ui.Activity.ui.home.ShopsModel.ShopsListModel;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<BookingHistoryModel> alist;
    private final int limit = 4;
    private int gallery_image_id;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView tv_name,tv_view,tv_date;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.histroryname);
            tv_view = (TextView)itemView.findViewById(R.id.histroryprice);
            tv_date = (TextView)itemView.findViewById(R.id.histrorydate);
        }
    }

    public HistoryAdapter(Context context, ArrayList<BookingHistoryModel> alist) {
        this.context = context;
        this.alist = alist;
    }

    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.orderhistoryadapter, viewGroup, false);

        return new HistoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_name.setText(alist.get(i).getProd_name());
        myViewHolder.tv_view.setText(alist.get(i).getPaid_amount());
        myViewHolder.tv_date.setText(alist.get(i).getPurchased_date());
    }
    @Override
    public int getItemCount() {
        return alist.size();
    }
}