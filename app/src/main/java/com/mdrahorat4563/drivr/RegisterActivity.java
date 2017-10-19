/*
* Name: Michal Drahorat
* Description: The Register class for the Drivr app.
* */

package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{
    private EditText registerUsername;
    private EditText registerPassword;
    private EditText registerConfirmPassword;
    private Button registerButton;
    private final String validToastMessage = "Success! Welcome!";
    private final String invalidToastMessage = "User already exists. Go log in!";
    final int duration = Toast.LENGTH_SHORT;
    private LoginModel login;
    private DrivrDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerUsername = (EditText) findViewById(R.id.registerUsername);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerConfirmPassword = (EditText) findViewById(R.id.registerConfirmPassword);
        registerButton = (Button) findViewById(R.id.registerButton);

        initObjects();

         View.OnClickListener registerButtonListener = new View.OnClickListener(){
             Context context = getApplicationContext();
             Toast validToast = Toast.makeText(context, validToastMessage, duration);
             Toast invalidToast = Toast.makeText(context, invalidToastMessage, duration);
            public void onClick(View v){
                if (attemptRegister()) {


                    if (!helper.checkUser(registerUsername.getText().toString().trim())){
                        validToast.show();

                        String userName = registerUsername.getText().toString().trim().toUpperCase();
                        String password = registerPassword.getText().toString().trim().toUpperCase();
                        login.setUserName(userName);
                        login.setPassword(password);

                        helper.addLogin(login);

                        SaveSharedPreference.setUserNameAndPassword(context,
                                registerUsername.getText().toString().trim(),
                                registerPassword.getText().toString().trim());
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        invalidToast.show();
                    }


                }
            }
        };
        registerButton.setOnClickListener(registerButtonListener);
    }

    /**
     * Performs validations for the register activity
     * @return Either true or false
     */
    public boolean attemptRegister(){
        try {
            Boolean isFailed = false;
            if (registerUsername == null || registerUsername.getText().toString().equals("")) {
                registerUsername.setError("Email cannot be blank.");
                isFailed = true;
            }
            if (registerPassword == null || registerPassword.getText().toString().equals("")) {
                registerPassword.setError("Password cannot be blank.");
                isFailed = true;
            }
            if (registerConfirmPassword == null
                    || registerConfirmPassword.getText().toString().equals("")) {
                registerConfirmPassword.setError("Confirm password cannot be blank.");
                isFailed = true;
            } else if (registerConfirmPassword.getText().equals(registerPassword)) {
                registerConfirmPassword.setError("Passwords must match.");
                isFailed = true;
            }
            if (registerPassword.getText().toString().length() < 8 || registerConfirmPassword
                    .getText().toString().length() < 8){
                registerPassword.setError("Password must be 8 characters or more.");
                isFailed = true;
            }
            if (isFailed) {
                return false;
            }
        }
        catch(Exception e){
            Log.e("1", "Exception on attemptRegister.", e);
        }
        return true;
    }

    public void returnToLogin(View v){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void initObjects(){
        helper = new DrivrDBHelper(getApplicationContext());
        login = new LoginModel();
    }

}
