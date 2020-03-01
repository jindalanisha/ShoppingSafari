package com.example.shoppingsafari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class product_adapter extends BaseAdapter {
    Context c;
    ArrayList<product_model> products;

    public product_adapter(Context c, ArrayList<product_model> products) {
        this.c = c;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.product,viewGroup,false);
        }

        final product_model s= (product_model) this.getItem(i);

        ImageView img= (ImageView) view.findViewById(R.id.icon);
        TextView titleTxt= (TextView) view.findViewById(R.id.title);
        TextView pricee=(TextView)view.findViewById(R.id.price);


        titleTxt.setText(s.getTitle());
        pricee.setText("Rs "+s.getPrice());
        img.setImageResource(s.getImage());
        return view;
    }
}
