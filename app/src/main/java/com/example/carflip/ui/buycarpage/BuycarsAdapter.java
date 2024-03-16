package com.example.carflip.ui.buycarpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carflip.R;

public class BuycarsAdapter extends RecyclerView.Adapter<BuycarsAdapter.ViewHolder> {

    private OnItemClickListener onItemClickListener; // Define an interface for item click listener

    // Constructor to initialize the item click listener
    public BuycarsAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_car_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Leave this empty for now since you don't want to display any data yet
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Return a fixed size of 0
        return 0;
    }

    // Define an interface for item click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Define ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize views here if needed
        }
    }
}
