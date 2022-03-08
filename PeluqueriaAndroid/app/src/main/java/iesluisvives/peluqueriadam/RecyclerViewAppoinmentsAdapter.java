package iesluisvives.peluqueriadam;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.ServiceEntity;

public class RecyclerViewAppoinmentsAdapter extends RecyclerView.Adapter<RecyclerViewAppoinmentsAdapter.ViewHolder> {
    private List<AppoinmentEntity> servicesList;

    public RecyclerViewAppoinmentsAdapter(List<AppoinmentEntity> servicesList) {
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public RecyclerViewAppoinmentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appoinment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAppoinmentsAdapter.ViewHolder holder, int position) {
        AppoinmentEntity appoinmentEntity = servicesList.get(position);
        holder.textDate.setText(appoinmentEntity.getDate().toString());
        holder.textTime.setText(appoinmentEntity.getTime().toString());
        holder.textServicename.setText(appoinmentEntity.getService().getName());
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDate, textTime, textServicename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.itemAppoinmentTextDate);
            textTime = itemView.findViewById(R.id.itemAppoinmentTextTime);
            textServicename = itemView.findViewById(R.id.itemAppoinmentTextName);
        }
    }
}
