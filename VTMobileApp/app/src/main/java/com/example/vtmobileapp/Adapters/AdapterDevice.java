package com.example.vtmobileapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vtmobileapp.Models.Device;
import com.example.vtmobileapp.R;
import com.example.vtmobileapp.ServiceActivity;

import java.util.ArrayList;

public class AdapterDevice extends RecyclerView.Adapter<AdapterDevice.ViewHolder> {

    Context context;
    ArrayList<Device> devices;

    public AdapterDevice(Context context, ArrayList<Device> devices) {
        this.context = context;
        this.devices = devices;
    }

    @NonNull
    @Override
    public AdapterDevice.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDevice.ViewHolder holder, int position) {
        String deviceName = devices.get(position).getName();
        String status = devices.get(position).getStatus();
        int id = devices.get(position).getId();

        holder.title.setText(deviceName);
        holder.status.setText(status);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String[] options = {"Servis Oluştur", "Cihaz Detayı"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Yapılacak İşlemi Seçin");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            // servis oluştur
                            Intent intent = new Intent(context, ServiceActivity.class);
                            intent.putExtra("deviceId", id);
                            context.startActivity(intent);
                        }
                        else if(which == 1){
                            // cihaz detayına bak
                        }
                    }
                });
                builder.show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title, status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.device_item_name);
            status = itemView.findViewById(R.id.device_item_status);
        }
    }
}























