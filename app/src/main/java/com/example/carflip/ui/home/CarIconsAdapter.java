package com.example.carflip.ui.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carflip.R;
import com.example.carflip.CarLogoActivity;

public class CarIconsAdapter extends ArrayAdapter<Integer> {

    private Integer[] carIcons;
    private Context context;

    public CarIconsAdapter(@NonNull Context context, Integer[] carIcons) {
        super(context, 0, carIcons);
        this.carIcons = carIcons;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Integer iconId = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.car_icon_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewCarIcon);

        if (imageView != null && iconId != null) {
            imageView.setImageResource(carIcons[position]);
        } else {
            Log.e("CarIconsAdapter", "ImageView or iconId is null at position " + position);
        }

        // Set OnClickListener on convertView
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open CarLogoActivity with the clicked car logo
                Intent intent = new Intent(context, CarLogoActivity.class);
                intent.putExtra("car_logo", carIcons[position]); // Pass the car logo resource id
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
