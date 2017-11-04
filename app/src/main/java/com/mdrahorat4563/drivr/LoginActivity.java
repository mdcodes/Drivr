/*
* Name: Michal Drahorat
* Description: The LoginActivity class for the Drivr app.
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mdrahorat4563.drivr.Models.LoginModel;

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
    private EditText userName;
    private EditText passwordInput;
    private LoginModel lm;
    private DrivrDBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initObjects();

        // Set up the login form
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
                    userName = (EditText) findViewById(R.id.usernameInput);
                    passwordInput = (EditText) findViewById(R.id.passwordInput);
                    if(attemptLogin()) {
                        if (dbh.checkUser(userName.getText().toString().trim().toUpperCase(),
                                passwordInput.getText().toString().trim())) {
                            success.show();

                            SaveSharedPreference.setUserNameAndPassword(getApplicationContext(),
                                    userName.getText().toString().trim().toUpperCase(),
                                    passwordInput.getText().toString().trim());
                            Intent intent = new Intent(LoginActivity.this, ForumActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            failure.show();
                        }
                    }
                }
                catch(Exception e){
                    Log.e("1", e.getMessage());
                }
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.email_register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }

    /**
     * Performs the validations for the initial login
     * @return
     */
    /*TODO: Remove hard-coded login, swap for database values*/
    public boolean attemptLogin() {
        try {
            userName = (EditText) findViewById(R.id.usernameInput);
            passwordInput = (EditText) findViewById(R.id.passwordInput);

            if (userName.getText().toString().trim().isEmpty()
                    || userName.getText().toString() == null){
                userName.setError("Username cannot be empty.");
                return false;
            }
            if (passwordInput.getText().toString().trim().isEmpty()
                    || passwordInput.getText().toString() == null){
                passwordInput.setError("Password cannot be null");
                return false;
            }
        }
        catch(Exception e){
            Log.e("2", "Exception occured on attemptLogin()", e);
        }
        return true;
    }

    public void initObjects(){
        lm = new LoginModel();
        dbh = new DrivrDBHelper(getApplicationContext());
    }
}

