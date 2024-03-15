package com.example.carflip.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carflip.R;

public class CarIconsAdapter<ImageView2> extends ArrayAdapter<Integer> {

    public CarIconsAdapter(@NonNull Context context, Integer[] icons) {
        super(context, 0, icons);
    }

    @SuppressLint("WrongViewCast")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        Integer iconId = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_icon_item, parent, false);
        }

        // Lookup view for data population
        ImageView imageView = convertView.findViewById(R.id.imageViewCarIcon);

        // Populate the data into the template view using the data object
        if (imageView != null && iconId != null) {
            imageView.getClass();
        } else {
            // Handle the case when imageView or iconId is null
            Log.e("CarIconsAdapter", "ImageView or iconId is null at position " + position);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}


