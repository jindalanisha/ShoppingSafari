package com.example.shoppingsafari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class view_cart extends AppCompatActivity {
//    private TextView details;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_cart);
//
//        Intent i = getIntent();
//        //   empty = (Button)findViewById(R.id.empty_cart);
//        String data = i.getStringExtra("data");
//        details = (TextView)findViewById(R.id.items);
//        details.setText(data);
//    }
private TextView details;
    private Button empty, checkout;
    public MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        dbHandler = new MyDBHandler(this, null, null, 1);

        Intent i = getIntent();
        empty = (Button)findViewById(R.id.emptycart);
        //checkout = (Button) findViewById (R.id.payment);
        String data = i.getStringExtra("data");
        details = (TextView)findViewById(R.id.items);
        details.setText(data);
        empty.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                details.setText("");
                dbHandler.deleteProduct();
            }
        });
//        checkout.setOnClickListener (new View.OnClickListener ( ) {
//            @Override
//            public void onClick(View v) {
//                Uri webpage = Uri.parse("https://paytm.com");
//                Intent payment = new Intent(Intent.ACTION_VIEW, webpage);
//
//            }
//        });
    }

}
