package com.example.carflip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register2 extends AppCompatActivity {

    TextInputEditText editTextEmail;
    EditText editTextPassword;
    TextInputEditText confirmPassword;

    Button buttonReg;

    FirebaseAuth mAuth;

    ProgressBar ProgressBar;

    TextView textView;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        editTextEmail = findViewById(R.id.forget_email);
        mAuth = FirebaseAuth.getInstance();
        TextInputLayout layoutPassword = findViewById(R.id.password);
        editTextPassword = layoutPassword.getEditText();
        buttonReg = findViewById(R.id.btn_register);
        ProgressBar = findViewById(R.id.ProgressBar);
        textView = findViewById(R.id.loginNow);
        confirmPassword = findViewById(R.id.confirm_Password);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });




        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ProgressBar.setVisibility(View.VISIBLE);
            String email,password,confirmpass;
            email = String.valueOf(editTextEmail.getText());
            password = String.valueOf(editTextPassword.getText());
            confirmpass =   String.valueOf(confirmPassword.getText());
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Register2.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register2.this,"Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //password Validation
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register2.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 8) {
                    Toast.makeText(Register2.this, "Password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.matches(".*[A-Z].*")) {
                    Toast.makeText(Register2.this, "Password should contain at least one capital letter", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
                    Toast.makeText(Register2.this, "Password should contain at least one symbol", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.matches(".*\\d.*")) {
                    Toast.makeText(Register2.this, "Password should contain at least one number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmpass)) {
                    Toast.makeText(Register2.this, "ConfirmPasswords do not match.", Toast.LENGTH_SHORT).show();
                    ProgressBar.setVisibility(View.GONE);
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {

                            public void onComplete(@NonNull Task<AuthResult> task) {
                                ProgressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Check if the user is not null
                                    if (user != null) {
                                        user.sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            // Email sent successfully, inform the user
                                                            Toast.makeText(Register2.this, "Verification email sent. Please check your email.",
                                                                    Toast.LENGTH_LONG).show();

                                                            // You might want to sign out the user here or direct them to a waiting screen
                                                            // where they can wait for email verification
                                                            FirebaseAuth.getInstance().signOut();

                                                            // Redirect to login page or a page that instructs them to verify their email
                                                            Intent intent = new Intent(Register2.this, Login.class);
                                                            startActivity(intent);
                                                            finish();
                                                        } else {
                                                            // Failed to send verification email
                                                            Toast.makeText(Register2.this, "Failed to send verification email.",
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                    }
                                } else {
                                    // If sign in fails, display a message to the user
                                    Toast.makeText(Register2.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}