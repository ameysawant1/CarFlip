package com.example.carflip;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CarDetailsActivity extends AppCompatActivity {

    private static final String TAG = "CarDetailsActivity";
    private TextView textViewCarBrand;
    private TextView textViewCarName;
    private TextView textViewCarPrice;
    private ImageView imageViewCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webscraper);

        // Initialize TextViews and ImageView
        textViewCarBrand = findViewById(R.id.textViewCarBrand);
        textViewCarName = findViewById(R.id.textViewCarName);
        textViewCarPrice = findViewById(R.id.textViewCarPrice);
        imageViewCar = findViewById(R.id.imageViewCar);

        // Get a reference to the Firestore database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get a reference to the document containing the data
        DocumentReference docRef = db.collection("CarData").document("0f4tyIctVa0MUkX1GLud");





        // Fetch the data from the Firestore document
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Get the data from the document
                    String carBrand = documentSnapshot.getString("carBrand");
                    String carName = documentSnapshot.getString("carName");
                    String carPrice = documentSnapshot.getString("carPrice");
                    String carImgSrc = documentSnapshot.getString("carImgSrc");
                    // Continue extracting other data fields as needed

                    // Display the data in your app's UI
                    textViewCarBrand.setText(carBrand);
                    textViewCarName.setText(carName);
                    textViewCarPrice.setText(carPrice);
                    Glide.with(CarDetailsActivity.this).load(carImgSrc).into(imageViewCar);
                } else {
                    Log.d(TAG, "No such document");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error getting document", e);
            }
        });
    }
}
