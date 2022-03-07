package iesluisvives.peluqueriadam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import iesluisvives.peluqueriadam.ApiRest.ApiClient;
import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.CreateAppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.ServiceEntity;
import iesluisvives.peluqueriadam.data.services.AppoinmentService;
import iesluisvives.peluqueriadam.data.services.ServiceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityAppointments extends AppCompatActivity {
    private ServiceService serviceService;
    private List<ServiceEntity> servicesList;
    private AppoinmentService appoinmentService;
    private List<AppoinmentEntity> appoinmentList;
    private Spinner servicesSpinner,makeAppoinmentMinuteSpinner, makeAppoinmentHourSpinner;
    private ServicesSpinnerAdapter spinnerAdapter;
    private CalendarView calendarView;
    private Button makeAppoinmentButton;
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
        makeAppoinmentHourSpinner = findViewById(R.id.makeAppoinmentHourSpinner);
        makeAppoinmentMinuteSpinner = findViewById(R.id.makeAppoinmentMinuteSpinner);
        calendarView = findViewById(R.id.calendarView);
        makeAppoinmentButton = findViewById(R.id.appoinmentMakerButton);

        spinnerControl();
        makeAppoinmentSender();
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

    private void makeAppoinmentSender() {
        makeAppoinmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CreateAppoinmentEntity appoinmentEntity = new CreateAppoinmentEntity(
                        UUID.randomUUID().toString(),
                        fromCalendarViewToLocalDate(),
                        LocalTime.of(Integer.parseInt((String)makeAppoinmentHourSpinner.getSelectedItem()),
                                Integer.parseInt((String)makeAppoinmentMinuteSpinner.getSelectedItem())),
                        LocalUser.getInstance().getId(),
                        servicesList.get(servicesSpinner.getSelectedItemPosition()).getId());
                Call<CreateAppoinmentEntity> makeAppoinmentCall = appoinmentService.createAppointment(appoinmentEntity,"Bearer "+LocalUser.getInstance().getToken());
                makeAppoinmentCall.enqueue(new Callback<CreateAppoinmentEntity>() {
                    @Override
                    public void onResponse(Call<CreateAppoinmentEntity> call, Response<CreateAppoinmentEntity> response) {
                        if(!response.isSuccessful())Toast.makeText(getApplicationContext(),("Code: "+response.code()),Toast.LENGTH_LONG).show();
                        else Toast.makeText(getApplicationContext(),"Appointment maked successfully",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<CreateAppoinmentEntity> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public LocalDate fromCalendarViewToLocalDate(){
        Calendar.getInstance().setTimeInMillis(calendarView.getDate());
        Calendar calendar = Calendar.getInstance();
        LocalDateTime lc = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        LocalDate ld = lc.toLocalDate();
        return ld;
    }
}