package com.example.shoppingsafari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppingsafari.R;

import java.util.ArrayList;

public class category_adapter extends BaseAdapter {

    Context c;
    ArrayList<category_model> categories;

    public category_adapter(Context c, ArrayList<category_model> categories) {
        this.c = c;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.category,viewGroup,false);
        }

        final category_model s= (category_model) this.getItem(i);

        ImageView img= (ImageView) view.findViewById(R.id.icon);
        TextView titleTxt= (TextView) view.findViewById(R.id.title);


        titleTxt.setText(s.getTitle());
        img.setImageResource(s.getImage());
        return view;
    }
}
