package iesluisvives.peluqueriadam;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentRegisterUser extends Fragment {

    private Button registerbutton;

    public FragmentRegisterUser() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private void initcomponents(ViewGroup container) {
        registerbutton=container.findViewById(R.id.registerButton_id);


    }

    private void initlisteners(){
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initcomponents(container);


        return inflater.inflate(R.layout.fragment_register_user, container, false);
    }


}