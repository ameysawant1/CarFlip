package com.example.carflip.homepageadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.carflip.R;

public class CarIconsAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private Integer[] carIcons;

    public CarIconsAdapter(Context context, Integer[] carIcons) {
        super(context, R.layout.car_icon_item, carIcons);
        this.context = context;
        this.carIcons = carIcons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.car_icon_item, parent, false);

        ImageView imageView = view.findViewById(R.id.carIconImageView);
        imageView.setImageResource(carIcons[position]);

        return view;
    }
}
