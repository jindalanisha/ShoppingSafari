package com.example.shoppingsafari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Map;

public class homepage extends AppCompatActivity {

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};

    category_adapter adapter;
    GridView gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("Products");

        dbRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                collectiouser((Map<String,Object>)dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void collectiouser(Map<String, Object> value) {
        ArrayList<category_model> all=new ArrayList<>();
        for(Map.Entry<String,Object> entry : value.entrySet()){
            Map single_category=(Map)entry.getValue();
            String u=entry.getKey().toString();
            Log.v("hey",u);
            category_model temp=new category_model();
            temp.setTitle(u);
            if(u.equals("Beverages"))
                temp.setImage(R.drawable.beverages);
            else if(u.equals("Vegetables"))
                temp.setImage(R.drawable.vegetables);
            else if(u.equals("Stationary"))
                temp.setImage(R.drawable.stationary);
            else if(u.equals("Personal Care"))
                temp.setImage(R.drawable.personalcare);
            else if(u.equals("KitchenWare"))
                temp.setImage(R.drawable.kitchenware);
            else if(u.equals("Home Care"))
                temp.setImage(R.drawable.homecare);
            else if(u.equals("Health Care"))
                temp.setImage(R.drawable.healthcare);
            else if(u.equals("Grocery"))
                temp.setImage(R.drawable.grocery);
            else if(u.equals("Fruits"))
                temp.setImage(R.drawable.fruits);
            else if(u.equals("Eatables"))
                temp.setImage(R.drawable.eatables);
            else if(u.equals("Dairy Products"))
                temp.setImage(R.drawable.dairyproducts);


            all.add(temp); // Add Data into List
        }
        gv= (GridView) findViewById(R.id.gridview);
        adapter=new category_adapter(this,all);
        gv.setAdapter(adapter);
    }




}
