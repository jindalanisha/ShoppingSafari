package com.example.shoppingsafari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class tour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        int id[]={R.id.no1,R.id.no2,R.id.no3,R.id.no4,R.id.no5,R.id.no6,R.id.no7,
                R.id.no8,R.id.no9,R.id.no10,R.id.no11,R.id.no12,R.id.no13,R.id.no14,R.id.no15,R.id.no16,
                R.id.no17,R.id.no18,R.id.no19,R.id.no20,R.id.no21,R.id.no22,R.id.no23,R.id.no24,R.id.no25};
        Bundle b = getIntent().getExtras();
        String [] arr = b.getStringArray("array");


        for(int i=0;i<id.length;i++)
        {
            ImageView im=findViewById(id[i]);
            im.setImageDrawable(null);
        }

        if(arr!=null)
        {
        for(int i=0;i<arr.length;i++)
        {
            int shelf=Integer.parseInt(arr[i])-1;
            ImageView im=findViewById(id[shelf]);
            if(i==0)
            im.setImageResource(R.drawable.num1);
            else if(i==1)
                im.setImageResource(R.drawable.num2);
        }
    }}
}
