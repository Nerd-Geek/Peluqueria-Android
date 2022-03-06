package iesluisvives.peluqueriadam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import iesluisvives.peluqueriadam.data.entity.ServiceEntity;

public class ServicesSpinnerAdapter extends ArrayAdapter<ServiceEntity> {
    List<ServiceEntity> serciveList;
    LayoutInflater inflater;
    public ServicesSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<ServiceEntity> objects) {
        super(context, resource, objects);
        serciveList = objects;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_service,parent,false);
        ServiceEntity service = serciveList.get(position);
        TextView serviceTextView = view.findViewById(R.id.itemServiceTextName);
        ImageView serviceImageView = view.findViewById(R.id.itemServiceImageView);
        serviceTextView.setText(service.getName().toString());
        if(service.getImage() != null) Glide.with(getContext()).load(service.getImage().toString()).into(serviceImageView);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_service,parent,false);
        ServiceEntity service = serciveList.get(position);
        TextView serviceTextView = view.findViewById(R.id.itemServiceTextName);
        ImageView serviceImageView = view.findViewById(R.id.itemServiceImageView);
        serviceTextView.setText(service.getName().toString());
        if(service.getImage() != null) Glide.with(getContext()).load(service.getImage().toString()).into(serviceImageView);
        return view;
    }
}
