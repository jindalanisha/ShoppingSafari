package com.example.shoppingsafari;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView tv_log,tv_reg;
    Button btn_login;
    EditText et_email_login,et_pas_login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    //    CheckBox cb_remember;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity here
            finish();
            startActivity(new Intent (getApplicationContext(),homepage.class));
        }
//
        tv_log=(TextView) findViewById(R.id.tv_log);
        tv_reg=(TextView) findViewById(R.id.tv);
        btn_login=(Button) findViewById(R.id.btn_login);
        et_email_login=(EditText) findViewById(R.id.et_email_login);
        et_pas_login=(EditText) findViewById(R.id.et_pas_login);
        saveLoginCheckBox = (CheckBox)findViewById(R.id.cb_remember);
//        ------------------------------------

//        cb_remember=(CheckBox) findViewById(R.id.cb_remember);
//
//        cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(compoundButton.isChecked()){
//                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE );
//                    SharedPreferences.Editor editor=preferences.edit();
//                    editor.putString("remember","true");
//                    editor.apply();
//
//                }
//                else{
//                    SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE );
//                    SharedPreferences.Editor editor=preferences.edit();
//                    editor.putString("remember","false");
//                    editor.apply();
//
//                }
//
//
//
//            }
//        });

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            et_email_login.setText(loginPreferences.getString("username", ""));
            et_pas_login.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login_user();
            }
        });

        tv_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg=new Intent(getApplicationContext(),register.class);
                startActivity(reg);
            }
        });
    }
    private void login_user() {
        String email=et_email_login.getText().toString().trim();
        String password=et_pas_login.getText().toString().trim();
        if (saveLoginCheckBox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", email);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }

//              if email is empty do not proceed further

        if(TextUtils.isEmpty(email)){
            Toast.makeText(login.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }
//              if password is empty
        if(TextUtils.isEmpty(password)){
            Toast.makeText(login.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    if(firebaseAuth.getCurrentUser()!=null){
                        //profile activity here
                        finish();
                        startActivity(new Intent(getApplicationContext(),homepage.class));
                    }
//                                direct to the profile of user
//                                or display message of success

                    Toast.makeText(login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(login.this, "Could not Register", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

