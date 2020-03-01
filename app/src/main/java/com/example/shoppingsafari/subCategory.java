package com.example.shoppingsafari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class subCategory extends AppCompatActivity {

    category_adapter adapter;
    GridView gv;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);


        Bundle b = getIntent().getExtras();
        category = b.getString("category");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("Products/"+category);

        dbRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {

                collectiouser((Map<String,Object>)dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void collectiouser(Map<String, Object> value) {
        ArrayList<category_model> all=new ArrayList<>();
        for(Map.Entry<String,Object> entry : value.entrySet()){
            Map single_category=(Map)entry.getValue();
            String u=entry.getKey().toString();
            Log.v("hey",u);
            category_model temp=new category_model();
            temp.setTitle(u);
            if(u.equals("Diwali"))
                temp.setImage(R.drawable.diwali);
            else if(u.equals("Holi"))
                temp.setImage(R.drawable.holi);
            else if(u.equals("Kitchenware"))
                temp.setImage(R.drawable.kitchenware);
            else if(u.equals("Beverages"))
                temp.setImage(R.drawable.beverages);
            else if(u.equals("Dairy Products"))
                temp.setImage(R.drawable.dairyproducts);
            if(u.equals("Dry Fruits"))
                temp.setImage(R.drawable.dryfruits);
            else if(u.equals("Fruits"))
                temp.setImage(R.drawable.fruits);
            else if(u.equals("Pulses"))
                temp.setImage(R.drawable.pulses);
            else if(u.equals("Vegetables"))
                temp.setImage(R.drawable.vegetables);
            else if(u.equals("Accessories"))
                temp.setImage(R.drawable.accessories);
            else if(u.equals("Stationary"))
                temp.setImage(R.drawable.stationary);
            else if(u.equals("Body Care"))
                temp.setImage(R.drawable.bodycare);
            else if(u.equals("Oral Care"))
                temp.setImage(R.drawable.oralcare);





            all.add(temp); // Add Data into List
        }
        gv= (GridView) findViewById(R.id.gridview1);
        adapter=new category_adapter(this,all);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                category_model temp=(category_model)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),temp.title,Toast.LENGTH_LONG).show();
                call(category,temp.title);

            }
        });

    }
    public void call(String category,String subcategory)
    {
        Intent i=new Intent(this,product.class);
        i.putExtra("category",category);
        i.putExtra("subcategory",subcategory);
        startActivity(i);
    }
}
