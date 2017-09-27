/*
* Name: Michal Drahorat
* Description: The LoginActivity class for the Drivr app.
*
* Revision History:
* */

package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    public String dummyEmail = "abc@123.com";
    public String dummyPassword = "abc123";
    public String validToast = "Welcome back!";
    public String invalidToast = "Please check your login details.";
    final int duration = Toast.LENGTH_SHORT;
    // UI references.
    private EditText emailInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form
        final Context context = getApplicationContext();
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        passwordInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Context context = getApplicationContext();
                    Toast failure = Toast.makeText(context, invalidToast, duration);
                    Toast success = Toast.makeText(context, validToast, duration);
                    emailInput = (EditText) findViewById(R.id.emailInput);
                    passwordInput = (EditText) findViewById(R.id.passwordInput);

                    if (emailInput.getText() == null
                            || emailInput.getText().toString().equals("")
                            || passwordInput.getText() == null
                            || passwordInput.getText().toString().equals("")) {
                        failure.show();
                    } else if (attemptLogin()) {
                        success.show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch(Exception e){
                    Log.e("1", "Exception occured on the login");
                }
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.email_register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean attemptLogin() {
        try {
            Context context = getApplicationContext();
            emailInput = (EditText) findViewById(R.id.emailInput);
            passwordInput = (EditText) findViewById(R.id.passwordInput);

            if (emailInput.getText().toString().equals(dummyEmail)
                    && passwordInput.getText().toString().equals(dummyPassword)) {
                Toast success = Toast.makeText(context, validToast, duration);
                success.show();

                return true;
            }
        }
        catch(Exception e){
            Log.e("2", "Exception occured on attemptLogin()", e);
        }
        return false;
    }
}

