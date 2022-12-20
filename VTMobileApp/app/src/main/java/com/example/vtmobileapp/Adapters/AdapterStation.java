package com.example.vtmobileapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vtmobileapp.DeviceActivity;
import com.example.vtmobileapp.Models.Device;
import com.example.vtmobileapp.Models.Station;
import com.example.vtmobileapp.R;

import java.util.ArrayList;

public class AdapterStation extends RecyclerView.Adapter<AdapterStation.ViewHolder> {

    Context context;
    ArrayList<Station> stations;

    public AdapterStation(Context context, ArrayList<Station> stations) {
        this.context = context;
        this.stations = stations;
    }

    @NonNull
    @Override
    public AdapterStation.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.station_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStation.ViewHolder holder, int position) {
        String stationName = stations.get(position).getName();

        holder.title.setText(stationName);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DeviceActivity.class);
            intent.putExtra("stationId", stations.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.station_item_title);
            location = itemView.findViewById(R.id.station_item_location);
        }
    }
}


















