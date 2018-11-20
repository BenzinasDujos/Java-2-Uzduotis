package com.example.adomas.firebaseprojektasss;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPamirstas;
    private EditText editTextPamirstas;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth = FirebaseAuth.getInstance();

        buttonPamirstas = (Button) findViewById(R.id.buttonPamirstas);
        editTextPamirstas = (EditText) findViewById(R.id.editTextPamirstas);

        buttonPamirstas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String userEmailPamirstas = editTextPamirstas.getText().toString();
        if (TextUtils.isEmpty(userEmailPamirstas)) {
            Toast.makeText(ForgotPasswordActivity.this, "Please write your email", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.sendPasswordResetEmail(userEmailPamirstas).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this, "Please check your email for password retrieving", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                    }
                    else {
                        String message = task.getException().getMessage();
                        Toast.makeText(ForgotPasswordActivity.this, "Error occurred : " + message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
