package iesluisvives.peluqueriadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {


    private Button btouser, btoappointments, btoverappointments, btosettins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        Toast.makeText(this, "Es el menu principal", Toast.LENGTH_LONG).show();
        initListeners();


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


        btouser = findViewById(R.id.button_user);
        btoappointments = findViewById(R.id.button_appointments);
        btoverappointments = findViewById(R.id.button_verAppointments);
        btosettins = findViewById(R.id.button_settings);
    }


}