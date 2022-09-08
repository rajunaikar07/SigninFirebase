package com.example.signinfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText nameedt,lastnameedt,mailedt,password;
    Button signupbtn;
    Button loginbtn;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameedt=findViewById(R.id.nameedt1);
        lastnameedt=findViewById(R.id.lastnedt1);
        mailedt=findViewById(R.id.numbmailedt1);
        password=findViewById(R.id.passwordedt1);

        signupbtn=findViewById(R.id.signup1);
        loginbtn=findViewById(R.id.logout);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performauth();
            }
        });
//        loginbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,LogiActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    private void performauth() {
        String  email=mailedt.getText().toString();
        String pass=password.getText().toString();

        if (!email.matches(emailPattern)){
           mailedt.setError("Enter Valid Mail");
        }
        else if (pass.isEmpty()||pass.length()<6){
            password.setError("Enter Valid Password");
        }

        else{
            progressDialog.setMessage(" Pleace Wait Registration.....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(MainActivity.this, "Registaration Succeful", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this,LogiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void loginpage(View view) {
        Intent intent=new Intent(MainActivity.this,LogiActivity.class);
        startActivity(intent);
    }
}