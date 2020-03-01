package com.example.shoppingsafari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class view_cart extends AppCompatActivity {
    private TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        Intent i = getIntent();
        //   empty = (Button)findViewById(R.id.empty_cart);
        String data = i.getStringExtra("data");
        details = (TextView)findViewById(R.id.items);
        details.setText(data);
    }
}
