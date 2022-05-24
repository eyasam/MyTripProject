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

public class RegisterActivity extends AppCompatActivity {
    private EditText FullName;
    private EditText Email;
    private EditText PhoneNumber;
    private EditText Password;
    private EditText Confirm_password;
    ProgressDialog progressDialog;

    Button Submit_btn;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String e1 = "emailKey";
    public static final String p1 = "passKey";
    public static final String phone1 = "phoneKey";
    public static final String name1 = "nameKey";
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    // public static SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        //sharedpreferences = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        initEvent();

    }
    private void initView(){

        FullName=findViewById(R.id.FullName);
        Email=findViewById(R.id.Email);
        PhoneNumber=findViewById(R.id.PhoneNumber);
        Password=findViewById(R.id.Password);
        Confirm_password=findViewById(R.id.Confirm_password);
        mAuth= FirebaseAuth.getInstance();
        Submit_btn=findViewById(R.id.Submit_btn);
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
    }



    public void initEvent() {


        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = FullName.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String confirmPassword = Confirm_password.getText().toString();
                String phonenumber = PhoneNumber.getText().toString();
            //    SharedPreferences.Editor editor = sharedpreferences.edit();

                if (username.isEmpty()) {
                    showError(FullName, "Your username is not valid!");
                } else if (email.isEmpty() || !email.contains("@")) {
                    showError(Email, "Your email is not valid!");
                } else if (phonenumber.isEmpty()) {
                    showError(Password, "Your number is not valid !");
                } else if (password.isEmpty() || password.length() < 7) {
                    showError(Password, "Your password must be have at least 8 characters long");
                } else if (confirmPassword.isEmpty() || !confirmPassword.equals(password)) {
                    showError(Confirm_password, "Password confirmation doesn't match Password");
                } else {
                    progressDialog.setMessage("Please wait while registration");
                    progressDialog.setMessage("Registration");
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Intent x = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(x);
                                Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        }  });
                  /*  editor.putString("NAME", username);
                    editor.putString("EMAIL", email);
                    editor.putString("phone1", phonenumber);
                    editor.putString("PASSWORD", password);
                    editor.commit();*/
                    //Intent x = new Intent(RegisterActivity.this, LoginActivity.class);
                    //  Toast.makeText(RegisterActivity.this, "thanks for you registration", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(RegisterActivity.this,"Thanks for Your Sign UP",Toast.LENGTH_LONG).show ();
                   // startActivity(x);
                    }

            }
        });
    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}


























/*
    private void checkCrededentials() {
        String username=FullName.getText().toString();
        String email=Email.getText().toString();
        String password=Password.getText().toString();
        String confirmPassword=Confirm_password.getText().toString();

        if (username.isEmpty() || username.length()<7 )
        {
            showError(FullName,"Your username is not valid!");
        }
        else if (email.isEmpty() || !email.contains("@"))
        {
            showError(Email,"Your email is not valid!");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(Password,"Your password must be have at least 8 characters long");
        }
        else if (confirmPassword.isEmpty() || confirmPassword.equals(password))
        {
            showError(Confirm_password,"Password confirmation doesn't match Password");
        }


    }


    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }



*/