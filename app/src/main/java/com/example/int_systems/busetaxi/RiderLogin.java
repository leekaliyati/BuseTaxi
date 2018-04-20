package com.example.int_systems.busetaxi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

import org.w3c.dom.Text;

public class RiderLogin extends AppCompatActivity implements View.OnClickListener {
    Button loginbtn;
    TextView accountReg;
    EditText etEmail, etPassword;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_login);
        etEmail = (EditText) findViewById(R.id.etemail);
        etPassword = (EditText) findViewById(R.id.etpassword);
        accountReg = (TextView) findViewById(R.id.textViewReg);
        loginbtn = (Button) findViewById(R.id.btnLogin);

        accountReg.setOnClickListener(this);
        loginbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == accountReg) {
            Intent intent = new Intent(RiderLogin.this,RiderRegistration.class);
            startActivity(intent);
        }
        if (view == loginbtn) {
            Riderlogin();
        }

    }

    private void Riderlogin() {
        final ProgressDialog progressDialog = new ProgressDialog(RiderLogin.this);

        progressDialog.setTitle("Signing in");
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(true);
        progressDialog.show();


        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.contentEquals("") || password.contentEquals("")) {
            progressDialog.dismiss();
            Toast.makeText(RiderLogin.this, "Fill in all the Feilds", Toast.LENGTH_LONG).show();


        } else {
            //Initialising progress dialog
            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("email", email);
            params.put("password", password);

            client.post("http://10.0.2.2/BuseTaxi/riderLogin/login.php", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    progressDialog.dismiss();
                    Toast.makeText(RiderLogin.this, "Login Failed check your connectivity", Toast.LENGTH_LONG);

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String result) {

                    progressDialog.dismiss();
                    if (result.equalsIgnoreCase("true")) {
                        Toast.makeText(RiderLogin.this, "Login successfull", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RiderLogin.this, MainActivity.class);
                        RiderLogin.this.startActivity(intent);

                    } else // If username and password does not match display a error message
                        if (result.equalsIgnoreCase("false"))
                            Toast.makeText(RiderLogin.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                        else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                            Toast.makeText(RiderLogin.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

                        }

                }
            });
        }
    }
}




