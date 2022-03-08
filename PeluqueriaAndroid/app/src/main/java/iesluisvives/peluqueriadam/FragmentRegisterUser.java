package iesluisvives.peluqueriadam;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.*;

import iesluisvives.peluqueriadam.ApiRest.ApiClient;
import iesluisvives.peluqueriadam.data.database.RoomDB;
import iesluisvives.peluqueriadam.data.entity.CreateUserEntity;
import iesluisvives.peluqueriadam.data.entity.UserEntity;
import iesluisvives.peluqueriadam.data.entity.UserGender;
import iesluisvives.peluqueriadam.data.entity.UserRoles;
import iesluisvives.peluqueriadam.data.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentRegisterUser extends Fragment {
    private UserService userService;
    private CreateUserEntity localUser;
    private Button registerbutton;
    private FragmentContainerView fragmentContainerView;
    private EditText usernameUserPlain, passwordUserPlain, nameUserPlain, surnamePlain,telephoneUserPlain, emailUserPlain;
    private Spinner genderSpinner;
    public FragmentRegisterUser() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userService =  ApiClient.getClient().create(UserService.class);
    }

    private void initcomponents(View view) {
        registerbutton = view.findViewById(R.id.registerButton_id);
        usernameUserPlain = view.findViewById(R.id.usernameUserPlain);
        passwordUserPlain = view.findViewById(R.id.passwordUserPlain);
        nameUserPlain = view.findViewById(R.id.nameUserPlain);
        surnamePlain = view.findViewById(R.id.surnameUserPain);
        telephoneUserPlain = view.findViewById(R.id.telephoneUserPain);
        emailUserPlain = view.findViewById(R.id.emailUserPain);
        genderSpinner = view.findViewById(R.id.registerGenderSpinner);
    }
    public  Button fedemetodo(){

        return registerbutton;
    }

    private void initlisteners(View viewarriba) {
       registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewabajo) {
                if(editTextValid()) {
                    CreateUserEntity user = new CreateUserEntity(
                            UUID.randomUUID().toString(),
                            " ",
                            usernameUserPlain.getText().toString(),
                            passwordUserPlain.getText().toString(),
                            passwordUserPlain.getText().toString(),
                            nameUserPlain.getText().toString(),
                            surnamePlain.getText().toString(),
                            telephoneUserPlain.getText().toString(),
                            emailUserPlain.getText().toString(),
                            UserGender.valueOf(genderSpinner.getSelectedItem().toString())
                            );
                    System.out.println(user);
                    Call<CreateUserEntity> registerUserinApi = userService.createUser(user);
                    registerUserinApi.enqueue(new Callback<CreateUserEntity>() {
                        @Override
                        public void onResponse(Call<CreateUserEntity> call, Response<CreateUserEntity> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(getContext(),("Code: "+response.code()),Toast.LENGTH_LONG).show();
                            }else {
                                localUser = response.body();
                                Set<UserRoles> roles = new HashSet<>();
                                roles.add(UserRoles.USER);
                                RoomDB.getInstance(getContext()).userDao().insert(new UserEntity(
                                        localUser.getId(),
                                        localUser.getImage(),
                                        localUser.getUsername(),
                                        localUser.getName(),
                                        localUser.getSurname(),
                                        localUser.getPhoneNumber(),
                                        localUser.getEmail(),
                                        UserGender.valueOf(localUser.getGender().toString()),
                                        null,
                                        roles,
                                        new HashSet<>()
                                ));

                                viewarriba.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateUserEntity> call, Throwable t) {
                            Toast.makeText(getContext(),("ERROR: " + t.getMessage()),Toast.LENGTH_LONG).show();
                        }
                    });

                    //Toast.makeText(getContext(), "dios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean editTextValid() {
        List<Boolean> notEmpty = new ArrayList<>();
        if(TextUtils.isEmpty(usernameUserPlain.getText().toString().trim())){
            usernameUserPlain.setError("Username cannot be empty");
            notEmpty.add(false);
        } else notEmpty.add(true);
        if(TextUtils.isEmpty(nameUserPlain.getText().toString().trim())){
            nameUserPlain.setError("Name cannot be empty");
        notEmpty.add(false);
        } else notEmpty.add(true);
        if(TextUtils.isEmpty(passwordUserPlain.getText().toString().trim())){
            passwordUserPlain.setError("Password cannot be empty");
         notEmpty.add(false);
        } else notEmpty.add(true);
        if(TextUtils.isEmpty(surnamePlain.getText().toString().trim())){
            surnamePlain.setError("Surname cannot be empty");
        notEmpty.add(false);
        } else notEmpty.add(true);
        if(TextUtils.isEmpty(telephoneUserPlain.getText().toString().trim())){
            telephoneUserPlain.setError("Telephone cannot be empty");
        notEmpty.add(false);
        } else notEmpty.add(true);
        if(TextUtils.isEmpty(emailUserPlain.getText().toString().trim())){
            emailUserPlain.setError("E-mail cannot be empty");
        notEmpty.add(false);
        } else notEmpty.add(true);
        boolean result = true;
        for (Boolean every: notEmpty) if (every == false) result = every;
        return result;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_register_user, container, false);

        initcomponents(view);
        initlisteners(view);

        return view;
    }


}