package com.example.signinfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogiActivity extends AppCompatActivity {
    EditText emailedt, passwordedt;
    Button loginbtn;
//    Button signup1;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_logi);
        emailedt = findViewById(R.id.mail_edt1);
        passwordedt = findViewById(R.id.pass_edt1);
        loginbtn = findViewById(R.id.login_btn1);
//        signup1=findViewById(R.id.signup1_btn);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();




            loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    performlogin();
                }
            });
//            signup1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(LogiActivity.this,MainActivity.class);
//                    startActivity(intent);
//
//                }
//            });
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Log.d("TAG", "Currently Signed in: " + currentUser.getEmail());
            Toast.makeText(LogiActivity.this, "Currently Logged in anther account please log out: " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
        }


    }


        private void performlogin() {
        String email = emailedt.getText().toString();
        String pass = passwordedt.getText().toString();

        if (!email.matches(emailPattern)) {
            emailedt.setError("Enter Valid Mail");
        } else if (pass.isEmpty() || pass.length() < 6) {
            passwordedt.setError("Enter Valid Password");

        } else {
            progressDialog.setMessage(" Pleace Wait while login.....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();


            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        SendUserToNextActivity();
                        Toast.makeText(LogiActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(LogiActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    private void SendUserToNextActivity() {
        Intent intent=new Intent(LogiActivity.this,DisplayActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null) {
            Toast.makeText(this, "alreay", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LogiActivity.this,MainActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "new user can login now", Toast.LENGTH_SHORT).show();
        }
    }
}