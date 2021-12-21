package com.example.nearby.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearby.Client.ClientHomePage;
import com.example.nearby.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    private TextView btn;
    private EditText mail,pass;
    private FirebaseAuth mAuth;
    private TextView signup;
    private ProgressBar progressBar;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mail=findViewById(R.id.username_input);
        pass=findViewById(R.id.pass);
        btn=findViewById(R.id.loginbtn);
        progressBar=findViewById(R.id.progressbar);
        mAuth=FirebaseAuth.getInstance();
        progressBar.setVisibility(View.INVISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        signup=findViewById(R.id.signuptv);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
    private void userLogin(){
        String email=mail.getText().toString().trim();
        String password =pass.getText().toString().trim();
        if(email.isEmpty()){
            mail.setError("Email is required");
            mail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mail.setError("Please enter a valid email");
            mail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            pass.setError("Email is required");
            pass.requestFocus();
            return;
        }
        if(password.length()<6){
            pass.setError("Minimum password length is 6 characters");
            pass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        Intent i=new Intent(getApplicationContext(), ClientHomePage.class);
                        startActivity(i);
                        progressBar.setVisibility(View.GONE);
                    }else{
                        Toast.makeText(LogIn.this,"Verify your email!",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }


                }else{
                    Toast.makeText(LogIn.this,"Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}