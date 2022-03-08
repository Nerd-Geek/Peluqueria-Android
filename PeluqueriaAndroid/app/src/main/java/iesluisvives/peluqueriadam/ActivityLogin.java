package iesluisvives.peluqueriadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iesluisvives.peluqueriadam.ApiRest.ApiClient;
import iesluisvives.peluqueriadam.data.entity.LoginEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;
import iesluisvives.peluqueriadam.data.services.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
    private Button registerbuttonactivity, registerbuttonfragment, loginbutton;
    private FragmentContainerView fragmentContainerView;
    private EditText usernameEditText, passwordEditText;
    private LoginService loginService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginService = ApiClient.getClient().create(LoginService.class); 
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

                fragmentContainerView.setVisibility(View.VISIBLE);}



        });

        loginbutton.setOnClickListener(view -> {
            LoginEntity login = new LoginEntity(usernameEditText.getText().toString(),passwordEditText.getText().toString());
            Call<UserEntity> loginCall = loginService.login(login);
            loginCall.enqueue(new Callback<UserEntity>() {
                @Override
                public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),("Code: "+response.code()),Toast.LENGTH_LONG).show();
                    }else{
                        UserEntity userResponse = response.body();
                        LocalUser.getInstance().setId(userResponse.getId());
                        LocalUser.getInstance().setUsername(userResponse.getUsername());
                        LocalUser.getInstance().setName(userResponse.getName());
                        LocalUser.getInstance().setSurname(userResponse.getSurname());
                        LocalUser.getInstance().setEmail(userResponse.getEmail());
                        LocalUser.getInstance().setTelephone(userResponse.getPhoneNumber());
                        LocalUser.getInstance().setGender(userResponse.getGender().toString());
                        LocalUser.getInstance().setToken(userResponse.getToken());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        //arrancar la activity
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<UserEntity> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
                }
            });
            

        });



    }

    private void initcomponents() {
        usernameEditText = findViewById(R.id.editTextTextUsername);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        registerbuttonactivity = findViewById(R.id.registerButtonActivity_id);
        loginbutton = findViewById(R.id.loginButton_id);
        registerbuttonfragment = findViewById(R.id.registerButton_id);
        fragmentContainerView = findViewById(R.id.fragmentContainerregister_id);
        fragmentContainerView.setVisibility(View.GONE);
    }


}