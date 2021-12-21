package com.example.nearby.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearby.Client.ClientHomePage;
import com.example.nearby.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    private EditText dob, fname, lname, phone, email, password, address, pin;
    private TextView logintv, signup;
    private FirebaseAuth mAuth;
    private String userId;
    private RadioButton male, female;
    private ProgressBar progressBar;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        dob = findViewById(R.id.dob);
        logintv = findViewById(R.id.logintv);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        phone = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        pin = findViewById(R.id.pincode);
        signup = findViewById(R.id.signupbtn);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        progressBar = findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();
        dialog=new Dialog(this);

        progressBar.setVisibility(View.INVISIBLE);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignUp.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        logintv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, ClientHomePage.class);
                startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));
    }

    private void registeruser() {
        String firstname = fname.getText().toString().trim();
        String lastname = lname.getText().toString().trim();
        String dateofbirth = dob.getText().toString().trim();
        String phonenumber = phone.getText().toString().trim();
        String e_mail = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String address1 = address.getText().toString().trim();
        String pincode = pin.getText().toString().trim();
        String gender = "";
        if (male.isChecked()) {
            gender = "male";
        } else if (female.isChecked()) {
            gender = "female";
        }
        String gender1 = gender;

        if (firstname.isEmpty()) {
            fname.setError("First name required!");
            fname.requestFocus();
            return;
        }
        if (lastname.isEmpty()) {
            lname.setError("Last name required!");
            lname.requestFocus();
            return;
        }
        if (phonenumber.isEmpty()) {
            phone.setError("Phone number required!");
            phone.requestFocus();
            return;
        }
        if (dateofbirth.isEmpty()) {
            dob.setError("Date of birth required!");
            dob.requestFocus();
            return;
        }
        if (e_mail.isEmpty()) {
            email.setError("Email required!");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()) {
            email.setError("Please provide valid email!");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            password.setError("Password required!");
            password.requestFocus();
            return;
        }
        if (pass.length() < 6) {

            password.setError("Min password length should be 6 characters!");
            password.requestFocus();
            return;
        }

        if (address1.isEmpty()) {
            address.setError("Address required!");
            address.requestFocus();
            return;
        }
        if (pincode.isEmpty()) {
            pin.setError("Last name required!");
            pin.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(e_mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserBean User = new UserBean(firstname, lastname, e_mail, dateofbirth, phonenumber, address1, pincode, gender1,mAuth.getUid());
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUp.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                        opendialog();
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        user.sendEmailVerification();
                                    } else {
                                        Toast.makeText(SignUp.this, "Failed to create account, Try again", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }

    private void opendialog() {
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialoglayout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView okbtn=dialog.findViewById(R.id.okbtn);
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}