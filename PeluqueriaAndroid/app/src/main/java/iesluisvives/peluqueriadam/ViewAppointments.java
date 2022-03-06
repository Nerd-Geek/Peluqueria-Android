package iesluisvives.peluqueriadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iesluisvives.peluqueriadam.ApiRest.ApiClient;
import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.services.AppoinmentService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAppointments extends AppCompatActivity {
    private AppoinmentService appoinmentService;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<AppoinmentEntity> appoinmentEntityList;
    private RecyclerViewAppoinmentsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointments);
        appoinmentEntityList = new ArrayList<>();
        AppoinmentService appoinmentService = ApiClient.getClient().create(AppoinmentService.class);
        recyclerView = findViewById(R.id.recycleViewAppoinments);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        Call<List<AppoinmentEntity>> listCall = appoinmentService.getAllAppoinmentsByUserName(LocalUser.getInstance().getUsername().toString(),"Bearer "+LocalUser.getInstance().getToken());
        listCall.enqueue(new Callback<List<AppoinmentEntity>>() {
            @Override
            public void onResponse(Call<List<AppoinmentEntity>> call, Response<List<AppoinmentEntity>> response) {
                System.out.println(LocalUser.getInstance().toString());
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),("Code: "+response.code()),Toast.LENGTH_LONG).show();
                    appoinmentEntityList.add(new AppoinmentEntity());
                }else {

                    if(response.body().isEmpty()){
                     //   appoinmentEntityList.add(new AppoinmentEntity());
                    }else appoinmentEntityList = response.body();
                    adapter = new RecyclerViewAppoinmentsAdapter(appoinmentEntityList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<AppoinmentEntity>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
            }
        });
    }
}