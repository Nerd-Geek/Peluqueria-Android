package iesluisvives.peluqueriadam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iesluisvives.peluqueriadam.ApiRest.ApiClient;
import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.ServiceEntity;
import iesluisvives.peluqueriadam.data.services.AppoinmentService;
import iesluisvives.peluqueriadam.data.services.ServiceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAppointments extends AppCompatActivity {
    private ChipGroup chipgroup;
    private ServiceService serviceService;
    private List<ServiceEntity> servicesList;
    private AppoinmentService appoinmentService;
    private List<AppoinmentEntity> appoinmentList;
    private Spinner servicesSpinner;
    private ServicesSpinnerAdapter spinnerAdapter;
    private CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_make);

        initcomponents();
    }

    private void initcomponents() {
        serviceService = ApiClient.getClient().create(ServiceService.class);
        appoinmentService = ApiClient.getClient().create(AppoinmentService.class);
        servicesList = new ArrayList<>();
        appoinmentList = new ArrayList<>();
        servicesSpinner = findViewById(R.id.servicesSpinner);
        calendarView = findViewById(R.id.calendarView);
        spinnerControl();
        calendarControl();
    }

    public void spinnerControl(){
        Call<List<ServiceEntity>> callServicesList = serviceService.getAllServices("Bearer "+LocalUser.getInstance().getToken());
        callServicesList.enqueue(new Callback<List<ServiceEntity>>() {
            @Override
            public void onResponse(Call<List<ServiceEntity>> call, Response<List<ServiceEntity>> response) {
                if(!response.isSuccessful()) Toast.makeText(getApplicationContext(),("Code: "+response.code()),Toast.LENGTH_LONG).show();

                else{
                    servicesList = response.body();
                    spinnerAdapter = new ServicesSpinnerAdapter(getApplicationContext(),R.id.servicesSpinner,servicesList);
                    servicesSpinner.setAdapter(spinnerAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ServiceEntity>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void calendarControl(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                i1 +=1;
                String month, day;
                if (i1<10) month = "0"+i1;
                else month = String.valueOf(i1);
                if (i1<10) day = "0"+i2;
                else day = String.valueOf(i2);
                String date = i+"-"+month+"-"+day;

                System.out.println(date);
                Call<List<AppoinmentEntity>> callAppoinmentsList =
                        appoinmentService.getAllAppoinmentsByDateAndServiceId(
                                (date),
                                servicesList.get(servicesSpinner.getSelectedItemPosition()).getId(),
                                "Bearer "+LocalUser.getInstance().getToken());
                callAppoinmentsList.enqueue(new Callback<List<AppoinmentEntity>>() {
                    @Override
                    public void onResponse(Call<List<AppoinmentEntity>> call, Response<List<AppoinmentEntity>> response) {
                        if(!response.isSuccessful()) Toast.makeText(getApplicationContext(),("Code: "+response.code()),Toast.LENGTH_LONG).show();
                        else {
                            System.out.println("OBTAINED");
                            appoinmentList = response.body();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<AppoinmentEntity>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
                        System.out.println(t.getMessage());
                    }
                });
                chipControl();
            }
        });
    }
    public void chipControl(){
        if(!appoinmentList.isEmpty()){
            for (AppoinmentEntity ap:appoinmentList) {
                System.out.println(ap);
            }
            ServiceEntity serviceSelected = servicesList.get(servicesSpinner.getSelectedItemPosition());
            Map<LocalTime,Integer> hourmap = new HashMap<>();
            for (AppoinmentEntity appoinment:appoinmentList) {
                if(hourmap.isEmpty()){
                    hourmap.put(appoinment.getTime(),1);
                }
                else if(hourmap.containsKey(appoinment.getDate())){
                    int temp = hourmap.get(appoinment.getDate());
                    hourmap.remove(appoinment.getDate());
                    hourmap.put(appoinment.getTime(),temp+1);
                }
            }
            for (LocalTime key:hourmap.keySet()) {
                if(hourmap.get(key)>=serviceSelected.getStock()){
                    for(int i = 0; i>chipgroup.getChildCount();i++) {
                        Chip chip = (Chip) chipgroup.getChildAt(i);
                        String keyHours = key.toString();
                        System.out.println(keyHours);
                        if(keyHours.equals(chip.getText().toString())){
                            chip.setEnabled(false);
                        }
                    }
                }
            }
        }
    }
}