package com.mdrahorat4563.drivr;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText registerEmail;
    private EditText registerPassword;
    private EditText registerConfirmPassword;
    private Button registerButton;
    private String validToastMessage = "Success! Welcome!";
    final int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerConfirmPassword = (EditText) findViewById(R.id.registerConfirmPassword);
        registerButton = (Button) findViewById(R.id.registerButton);

         View.OnClickListener registerButtonListener = new View.OnClickListener(){
             Context context = getApplicationContext();
             Toast validToast = Toast.makeText(context, validToastMessage, duration);
            public void onClick(View v){
                attemptRegister();
                if (attemptRegister()) {
                    validToast.show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
        registerButton.setOnClickListener(registerButtonListener);
    }

    public boolean attemptRegister(){
        try {
            Boolean isFailed = false;
            if (registerEmail == null || registerEmail.getText().toString().equals("")) {
                registerEmail.setError("Email cannot be blank.");
                isFailed = true;
            }
            if (registerPassword == null || registerPassword.getText().toString().equals("")) {
                registerPassword.setError("Password cannot be blank.");
                isFailed = true;
            }
            if (registerConfirmPassword == null || registerConfirmPassword.getText().toString().equals("")) {
                registerConfirmPassword.setError("Confirm password cannot be blank.");
                isFailed = true;
            } else if (registerConfirmPassword.getText().equals(registerPassword)) {
                registerConfirmPassword.setError("Passwords must match.");
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


}
