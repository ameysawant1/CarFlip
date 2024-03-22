package com.example.carflip.ui.profilepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.carflip.Login;
import com.example.carflip.R;
import com.example.carflip.Login;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;
    private Button button;
    private TextView textView;
    private ImageView profileImageView;
    private FirebaseUser user;
    private GoogleSignInClient mGoogleSignInClient;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        auth = FirebaseAuth.getInstance();

        button = view.findViewById(R.id.logout_button);
        textView = view.findViewById(R.id.email_id);
        profileImageView = view.findViewById(R.id.profile_pic);
        user = auth.getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(requireContext(), Login.class);
            startActivity(intent);
            requireActivity().finish();
        } else {
            textView.setText(user.getEmail());
            // Check if user has a profile photo
            if (user.getPhotoUrl() != null) {
                // Load profile image using Glide with placeholder and error images
                Glide.with(requireContext())
                        .load(user.getPhotoUrl())
                        .placeholder(R.drawable.logo)
                        .error(R.drawable.logo)
                        .into(profileImageView);
            } else {
                // Handle case where user does not have a profile photo
                Toast.makeText(requireContext(), "User does not have a profile photo", Toast.LENGTH_SHORT).show();
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out from Firebase
                FirebaseAuth.getInstance().signOut();

                // Sign out from Google Sign-In
                mGoogleSignInClient.signOut().addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // After signing out from both Firebase and Google Sign-In, navigate to the login page
                        Intent intent = new Intent(requireContext(), Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                });
            }
        });

        return view;
    }
}
