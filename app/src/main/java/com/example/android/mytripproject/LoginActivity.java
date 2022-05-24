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
/*
public class LoginActivity extends AppCompatActivity {
    Button Signup_btn;
    Button ForgetBtn;
    Button Login_btn;
    EditText EmailLogin,PasswordLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();

    }
    private void initView(){
        EmailLogin= (EditText) findViewById(R.id.Email);
        PasswordLogin=(EditText) findViewById(R.id.Password);


        Signup_btn= (Button)findViewById(R.id.Signup_btn);
        ForgetBtn=(Button) findViewById(R.id.ForgetBtn);
        Login_btn=(Button) findViewById(R.id.Login_btn);
    }
    public String forgotPass(String name,String mail,String password){
        String message =getString(R.string.hi)+" "+name;
        message = message + "\n"+getString(R.string.mailmsg)+" "+ mail;
        message= message +"\n" +getString(R.string.passmsg)+ password + "\n"+ getString(R.string.thanks);
        return message;
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
                String login_email=EmailLogin.getText().toString();
                String register_login=RegisterActivity.sharedpreferences.getString("EMAIL","Email is not available");
                String register_name=RegisterActivity.sharedpreferences.getString("NAME","name is not available");
                String register_pass=RegisterActivity.sharedpreferences.getString("PASSWORD","Password is incorrect");
                if(!login_email.equals(register_login) ){
                    Toast.makeText(LoginActivity.this, "lemail famech f data base", Toast.LENGTH_SHORT).show();
                }
                else{
                  //
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, register_login);
                    intent.putExtra(Intent.EXTRA_CC, "mytrippass@gmail.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "MyTripPass FORGOT PASSWORD : "+register_name);
                    intent.putExtra(Intent.EXTRA_TEXT, forgotPass(register_name,login_email,register_pass));
                    //startActivity(Intent.createChooser(intent, "Ab3ith Email"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
                }


        });




        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_email=EmailLogin.getText().toString();
                String login_password=PasswordLogin.getText().toString();

                //Comparing email & password inserted by user while sign up and login
                //if same,navigate to home screen.If not,Display the message.
                String register_login=RegisterActivity.sharedpreferences.getString("EMAIL","Email is not available");
                String register_pass=RegisterActivity.sharedpreferences.getString("PASSWORD","Password is incorrect");

                if(!login_email.equals(register_login) && !login_password.equals(register_pass)) {

                    Toast.makeText(LoginActivity.this, "Please Enter Correct details", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent x2 = new Intent(LoginActivity.this, AcceuilActivity.class);
                    startActivity(x2);
                    Toast.makeText(LoginActivity.this, "DONEEEEEEEEE", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}*/


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
                // Intent Explicite
                Intent x1 = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(x1);

            }

        });





        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = EmailLogin.getText().toString();
                String password = PasswordLogin.getText().toString();
                //    SharedPreferences.Editor editor = sharedpreferences.edit();

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
                  /* editor.putString("NAME", username);
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