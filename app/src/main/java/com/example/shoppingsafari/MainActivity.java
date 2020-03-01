package com.example.shoppingsafari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button b;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        dbHandler = new MyDBHandler(this, null, null, 1);

        b = (Button) findViewById (R.id.Login);
        Button btn = (Button) findViewById (R.id.Logout);
        b.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                Intent hintent = new Intent (getApplicationContext ( ), login.class);
                startActivity (hintent);
            }
        });
        btn.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance ( ).signOut ( );
                startActivity (new Intent (MainActivity.this, login.class)); //Go back to home page
                finish ( );
            }
        });
    }
    @Override
    public void onDestroy()
    {
        dbHandler.deleteProduct();
        super.onDestroy();
    }
}
