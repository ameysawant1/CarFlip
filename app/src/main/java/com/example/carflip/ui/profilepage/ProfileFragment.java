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
import com.example.carflip.R;
import com.example.carflip.authentication.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private FirebaseAuth auth;
    private Button button;
    private TextView textView;
    private ImageView profileImageView;
    private FirebaseUser user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

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
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(requireContext(), Login.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });

        return view;
    }
}
