package com.example.shoppingsafari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

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
            if(u.equals("Festive Season"))
                temp.setImage(R.drawable.festiveseason);
            else if(u.equals("Food"))
                temp.setImage(R.drawable.food);
            else if(u.equals("Home Care"))
                temp.setImage(R.drawable.homecare);
            else if(u.equals("Personal Care"))
                temp.setImage(R.drawable.personalcare);
            else if(u.equals("Non Food"))
                temp.setImage(R.drawable.nonfood);



            all.add(temp); // Add Data into List
        }
        gv= (GridView) findViewById(R.id.gridview);
        adapter=new category_adapter(this,all);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category_model temp=(category_model)parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(),temp.title,Toast.LENGTH_LONG).show();
                call(temp.title);

            }
        });
    }
    public void call(String title)
    {
        Intent i=new Intent(this,subCategory.class);
        i.putExtra("category",title);
        startActivity(i);
    }




}
