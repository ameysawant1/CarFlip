package com.example.carflip;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.carflip.Login;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.carflip.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class forget_passwordActivity extends AppCompatActivity {

    TextView goback_btn,recovery_text;
    TextInputEditText forgetemail;

    //Declartion of email_icon faliure.
    ImageView email_icon;
    ViewGroup forgot_password_container;
    Button reset_password_btn;
    FirebaseAuth mAuth;
    String strEmail;
    //Declartion of ProgressBar.(PB(ProgressBar)).
    ProgressBar forget_Page_PB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);
        //Declartion of goback button

        goback_btn = findViewById(R.id.Gobackbtn);

        //Declartion of forget Email(Provide Registered email)

        forgetemail = findViewById(R.id.forget_email);

        //Declartion of ResetPasswordbtn , email_icon faliure and forgot_progressBar.
        reset_password_btn = findViewById(R.id.reset_btn);
        email_icon = findViewById(R.id.forgot_password_email_icon);
        forget_Page_PB = (findViewById(R.id.forgot_progressBar));

        //animation
        recovery_text = findViewById(R.id.Recovery_text);
        forgot_password_container = findViewById(R.id.Forgot_password_container);

        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth instance

        //implementation  ResetPasswordbtn
        reset_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strEmail = forgetemail.getText().toString();
                if (TextUtils.isEmpty(strEmail)) {
                    reset_password_btn.setTextColor(Color.argb(50, 255, 255, 255));
                    Toast.makeText(forget_passwordActivity.this, "Please provide your registered email", Toast.LENGTH_SHORT).show();
                } else {
                    reset_password_btn.setEnabled(false); // Disable the button while processing
                    reset_password_btn.setTextColor(Color.rgb(255, 255, 255));
                    ResetPassword(); // Move the call to ResetPassword inside the else block
                }
            }
        });




        //settiing on click on gobacklabel
        goback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.carflip.Login.class);
                startActivity(intent);
                finish();


            }

        });



    }




    private void ResetPassword() {
        {
            TransitionManager.beginDelayedTransition(forgot_password_container);
            email_icon.setVisibility(View.VISIBLE);
            forget_Page_PB.setVisibility(View.VISIBLE);
            recovery_text.setVisibility(View.GONE);
            //
            mAuth.sendPasswordResetEmail(strEmail)

                    .addOnCompleteListener((task) ->  {
                        if(task.isSuccessful()) {
                            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, email_icon.getWidth() / 2, email_icon.getHeight() / 2);
                            scaleAnimation.setDuration(100);
                            scaleAnimation.setInterpolator(new AccelerateInterpolator());
                            scaleAnimation.setRepeatMode(Animation.REVERSE);
                            scaleAnimation.setRepeatCount(1);

                            scaleAnimation.setAnimationListener(new Animation.AnimationListener() {

                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {

                                    recovery_text.setText("Recovery email sent successfully ! check your inbox");
                                    recovery_text.setTextColor(getResources().getColor(R.color.success_green));

                                    TransitionManager.beginDelayedTransition(forgot_password_container);
                                    recovery_text.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                    email_icon.setImageResource(R.drawable.success_email_icon);
                                    email_icon.setVisibility(View.GONE);
                                }


                            });

                                    email_icon.startAnimation(scaleAnimation);




                        }
                        else {
                                String error = task.getException().getMessage();

                                recovery_text.setText(error);
                                recovery_text.setTextColor(getResources().getColor(R.color.failure_red));
                                TransitionManager.beginDelayedTransition(forgot_password_container);
                                recovery_text.setVisibility(View.VISIBLE);
                        }
                            forget_Page_PB.setVisibility(View.GONE);
                            reset_password_btn.setEnabled(true);
                        });
                    }

        }
    }




