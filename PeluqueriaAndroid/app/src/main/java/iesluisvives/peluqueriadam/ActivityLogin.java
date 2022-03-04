package iesluisvives.peluqueriadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLogin extends AppCompatActivity {
    private Button registerbuttonactivity, registerbuttonfragment, loginbutton;
    private FragmentContainerView fragmentContainerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initcomponents();
        initlisteners();
    }

    private void initlisteners() {
       registerbuttonactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*FragmentRegisterUser fragment = new FragmentRegisterUser();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainerregister_id,fragment);
            fragmentTransaction.commit();*/

            fragmentContainerView.setVisibility(View.VISIBLE);

            }
        });




    }

    private void initcomponents() {
        registerbuttonactivity = findViewById(R.id.registerButtonActivity_id);
        loginbutton = findViewById(R.id.loginButton_id);
        registerbuttonfragment = findViewById(R.id.registerButton_id);
        fragmentContainerView=findViewById(R.id.fragmentContainerregister_id);
        fragmentContainerView.setVisibility(View.GONE);
    }


}