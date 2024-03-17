package com.example.carflip.homepageadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.carflip.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TopCarsAdapter extends BaseAdapter {

    private Context mContext;
    private List<topcarsmodel> mCarList;
    private DatabaseReference mDatabaseReference;

    public TopCarsAdapter(Context context) {
        mContext = context;
        mCarList = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("CarData");
    }

    @Override
    public int getCount() {
        return mCarList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCarList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.top_cars_grid, parent, false);
            holder = new ViewHolder();
            holder.carNameTextView = convertView.findViewById(R.id.carName);
            holder.carPriceTextView = convertView.findViewById(R.id.carprice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        topcarsmodel car = mCarList.get(position);
        holder.carNameTextView.setText(car.getCarName());
        holder.carPriceTextView.setText(car.getCarPrice());

        return convertView;
    }

    static class ViewHolder {
        TextView carNameTextView;
        TextView carPriceTextView;
    }

    public void fetchData() {
        Query query = mDatabaseReference.orderByChild("carNumber").limitToFirst(10);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCarList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    topcarsmodel car = snapshot.getValue(topcarsmodel.class);
                    mCarList.add(car);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("TopCarsAdapter", "Failed to fetch data: " + databaseError.getMessage());
                // You can handle the error here, e.g., display a toast message
            }
        });
    }
}
