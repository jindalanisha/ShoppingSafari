package com.example.shoppingsafari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class product extends AppCompatActivity {

    String category,subcategory;
    GridView gv;
    product_adapter adapter;
    public MyDBHandler dbHandler;
    Button viewcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Bundle b = getIntent().getExtras();
        category = b.getString("category");
        subcategory=b.getString("subcategory");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = firebaseDatabase.getReference("Products/"+category+"/"+subcategory);
        dbHandler = new MyDBHandler(this, null, null, 1);


        viewcart=(Button)findViewById(R.id.viewcart);


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
        ArrayList<product_model> all=new ArrayList<>();
        String z;
        for(Map.Entry<String,Object> entry : value.entrySet()){
            Map single_product=(Map)entry.getValue();
            String u=entry.getKey().toString();
            String v=single_product.get("Price").toString();
            z=single_product.get("Shelf").toString();
            Log.v("hey",u);
            product_model temp=new product_model();
            temp.setTitle(u);
            temp.setPrice(v);
            temp.setShelf(z);

            all.add(temp); // Add Data into List
        }
        gv= (GridView) findViewById(R.id.gridview1);
        adapter=new product_adapter(this,all);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                product_model temp=(product_model)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),temp.title,Toast.LENGTH_LONG).show();
                call(category,subcategory,temp.title,temp.shelf);

            }
        });

    }
    public void call(String category,String subcategory,String productname,String shelf)
    {
        myproduct product = new myproduct(category+"/"+subcategory+"/"+productname,Integer.parseInt(shelf));
        dbHandler.addProduct(product);
        Toast.makeText(getApplicationContext(),"Added to Cart", Toast.LENGTH_SHORT).show();
    }
    public void view_cart(View view)
    {
        String data = dbHandler.findData();
        Intent intent = new Intent(getApplicationContext(), view_cart.class);
        intent.putExtra("data", data);
        startActivity(intent);
    }
    public void taketour(View view)
    {
        Intent intent = new Intent(getApplicationContext(), tour.class);
        ArrayList<String>data1=new ArrayList<>();
        data1=dbHandler.findshelves();

        List<Integer>temp=new ArrayList<>();
        for(int i=0;i<data1.size();i++)
            temp.add(Integer.parseInt(data1.get(i)));

        dijkastra t = new dijkastra();
        ArrayList<Integer>d=new ArrayList<>();
        d=t.dijkstra(temp, 0);

        String data2[]=new String[data1.size()];
        data2=data1.toArray(data2);
        intent.putExtra("array",data2);
        startActivity(intent);
    }
    }

