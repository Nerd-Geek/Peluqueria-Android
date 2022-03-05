package iesluisvives.peluqueriadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView mainViewTextName;
    private Button btouser, btoappointments, btoverappointments, btosettins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        Toast.makeText(this, "Es el menu principal", Toast.LENGTH_LONG).show();
        initListeners();
       //mainViewTextName.setText(LocalUser.getInstance().getUsername().toString());

    }

    //aciones botones
    private void initListeners() {


        btouser.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityUserProfile.class);
            //arrancar la activity
            startActivity(intent);
        });

        btoappointments.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityAppointments.class);
            //arrancar la activity
            startActivity(intent);
        });


        btoverappointments.setOnClickListener(view -> {
            Intent intent = new Intent(this, ViewAppointments.class);
            //arrancar la activity
            startActivity(intent);
        });

        btosettins.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityLogin.class);
            //arrancar la activity
            startActivity(intent);
        });

    }


    private void initComponents() {

        mainViewTextName = findViewById(R.id.mainViewTextName);
        btouser = findViewById(R.id.button_user);
        btoappointments = findViewById(R.id.button_appointments);
        btoverappointments = findViewById(R.id.button_verAppointments);
        btosettins = findViewById(R.id.button_settings);
    }


}