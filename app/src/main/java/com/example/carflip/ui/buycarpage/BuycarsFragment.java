package com.example.carflip.ui.buycarpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carflip.R;
import com.example.carflip.databinding.FragmentBuycarsBinding;
import com.example.carflip.ui.home.CarIconsAdapter;

public class BuycarsFragment extends Fragment {

    private FragmentBuycarsBinding binding;
    private GridView car_icons;
    private RecyclerView all_cars;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BuycarsViewModel buycarsViewModel =
                new ViewModelProvider(this).get(BuycarsViewModel.class);

        binding = FragmentBuycarsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Call setupCarIcons method to initialize car icons GridView
        setupCarIcons(root);

        // Call setupAllCars method to initialize RecyclerView for all cars
        setupAllCars(root);

        return root;
    }

    // Define setupCarIcons method to initialize car icons GridView
    private void setupCarIcons(View root) {
        // Find the GridView within the fragment's layout
        car_icons = root.findViewById(R.id.car_icons);

        // Define the array of car icons
        Integer[] carIcons = {
                R.drawable.hyundai,
                R.drawable.ferrari,
                R.drawable.ford,
                R.drawable.mercedes,
                R.drawable.audi,
                R.drawable.chevrolet,
                R.drawable.nissan,
                R.drawable.volkswagen,
                R.drawable.chevrolet,
                R.drawable.nissan,
                R.drawable.volkswagen,
                R.drawable.audi,
                R.drawable.chevrolet,
                R.drawable.nissan,
                R.drawable.volkswagen,
                R.drawable.chevrolet,
        };

        // Create an adapter for the GridView
        CarIconsAdapter adapter = new CarIconsAdapter(requireContext(), carIcons);

        // Set the adapter to the GridView
        car_icons.setAdapter(adapter);

        // Set item click listener for the GridView
        car_icons.setOnItemClickListener((parent, view, position, id) -> onCarIconClicked(position));
    }

    // Define setupAllCars method to initialize RecyclerView for all cars
    private void setupAllCars(View root) {
        // Find the RecyclerView within the fragment's layout
        all_cars = root.findViewById(R.id.all_cars);

        // Set layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        all_cars.setLayoutManager(layoutManager);

        // Create and set adapter for the RecyclerView
        BuycarsAdapter adapter = new BuycarsAdapter(/* pass data if needed */);
        all_cars.setAdapter(adapter);

        // Add scroll listener to the RecyclerView for full-screen behavior
        all_cars.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // Scrolling has stopped
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                        View itemView = layoutManager.findViewByPosition(i);
                        // Modify layout params to make the item full screen
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                        layoutParams.height = recyclerView.getHeight();
                        itemView.setLayoutParams(layoutParams);
                    }
                } else {
                    // Scrolling is in progress, reset layout params
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    for (int i = firstVisibleItemPosition; i <= lastVisibleItemPosition; i++) {
                        View itemView = layoutManager.findViewByPosition(i);
                        // Reset layout params
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) itemView.getLayoutParams();
                        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT; // Reset to original height
                        itemView.setLayoutParams(layoutParams);
                    }
                }
            }
        });
    }

    private void onCarIconClicked(int position) {
        int selectedCarIcon = position;
        Toast.makeText(requireContext(), "Selected Car Icon: " + selectedCarIcon, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
