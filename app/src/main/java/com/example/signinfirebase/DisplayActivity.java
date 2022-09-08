package com.example.signinfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;

public class DisplayActivity extends AppCompatActivity {
    Button logoutbtn;

//    FirebaseAuth firebaseAuth;
//    ProgressDialog progressDialog;
//    GoogleSignInClient mGoogleSignInClient;
//    GoogleSignInAccount googleSignInAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        logoutbtn=findViewById(R.id.logout);


        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DisplayActivity.this,LogiActivity.class);
                startActivity(intent);

            }
        });

    }
}