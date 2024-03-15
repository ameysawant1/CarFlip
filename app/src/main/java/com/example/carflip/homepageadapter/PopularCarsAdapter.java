package com.example.carflip.homepageadapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.carflip.R;
import com.example.carflip.homepageadapter.CarIconsAdapter;
import com.example.carflip.ui.home.HomeFragment;

import java.util.List;

public class PopularCarsAdapter extends RecyclerView.Adapter<PopularCarsAdapter.ViewHolder> {
    private List<popularcarsmodel> popularCarsList;

    public PopularCarsAdapter(List<popularcarsmodel> popularCarsList) {
        this.popularCarsList = popularCarsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_car_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        popularcarsmodel car = popularCarsList.get(position);
        holder.bind(car);
    }

    @Override
    public int getItemCount() {
        return popularCarsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView carImageView;
        private TextView carNameTextView;
        private TextView carPriceTextView;
        private TextView carLocationTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            carImageView = itemView.findViewById(R.id.popularcarsimages);
            carNameTextView = itemView.findViewById(R.id.popularcarsname);
            carPriceTextView = itemView.findViewById(R.id.popularcarsprice);
            carLocationTextView = itemView.findViewById(R.id.popularcarslocation);
        }

        public void bind(popularcarsmodel car) {
            carNameTextView.setText(car.getCarName());
            carPriceTextView.setText(car.getCarPrice());
            carLocationTextView.setText(car.getCarLocation());

            // Load image into ImageView using Glide
            Glide.with(itemView.getContext())
                    .load(car.getCarImageResource()) // Image URL
                    .into(carImageView);
        }
    }
}
