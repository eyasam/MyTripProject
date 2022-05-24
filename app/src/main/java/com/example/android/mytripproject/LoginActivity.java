package com.example.android.mytripproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button Signup_btn;
    Button ForgetBtn;
    Button Login_btn;
    EditText EmailLogin,PasswordLogin;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        initView();
        initEvent();


    }
    private void initView(){
        EmailLogin= (EditText) findViewById(R.id.Email);
        PasswordLogin=(EditText) findViewById(R.id.Password);
        progressDialog = new ProgressDialog(this);


        Signup_btn= (Button)findViewById(R.id.Signup_btn);
        ForgetBtn=(Button) findViewById(R.id.ForgetBtn);
        Login_btn=(Button) findViewById(R.id.Login_btn);
    }


    public void initEvent() {
        Signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent Explicite
                Intent x1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(x1);

            }

        });

        ForgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x1 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(x1);

            }

        });





        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EmailLogin.getText().toString();
                String password = PasswordLogin.getText().toString();

                if (email.isEmpty() || !email.contains("@")) {
                    showError(EmailLogin, "Your email is not valid!");
                }else if (password.isEmpty() || password.length() < 7) {
                    showError(PasswordLogin, "Your password is not valid!");
                }else {
                    progressDialog.setMessage("Please wait while Login");
                    progressDialog.setMessage("Login");
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Intent x = new Intent(LoginActivity.this, AcceuilActivity.class);
                                startActivity(x);
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            } else {

                                Toast.makeText(LoginActivity.this, "We didn't find an account with those credentials ", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        }  });

                }

            }
        });
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }



}