package com.example.int_systems.busetaxi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RiderRegistration extends AppCompatActivity implements View.OnClickListener {
    Button buttonSignup;
    TextView textViewlogin;
    EditText etpassword, etemail,etphonenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_registration);

        buttonSignup =(Button) findViewById(R.id.btnCreatAcc);
        textViewlogin =(TextView) findViewById(R.id.textViewSignin);

        etpassword = (EditText) findViewById(R.id.password);
        etemail =(EditText) findViewById(R.id.email);
        etphonenumber =(EditText) findViewById(R.id.phone);

        buttonSignup.setOnClickListener(this);
        textViewlogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if(view == buttonSignup){

            registerUser();
        }
        if(view == textViewlogin){
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, RiderLogin.class));
        }
    }

    private void registerUser() {
        final String email = etemail.getText().toString();
        final String password = etpassword.getText().toString();
        final String phoneNumber = etphonenumber.getText().toString();

        final ProgressDialog progressDialog= new ProgressDialog(RiderRegistration.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(true);

        progressDialog.show();

        if (email.contentEquals("") || password.contentEquals("") || phoneNumber.contentEquals("")){
            Toast.makeText(RiderRegistration.this,"Fill in all the Feilds", Toast.LENGTH_LONG).show();
            progressDialog.hide();


        }
        else {



        }

    }
}

