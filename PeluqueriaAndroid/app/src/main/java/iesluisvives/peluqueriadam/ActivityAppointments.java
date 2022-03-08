package iesluisvives.peluqueriadam;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Priority;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.time.*;
import java.util.*;

import iesluisvives.peluqueriadam.ApiRest.ApiClient;
import iesluisvives.peluqueriadam.data.entity.AppoinmentEntity;
import iesluisvives.peluqueriadam.data.entity.AppoinmentEntityWithUser;
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
    private Calendar calendar;
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
        calendar = Calendar.getInstance();
        calendarView = findViewById(R.id.calendarView);
        makeAppoinmentButton = findViewById(R.id.appoinmentMakerButton);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
               calendar.set(year, month, dayOfMonth);
            }
        });
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
                Call<AppoinmentEntityWithUser> makeAppoinmentCall = appoinmentService.createAppointment(appoinmentEntity,"Bearer "+LocalUser.getInstance().getToken());
                makeAppoinmentCall.enqueue(new Callback<AppoinmentEntityWithUser>() {

                    @Override
                    public void onResponse(Call<AppoinmentEntityWithUser> call, Response<AppoinmentEntityWithUser> response) {
                        if(response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Appointment made successfully", Toast.LENGTH_LONG).show();
                        }
                        else {
                                Toast.makeText(getApplicationContext(),("This appointment is not available"),Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AppoinmentEntityWithUser> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public LocalDate fromCalendarViewToLocalDate(){
        LocalDateTime lc = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        return lc.toLocalDate();
    }
}